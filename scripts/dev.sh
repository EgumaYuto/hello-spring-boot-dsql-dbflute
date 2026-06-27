#!/usr/bin/env bash
# Convenience launcher for local full-stack development.
#
# Starts the Spring Boot backend (:8080) and the Vite dev server (:5173) and
# tears both down on Ctrl-C. Assumes the local Postgres is already up and
# migrated (see README "Local development").
#
#   docker compose up -d
#   ./gradlew flywayMigrate
#   ./scripts/dev.sh
#
set -euo pipefail
cd "$(dirname "$0")/.."

if [ ! -d frontend/node_modules ]; then
  echo "[dev] installing frontend deps..."
  (cd frontend && npm install)
fi

pids=()
cleanup() {
  echo
  echo "[dev] shutting down..."
  for pid in "${pids[@]}"; do
    kill "$pid" 2>/dev/null || true
  done
}
trap cleanup EXIT INT TERM

echo "[dev] starting Spring Boot on :8080 ..."
./gradlew bootRun &
pids+=($!)

echo "[dev] starting Vite dev server on :5173 ..."
(cd frontend && npm run dev) &
pids+=($!)

echo "[dev] backend :8080  frontend http://localhost:5173  (Ctrl-C to stop)"
wait
