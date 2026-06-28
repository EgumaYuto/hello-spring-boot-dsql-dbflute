-- DBFlute ReplaceSchema: local development schema (drop + recreate).
--
-- ReplaceSchema auto-drops the existing objects in the target schema (public on the
-- local Postgres, see dfprop/databaseInfoMap.dfprop) before running this SQL, so a
-- re-run cleanly re-initializes the DB. LOCAL ONLY — never run against Aurora DSQL.
--
-- Keep this in sync with the DSQL provisioning in src/.../tools/DbInit.kt.

-- Column order mirrors the Flyway build (V1 created id/name/email/created_at, then
-- V2 appended password_hash last) so the generated DBFlute code stays identical.
CREATE TABLE users (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT now(),
    password_hash VARCHAR(255)
);

-- Classification master for todos.status (DBFlute table classification "TodoStatus").
-- `cls_` prefix marks classification masters (exempted from the UUID-PK policy via
-- schemaPolicyMap tableExceptList prefix:cls_). code is the PK by classification convention.
CREATE TABLE cls_todo_status (
    code       VARCHAR(20) PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    disp_order INTEGER NOT NULL
);
INSERT INTO cls_todo_status (code, name, disp_order) VALUES
    ('TODO', 'To Do', 1),
    ('DOING', 'Doing', 2),
    ('DONE', 'Done', 3);

CREATE TABLE todos (
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id    UUID NOT NULL,
    title      VARCHAR(500) NOT NULL,
    status     VARCHAR(20) NOT NULL DEFAULT 'TODO',
    created_at TIMESTAMPTZ DEFAULT now()
);
