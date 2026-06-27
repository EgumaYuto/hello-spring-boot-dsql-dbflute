# hello-spring-boot-dsql-dbflute

A **DBFlute** edition of [`hello-spring-boot-dsql`](../hello-spring-boot-dsql):
same Kotlin + Spring Boot app on **Amazon Aurora DSQL** (serverless,
PostgreSQL-compatible, IAM-authenticated) running on **AWS Lambda** behind a
Function URL — but the O/R mapper is [DBFlute](http://dbflute.org/) instead of
jOOQ.

## Why Lambda instead of ECS?

Aurora DSQL is serverless (scales to zero), reachable over a **public regional
endpoint with IAM authentication**, so there is **no VPC, NAT gateway, ALB, or
Secrets Manager** to run. That pairs naturally with Lambda:

| | `hello-spring-boot` (Aurora MySQL + ECS) | this project (Aurora DSQL + Lambda) |
|---|---|---|
| DB | Aurora MySQL Serverless v2 (min 0.5 ACU, always billed) | Aurora DSQL (scales to zero) |
| DB auth | username/password in Secrets Manager | **IAM token** (no secret) |
| Networking | VPC + NAT (~$32/mo) + ALB (~$16/mo) | **none** (public endpoint + Function URL) |
| Compute | always-on Fargate task | Lambda (pay per request) |
| Idle cost | meaningful | **~$0** |
| Trade-off | warm, no cold start | JVM cold start (a few seconds) |

For "just trying DSQL", Lambda removes the entire always-on networking layer.
The only cost is a cold start on the first request — fine for a sandbox, and
reducible later with [Lambda SnapStart](https://docs.aws.amazon.com/lambda/latest/dg/snapstart.html).

## O/R mapper: DBFlute

DBFlute generates `Behavior` / `Entity` / `ConditionBean` classes from the DB
schema (similar in spirit to jOOQ's codegen). Setup here:

- **Engine (code generator)** is downloaded on demand into `mydbflute/`
  (git-ignored): `scripts/dbflute-engine.sh` fetches the DBFlute 1.3.1 release zip.
- **Client config** lives in `dbflute_hellodb/` (`dfprop/*.dfprop`): it points at
  the local Postgres, sets `database = postgresql`, `targetContainer = spring`,
  and `packageBase = org.example.dbflute`.
- **Codegen**: `scripts/dbflute-generate.sh` runs `manage.sh jdbc` (read schema)
  then `manage.sh generate`, emitting Java under
  `src/main/java/org/example/dbflute/` (committed, like jOOQ's generated sources).
- **Spring wiring**: the generated `DBFluteBeansJavaConfig` is pulled in with
  `@Import` on `MyApplication`; it binds to the Spring `dataSource` bean (the DSQL
  HikariCP datasource) and component-scans the Behaviors. `UserRepository` simply
  injects `UsersBhv` and calls `selectList { }`.
- Runtime is `org.dbflute:dbflute-runtime:1.3.1`; `spring-boot-starter-jdbc`
  supplies the `dataSource`.

## How DSQL changes the app

- **PostgreSQL-compatible**, so the driver/DBFlute/Flyway are all PostgreSQL,
  not MySQL.
- **IAM auth** via the official [Aurora DSQL JDBC connector](https://docs.aws.amazon.com/aurora-dsql/latest/userguide/SECTION_program-with-jdbc-connector.html)
  (`software.amazon.dsql:aurora-dsql-jdbc-connector`): the URL scheme is
  `jdbc:aws-dsql:postgresql://…`, the driver is
  `software.amazon.dsql.jdbc.DSQLConnector`, and short-lived auth tokens are
  generated transparently — no password.
- **Connection lifetime**: DSQL closes connections after 60 minutes, so HikariCP
  `max-lifetime` is set to 25 min (see `application.yml`, `dsql` profile).
- **No embedded Tomcat on Lambda**: `aws-serverless-java-container` provides its
  own `ServerlessMVC`, so `spring-boot-starter-tomcat` is excluded from the
  deployment package (a second servlet context leaves the Lambda handler's
  DispatcherServlet uninitialized). Tomcat is added back as `developmentOnly` for
  local `bootRun` — it stays out of the zip via `productionRuntimeClasspath`.
- **Schema differences** (see `db/migration/V1__create_users_table.sql`):
  - UUID primary key (`gen_random_uuid()`) instead of `AUTO_INCREMENT`.
  - One DDL statement per migration (DSQL allows one DDL per transaction).
  - Secondary/`UNIQUE` indexes need `CREATE INDEX ASYNC` — omitted here.
- **Schema migration on DSQL is provisioned out-of-band, not on Lambda boot.**
  Flyway is great locally, but running it inside the Lambda against DSQL stalls:
  creating its own `flyway_schema_history` table involves a secondary index, which
  DSQL only supports via `CREATE INDEX ASYNC` (built and polled on a *separate*
  connection) — that deadlocks/stalls during a cold start. So `spring.flyway.enabled`
  is `false` in the `dsql` profile, and the schema is applied once by the
  **`dsqlInit`** Gradle task (plain JDBC, single DDL, no secondary index). Locally,
  Flyway is still used as normal (`./gradlew flywayMigrate`). The AWS
  [`aurora-dsql-flyway-support`](https://github.com/awslabs/aurora-dsql-tools/tree/main/flyway)
  artifact is still on the classpath for anyone who wants to drive Flyway against
  DSQL from a non-Lambda JVM.

## Local development

Aurora DSQL has no local emulator, so locally we use a plain PostgreSQL container
(it's wire-compatible enough for migrations and DBFlute code generation).

```bash
$ docker compose up -d

# connect to local postgres (password: hellopassword)
$ psql -h 127.0.0.1 -U hellouser -d hellodb

# migration (creates the users table + password_hash column)
$ ./gradlew flywayMigrate

# generate DBFlute code from the migrated schema
# (downloads the engine into mydbflute/ on first run, then jdbc + generate)
$ ./scripts/dbflute-generate.sh

# run the app locally (normal embedded Tomcat, default profile)
$ ./gradlew bootRun
$ curl http://127.0.0.1:8080/api/health  # -> Hello World!
$ curl http://127.0.0.1:8080/api/users   # -> 401 (now auth-protected)
```

> Note: locally the backend does NOT serve the SPA (`/` returns 404 on :8080) —
> use the Vite dev server for the UI (see the next section). The SPA is only
> bundled into the app for the AWS deployment.

> The generated DBFlute sources under `src/main/java/org/example/dbflute/` are
> committed, so the deployable build does not need a database or the engine.
> Re-run `./scripts/dbflute-generate.sh` after changing a migration.

## Web app: React frontend + JWT login

A React + TypeScript single-page app lives in [`frontend/`](frontend/). It has a
sign-up / login flow and a protected page (the user list). Auth is **stateless
JWT**: `POST /api/auth/login` (or `/register`) returns a token, the SPA stores it
in `localStorage` and sends it as `Authorization: Bearer <token>`; everything
under `/api/**` except `/api/auth/**` requires a valid token. The token-issuing
secret is `app.jwt.secret` in `application.yml` (override via `APP_JWT_SECRET` in
production). Passwords are stored as BCrypt hashes in `users.password_hash`.

### Run both locally

The Vite dev server (`:5173`) proxies `/api` to Spring Boot (`:8080`), so the
browser talks to a single origin.

```bash
# one-time: start DB + migrate (see "Local development" above)
$ docker compose up -d && ./gradlew flywayMigrate

# start backend + frontend together (Ctrl-C stops both)
$ ./scripts/dev.sh

# ...or in two terminals:
$ ./gradlew bootRun                       # terminal A: backend on :8080
$ cd frontend && npm install && npm run dev  # terminal B: frontend on :5173
```

Then open <http://localhost:5173>, register a user, and you land on the protected
user-list page. Reloading keeps you logged in (token in `localStorage`); logout
clears it.

Quick API check without the UI:

```bash
$ curl -s -X POST http://127.0.0.1:8080/api/auth/register \
    -H 'Content-Type: application/json' \
    -d '{"name":"Ada","email":"ada@example.com","password":"secret123"}'
# -> {"token":"<jwt>","user":{"id":"...","name":"Ada","email":"ada@example.com"}}

$ TOKEN=<jwt>
$ curl -s http://127.0.0.1:8080/api/users -H "Authorization: Bearer $TOKEN"
# -> [{"id":"...","name":"Ada","email":"ada@example.com"}]
```

### Run the local frontend against the deployed Lambda API

Once the API is deployed to AWS (below), you can keep developing the SPA locally
but have its `/api` calls hit the **real Aurora DSQL + Lambda** backend — no SPA
hosting on AWS required. The Vite proxy target is configurable via `API_TARGET`;
`scripts/frontend-lambda.sh` fills it from the Terraform `function_url` output:

```bash
$ ./scripts/frontend-lambda.sh   # Vite on :5173, /api -> deployed Function URL
```

The browser still talks only to `localhost:5173` (Vite proxies server-side), so
there is no CORS and the SPA is served by Vite with correct content types.

## Deploy to AWS (Aurora DSQL + Lambda) — API only

The Lambda hosts the **JSON API only** (see "Why the SPA isn't served from Lambda"
below). Run the React UI locally against it with `./scripts/frontend-lambda.sh`.

Prerequisites:
- A valid AWS profile. Override with `AWS_PROFILE=...` (default `sandbox`). An
  assume-role profile is fine — `dsqlInit` puts `software.amazon.awssdk:sts` on
  its (local-only) classpath so the SDK can resolve it.
- A region where **Aurora DSQL is available** (default `us-east-1`; override with
  `REGION=...`). DSQL is not yet in every region.
- A JDK to launch Gradle (17+); the build targets Java 21 and Gradle
  auto-provisions a 21 toolchain (the DSQL support artifacts require Java 21).

```bash
$ ./scripts/deploy.sh     # build zip -> terraform apply -> dsqlInit
$ ./scripts/teardown.sh   # destroys everything to stop charges
```

After `deploy.sh` (the first request is a cold start):

```bash
$ curl  https://<function-url>/api/health  # -> Hello World!  (quick health check)
$ curl  https://<function-url>/api/users   # -> 401 (auth required)

# register/login to get a token, then read the protected endpoint:
$ TOKEN=$(curl -s -X POST https://<function-url>/api/auth/register \
    -H 'Content-Type: application/json' \
    -d '{"name":"Ada","email":"ada@example.com","password":"secret123"}' \
    | sed -n 's/.*"token":"\([^"]*\)".*/\1/p')
$ curl https://<function-url>/api/users -H "Authorization: Bearer $TOKEN"
```

### Why the SPA isn't served from Lambda

We tried bundling the built SPA into the Lambda and serving it via the Function
URL (single origin, zero extra infra). The **API works**, but **static assets come
back as `application/json`**: `aws-serverless-java-container` builds the response
with `multiValueHeaders` only, while a Lambda **Function URL** (payload v2) reads
single-value `headers`, so the `Content-Type` Spring sets (text/html, JS, CSS) is
dropped and the platform defaults it to `application/json`. Browsers then refuse to
run the JS module / render the HTML. It's a container/Function-URL limitation, not
fixable from app code.

Getting the **JWT API** working under the serverless container also required three
fixes (kept in `SecurityConfig` / `JwtAuthenticationFilter`):
- `requestMatchers(antMatcher(...))` instead of String patterns — the container's
  servlet `getMappings()` returns `null`, which NPEs Spring Security's default
  servlet-mapping introspection.
- The JWT filter is constructed in `SecurityConfig` (not a `@Component`), so it is
  not also auto-registered as a top-level servlet filter — that copy ran before
  `SecurityContextHolderFilter`, which then discarded the authentication.
- `sessionManagement { sessionFixation { none() } }` — the container has no real
  HTTP session, so `request.changeSessionId()` threw `UnsupportedOperationException`.

For a production-shaped static frontend, put the SPA on **S3 + CloudFront** and
route `/api/*` to the Function URL (correct content types, HTTPS, still ~$0 idle).

## What's deployed

A single Terraform stack (`infra/aws/`, local state):

- `aws_dsql_cluster` — the serverless database.
- `aws_lambda_function` (Java 21) running Spring Boot via
  `aws-serverless-java-container`, packaged as a zip (`./gradlew lambdaZip`).
- `aws_lambda_function_url` — public URL (`AuthType: NONE`) serving the JSON API.
- IAM role granting the Lambda `dsql:DbConnectAdmin` on the cluster (no secrets).
- CloudWatch log group.

## Logs

On Lambda (the `dsql` profile) the app uses Spring Boot 3.4 structured logging
(`logging.structured.format.console: ecs`), so each log event — stack traces
included — is a single ECS-JSON line. That keeps CloudWatch readable (one event
per log, not one per stack-trace line) and queryable, e.g. in Logs Insights:

```
fields @timestamp, `log.level`, `log.logger`, message, `error.stack_trace`
| filter `log.level` = "ERROR"
| sort @timestamp desc
```

Local dev keeps the plain-text format. (The Lambda *platform* lines —
`START`/`END`/`REPORT` — stay text; flip them to JSON too via the function's
`logging_config { log_format = "JSON" }` if you want.)

## Status

Verified end-to-end against a real Aurora DSQL cluster + Lambda Function URL in
`us-east-1`: `GET /` returns `Hello World!`, and `GET /user` reads the DSQL
`users` table via DBFlute's `UsersBhv.selectList` (returns `[]`, or the seeded row
after `DSQL_SEED=1`). Cold start is a few seconds; warm requests are sub-second.
