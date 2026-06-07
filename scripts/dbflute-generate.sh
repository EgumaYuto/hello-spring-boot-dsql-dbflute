#!/usr/bin/env bash
#
# Generate DBFlute classes from the local Postgres schema:
#   ensure engine -> manage jdbc (read schema) -> manage generate (emit classes)
#
# Prerequisites: local Postgres up (docker compose up -d) and migrated
# (./gradlew flywayMigrate), so the `users` table exists.
#
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# 1. make sure the engine is present
"$ROOT/scripts/dbflute-engine.sh"

# 2. read schema -> SchemaXML, then generate the Behavior/Entity/ConditionBean code
cd "$ROOT/dbflute_hellodb"
./manage.sh jdbc
./manage.sh generate
