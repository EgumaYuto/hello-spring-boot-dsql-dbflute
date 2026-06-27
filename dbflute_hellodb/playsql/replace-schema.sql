-- DBFlute ReplaceSchema: local development schema (drop + recreate).
--
-- ReplaceSchema auto-drops the existing objects in the target schema (public on the
-- local Postgres, see dfprop/databaseInfoMap.dfprop) before running this SQL, so a
-- re-run cleanly re-initializes the DB. LOCAL ONLY — never run against Aurora DSQL.
--
-- This is the current full schema (the net result of the Flyway migrations
-- V1..V3, which are kept for reference). Keep both in sync when adding tables.

-- Column order mirrors the Flyway build (V1 created id/name/email/created_at, then
-- V2 appended password_hash last) so the generated DBFlute code stays identical.
CREATE TABLE users (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT now(),
    password_hash VARCHAR(255)
);

CREATE TABLE todos (
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id    UUID NOT NULL,
    title      VARCHAR(500) NOT NULL,
    done       BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMPTZ DEFAULT now()
);
