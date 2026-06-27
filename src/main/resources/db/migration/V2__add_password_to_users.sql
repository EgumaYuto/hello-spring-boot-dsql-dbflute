-- Add a password hash column so users can authenticate (JWT login).
-- Aurora DSQL notes (same as V1):
--   * One DDL statement per migration (DSQL allows one DDL per transaction).
--   * No secondary / UNIQUE index here: email uniqueness is enforced in the app
--     layer instead (DSQL requires CREATE INDEX ASYNC for secondary indexes).
-- Stores a BCrypt hash, never a plaintext password.
ALTER TABLE users ADD COLUMN password_hash VARCHAR(255);
