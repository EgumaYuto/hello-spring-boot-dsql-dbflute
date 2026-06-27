#!/usr/bin/env bash
#
# Initialize / reset the LOCAL development database with DBFlute ReplaceSchema:
#   ensure engine -> manage replace-schema (drop all + run playsql/replace-schema.sql)
#
# This DROPS every table in the local Postgres schema and recreates them, so it is
# LOCAL ONLY. Never point it at Aurora DSQL.
#
# Prerequisites: local Postgres up (docker compose up -d).
#
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# 1. make sure the engine is present
"$ROOT/scripts/dbflute-engine.sh"

# 2. drop + recreate the schema from dbflute_hellodb/playsql/replace-schema.sql.
#    replace-schema asks for confirmation; auto-answer "y" so it runs non-interactively.
cd "$ROOT/dbflute_hellodb"
echo "y" | ./manage.sh replace-schema
