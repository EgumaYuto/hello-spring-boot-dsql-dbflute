buildscript {
    dependencies {
        // Flyway needs the PostgreSQL database module on its own classpath for the
        // Gradle `flywayMigrate` task (used against the local Postgres container).
        classpath("org.flywaydb:flyway-database-postgresql:11.3.1")
    }
}

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.flywaydb.flyway") version "11.3.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// The project has a second main() (org.example.tools.DbInit, used by dsqlInit),
// so pin the Spring Boot application class explicitly.
springBoot {
    mainClass.set("org.example.MyApplicationKt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    // On Lambda the request handling is done by aws-serverless-java-container's
    // ServerlessMVC, not a real embedded server. The embedded Tomcat must be
    // excluded or it starts a second servlet context and the Lambda handler's
    // DispatcherServlet ends up uninitialized ("ServletConfig has not been
    // initialized"). Tomcat is added back as developmentOnly for local `bootRun`
    // (developmentOnly is not on runtimeClasspath, so it stays out of the zip).
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    developmentOnly("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // --- Authentication: Spring Security + JWT -------------------------------
    // Security provides the BCrypt password encoder and the stateless filter
    // chain; JWT (jjwt) carries the authenticated identity so no server-side
    // session is needed (fits the Lambda + DSQL stateless model).
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // --- O/R mapper: DBFlute ---------------------------------------------------
    // Runtime for the generated Behavior/Entity/ConditionBean classes (generated
    // under src/main/java/org/example/dbflute by `scripts/dbflute-generate.sh`).
    implementation("org.dbflute:dbflute-runtime:1.3.1")

    // --- Database migration (PostgreSQL-compatible, incl. Aurora DSQL) ---------
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    // AWS official Flyway support for Aurora DSQL (IAM auth + DSQL dialect quirks).
    implementation("software.amazon.dsql:aurora-dsql-flyway-support:1.0.1")

    // --- JDBC drivers ----------------------------------------------------------
    // Plain Postgres driver for local development against the docker-compose DB.
    runtimeOnly("org.postgresql:postgresql")
    // Aurora DSQL JDBC connector: wraps pgjdbc and generates IAM auth tokens
    // transparently for `jdbc:aws-dsql:postgresql://...` URLs.
    implementation("software.amazon.dsql:aurora-dsql-jdbc-connector:1.5.0")

    // --- Run the Spring Boot app inside AWS Lambda -----------------------------
    implementation("com.amazonaws.serverless:aws-serverless-java-container-springboot3:2.1.5")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

// Local development database (docker-compose Postgres). Override via -P flags if needed.
val localDbUrl = "jdbc:postgresql://127.0.0.1:5432/hellodb"
val localDbUser = "hellouser"
val localDbPassword = "hellopassword"

flyway {
    url = localDbUrl
    user = localDbUser
    password = localDbPassword
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin") {
            useVersion(io.gitlab.arturbosch.detekt.getSupportedKotlinVersion())
        }
    }
}

// --- AWS Lambda deployment package -------------------------------------------
// Lambda's Java runtime puts `/var/task` and `/var/task/lib/*.jar` on the
// classpath, so we ship compiled classes/resources at the zip root and every
// dependency jar under lib/. This keeps each dependency's META-INF intact
// (no shaded uber-jar), which Spring relies on for auto-configuration.
tasks.register<Zip>("lambdaZip") {
    group = "build"
    description = "Builds the AWS Lambda deployment package (build/dist/function.zip)."
    dependsOn(tasks.named("classes"))
    archiveFileName.set("function.zip")
    destinationDirectory.set(layout.buildDirectory.dir("dist"))

    from(sourceSets.main.get().output) // classes + resources at zip root
    into("lib") {
        // productionRuntimeClasspath = runtimeClasspath minus `developmentOnly`,
        // so the local-only embedded Tomcat is kept out of the deployment package.
        from(configurations.named("productionRuntimeClasspath"))
    }
}

// --- One-off Aurora DSQL schema provisioning ---------------------------------
// Runs org.example.tools.DbInit against DSQL using the JDBC connector (IAM auth
// from the ambient AWS credentials). Usage:
//   AWS_PROFILE=sandbox DSQL_JDBC_URL='jdbc:aws-dsql:postgresql://<ep>:5432/postgres?user=admin' \
//     ./gradlew dsqlInit
//
// `sts` is needed only here so the SDK can resolve assume-role AWS profiles when
// generating the token locally. It is deliberately kept out of the Lambda zip
// (the function authenticates via its execution role, no assume-role needed).
val dsqlToolRuntime by configurations.creating {
    extendsFrom(configurations.named("productionRuntimeClasspath").get())
}
dependencies {
    dsqlToolRuntime("software.amazon.awssdk:sts:2.44.13")
}
tasks.register<JavaExec>("dsqlInit") {
    group = "application"
    description = "Provision the Aurora DSQL schema (plain JDBC, no Flyway)."
    dependsOn(tasks.named("classes"))
    classpath = sourceSets.main.get().output + dsqlToolRuntime
    mainClass.set("org.example.tools.DbInitKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    // Aurora DSQL's flyway-support artifact is compiled for Java 21, so the whole
    // project (and the Lambda runtime) targets 21.
    jvmToolchain(21)
}
