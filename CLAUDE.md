# CLAUDE.md

Guidance for working in this repo. Keep this file current when conventions change.

## What this is

A sandbox web app: **Kotlin + Spring Boot** API with a **React + TypeScript** SPA,
**DBFlute** as the O/R mapper, on **Amazon Aurora DSQL** (serverless, PostgreSQL-
compatible, IAM auth) running on **AWS Lambda** behind a Function URL. Auth is
**stateless JWT**. See `README.md` for the deep dive.

- Backend: `src/main/kotlin/org/example/` — `controller/` (REST + DTOs), `security/`
  (JWT + Spring Security), `repository/` (DBFlute behaviors), `tools/DbInit.kt` (DSQL
  schema provisioner).
- Generated DBFlute code: `src/main/java/org/example/dbflute/` (committed).
- Frontend: `frontend/` (Vite + React + TS).
- DBFlute client: `dbflute_hellodb/` (dfprop config, playsql, schema/, output/).
- Infra (Terraform, local state): `infra/dev/`, `infra/local/`.

## Local development

```bash
docker compose up -d                 # local Postgres (stands in for DSQL)
./scripts/dbflute-replace-schema.sh  # init/reset local schema (DBFlute ReplaceSchema)
./scripts/dbflute-generate.sh        # regenerate DBFlute code (jdbc + generate)
./scripts/dev.sh                     # backend :8080 + frontend :5173
```

- The local schema is owned by **DBFlute ReplaceSchema** (`dbflute_hellodb/playsql/replace-schema.sql`).
  **Flyway is disabled locally** (`spring.flyway.enabled: false`); the `db/migration` files are
  kept only for reference. DSQL is provisioned out-of-band by `DbInit.kt` (not Flyway).
- Backend does NOT serve the SPA locally (`/` is 404 on :8080); use Vite for the UI.
- Run the local SPA against the deployed dev API: `./scripts/frontend-lambda.sh` (Vite :5174).

## Environments (Terraform)

- **dev** = `infra/dev/` — full stack (DSQL + Lambda + Function URL + SSM + IAM + Logs).
  Manage with `./scripts/deploy.sh` and `./scripts/teardown.sh`. Default region is
  **ap-northeast-1 (Tokyo)** (`scripts/lib.sh`, `infra/dev/variables.tf`).
- **local** = `infra/local/` — a DSQL-only cluster for running DBFlute AlterCheck against
  real DSQL. Manage with `./scripts/local-dsql.sh up|url|down`.
- Each stack has its own local `terraform.tfstate` (git-ignored). The DBFlute engine
  (`mydbflute/`) is git-ignored and fetched by `scripts/dbflute-engine.sh`.

## Aurora DSQL constraints (important)

DSQL is PostgreSQL-compatible but limited. The schema/migrations must respect:

- **No foreign keys** — use DBFlute pseudo FKs (`additionalForeignKeyMap.dfprop`) instead.
- **No secondary indexes inline** — they require `CREATE INDEX ASYNC` (avoided here).
- **One DDL per transaction** — `DbInit.kt` runs each statement separately (auto-commit).
- **`ALTER TABLE ADD COLUMN` cannot carry a constraint** (NOT NULL/DEFAULT) — add a plain
  column then backfill.
- **`ALTER TABLE DROP COLUMN` is unsupported** — drop is best-effort; leaving an unused
  column is harmless (generated entities are based on the local schema, not DSQL).
- **Flyway can't run inside Lambda** (its history table needs a secondary index) → schema is
  applied by `./gradlew dsqlInit` (DbInit.kt) out-of-band.
- Keep `playsql/replace-schema.sql` (local) and `DbInit.kt` (DSQL) in sync.

## DBFlute notes & gotchas

- Day-to-day regeneration: `./scripts/dbflute-generate.sh` (jdbc + generate). `manage.sh
  regenerate`/`renewal` also run doc + SchemaPolicyCheck + OutsideSqlTest.
- **SchemaPolicyCheck runs on `manage.sh doc`** (not jdbc/generate). Current policies
  (`dfprop/schemaPolicyMap.dfprop`): tables need a PK + lower-case names; columns lower-case;
  **FKs forbidden**; **PK must be `id` + UUID**; `*_at` columns must be timestamp.
- **dfprop list/map items must not have trailing inline `# ...` comments** (DBFlute folds the
  rest of the line into the value). Comments go on their own lines.
- **SchemaPolicy supplement comments** use `then bad => <text>`, and the text **must not
  contain `;`** (the dfprop list delimiter).
- `outsideSqlMap.dfprop` sets `sqlPackage = $$PACKAGE_BASE$$` so OutsideSqlTest doesn't scan
  the Flyway `db/migration` SQL.
- **Classification (区分値):** master tables use a **`cls_` prefix** (e.g. `cls_todo_status`)
  and a `code` PK. `cls_*` tables are exempted from the UUID-PK policy via
  `tableExceptList = list:{ prefix:cls_ }`. A table classification is deployed onto a column
  via a pseudo FK (so the column also needs a `columnExceptMap` entry to pass the no-FK policy).
  Classification columns get protected plain getters/setters; use the generated typed
  accessors (`setStatus_ToDo()`, `getStatusAsTodoStatus()`, `CDef.TodoStatus.of(code)`).
- `schema/` and `output/` ARE committed (per the dbflute-example convention); `log/`,
  `mydbflute/`, and `dbflute-intro.*` are git-ignored.

## Serverless container (aws-serverless-java-container) gotchas

Spring Security + the serverless container needed three fixes (in `security/`):
- Use `requestMatchers(antMatcher(...))`, not String patterns (the container's servlet
  `getMappings()` returns null → NPE otherwise).
- The JWT filter is constructed in `SecurityConfig` (NOT a `@Component`), so it isn't also
  auto-registered as a top-level servlet filter (which would run before
  SecurityContextHolderFilter and lose the auth).
- `sessionManagement { sessionFixation { none() } }` (no real HTTP session on Lambda).
- A Function URL **cannot serve a browser SPA**: the container emits `multiValueHeaders` only,
  so the SPA's Content-Type is dropped and defaults to `application/json`. The SPA therefore
  runs locally (Vite) and only the API is on Lambda.
- CORS: even via the Vite proxy the browser sends `Origin` on POST/PATCH, so Spring Security
  evaluates CORS. `allowedOriginPatterns` covers `http://localhost:*`; **PATCH must be in
  `allowedMethods`**.

## Secrets / config

- JWT secret: locally a dev default in `application.yml`; on Lambda (dsql profile) read from
  **SSM Parameter Store** at startup via spring-cloud-aws (`spring.config.import:
  aws-parameterstore:/...`). `spring.cloud.aws.parameterstore.enabled` is false by default and
  true only in the dsql profile, so local dev makes no AWS call.

## Conventions / workflow

- Work on a feature branch; open a PR and merge (squash/merge) — don't commit directly to
  `main`. Commits end with the `Co-Authored-By: Claude ...` trailer; PR bodies end with the
  Claude Code generation note.
- After backend changes: `./gradlew compileKotlin detekt`. After schema/policy changes:
  `manage.sh doc` (expect `violations=0`). After frontend changes: `cd frontend && npm run build`.
- Verify a deploy with `./scripts/frontend-lambda.sh` or curl against the Function URL
  (`terraform -chdir=infra/dev output -raw function_url`).
