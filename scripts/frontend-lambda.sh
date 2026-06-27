#!/usr/bin/env bash
# Run the React frontend locally (Vite dev server) but point its /api proxy at the
# DEPLOYED Lambda Function URL instead of a local backend. This lets you exercise
# the real Aurora DSQL + Lambda API from the local SPA without hosting the SPA on
# AWS (the serverless container can't serve a browser SPA with correct content
# types — see README). The browser still talks only to localhost:5173, so no CORS.
#
# Requires: the stack to be deployed (./scripts/deploy.sh) so terraform can output
# the Function URL, plus Node.js/npm.
#
# Usage: ./scripts/frontend-lambda.sh
#
set -euo pipefail
cd "$(dirname "$0")/.."

URL="$(terraform -chdir=infra/aws output -raw function_url)"
URL="${URL%/}" # strip trailing slash for use as a proxy target origin

echo "[frontend-lambda] UI on http://localhost:5174  (/api -> $URL)"

if [ ! -d frontend/node_modules ]; then
  echo "[frontend-lambda] installing frontend deps..."
  (cd frontend && npm install)
fi

# Runs on :5174 (npm run dev:lambda) so it won't clash with the local-backend
# dev server on :5173.
cd frontend
API_TARGET="$URL" npm run dev:lambda
