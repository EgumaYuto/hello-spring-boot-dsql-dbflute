package org.example.tools

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * One-off schema provisioning for Aurora DSQL, run via `./gradlew dsqlInit`.
 *
 * Flyway is not used against DSQL inside the Lambda (its schema-history table needs
 * a secondary index, which DSQL only supports via async index creation and which
 * stalls during a cold start). This runner instead applies the schema with plain
 * JDBC through the Aurora DSQL JDBC connector, which generates the IAM auth token
 * from the ambient AWS credentials (e.g. AWS_PROFILE).
 *
 * Connection details come from the DSQL_JDBC_URL env var, e.g.
 *   jdbc:aws-dsql:postgresql://<cluster-id>.dsql.us-east-1.on.aws:5432/postgres?user=admin
 *
 * Keep this in sync with dbflute_hellodb/playsql/replace-schema.sql (the local schema).
 * Each statement runs on its own (auto-committed) so DSQL's "one DDL per transaction"
 * rule is respected, and every statement is idempotent.
 */

// label -> SQL. DSQL rejects ADD COLUMN with a constraint and DROP COLUMN, so the
// todos migration adds a plain column + backfills (done is dropped best-effort below).
private val SCHEMA_STATEMENTS: List<Pair<String, String>> = listOf(
    "users table" to """
        CREATE TABLE IF NOT EXISTS users (
            id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            name          VARCHAR(100) NOT NULL,
            email         VARCHAR(255) NOT NULL,
            password_hash VARCHAR(255),
            created_at    TIMESTAMPTZ DEFAULT now()
        )
    """.trimIndent(),
    "cls_todo_status table" to """
        CREATE TABLE IF NOT EXISTS cls_todo_status (
            code       VARCHAR(20) PRIMARY KEY,
            name       VARCHAR(50) NOT NULL,
            disp_order INTEGER NOT NULL
        )
    """.trimIndent(),
    "todos table" to """
        CREATE TABLE IF NOT EXISTS todos (
            id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            user_id    UUID NOT NULL,
            title      VARCHAR(500) NOT NULL,
            status     VARCHAR(20) NOT NULL DEFAULT 'TODO',
            created_at TIMESTAMPTZ DEFAULT now()
        )
    """.trimIndent(),
    "todos.status column" to "ALTER TABLE todos ADD COLUMN IF NOT EXISTS status VARCHAR(20)",
    "todos.status backfill" to "UPDATE todos SET status = 'TODO' WHERE status IS NULL",
)

private val SEED_CLS_TODO_STATUS = """
    INSERT INTO cls_todo_status (code, name, disp_order) VALUES
        ('TODO', 'To Do', 1), ('DOING', 'Doing', 2), ('DONE', 'Done', 3)
    ON CONFLICT (code) DO NOTHING
""".trimIndent()

fun main() {
    val url = System.getenv("DSQL_JDBC_URL")
        ?: error("DSQL_JDBC_URL env var is required, e.g. jdbc:aws-dsql:postgresql://<ep>:5432/postgres?user=admin")

    println("Connecting to: ${url.substringBefore('?')}")
    DriverManager.getConnection(url).use { conn ->
        for ((label, sql) in SCHEMA_STATEMENTS) {
            conn.createStatement().use { it.execute(sql) }
            println("OK: $label")
        }
        conn.prepareStatement(SEED_CLS_TODO_STATUS).use { it.executeUpdate() }
        println("OK: cls_todo_status seeded.")

        dropLegacyDoneColumn(conn)
        if (System.getenv("DSQL_SEED") == "1") seedSampleUser(conn)
    }
}

/**
 * Best-effort drop of the legacy boolean `done` column. Aurora DSQL doesn't support
 * ALTER TABLE DROP COLUMN, so this is skipped there — the unused column is harmless
 * (the generated entity has no `done`).
 */
private fun dropLegacyDoneColumn(conn: Connection) {
    try {
        conn.createStatement().use { it.execute("ALTER TABLE todos DROP COLUMN IF EXISTS done") }
        println("OK: todos.done dropped.")
    } catch (e: SQLException) {
        println("SKIP: could not drop todos.done (${e.message})")
    }
}

/** Optional sample user, inserted when DSQL_SEED=1 (handy for the demo). */
private fun seedSampleUser(conn: Connection) {
    conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)").use { ps ->
        ps.setString(1, "Ada")
        ps.setString(2, "ada@example.com")
        ps.executeUpdate()
    }
    println("OK: seeded sample user (Ada).")
}
