-- Per-user TODO items. Aurora DSQL notes (same as V1):
--   * Single DDL statement per migration (one DDL per transaction on DSQL).
--   * UUID primary key via gen_random_uuid(); no SERIAL/sequences.
--   * No secondary index / FK: DSQL needs CREATE INDEX ASYNC for secondary indexes,
--     so user_id is a plain column and the user<->todo relationship is enforced in
--     the application layer (queries always filter by user_id).
CREATE TABLE todos (
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id    UUID NOT NULL,
    title      VARCHAR(500) NOT NULL,
    done       BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMPTZ DEFAULT now()
);
