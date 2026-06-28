#!/usr/bin/env bash
# Shared configuration and helpers for the Aurora DSQL + Lambda dev scripts.
set -euo pipefail

# --- configuration (override via environment if needed) -----------------------
export AWS_PROFILE="${AWS_PROFILE:-sandbox}"
# Aurora DSQL is only available in a subset of regions; ap-northeast-1 (Tokyo) is
# supported and is the default here (low latency from Japan).
REGION="${REGION:-ap-northeast-1}"

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
INFRA_DIR="$ROOT/infra/dev"
LAMBDA_ZIP="$ROOT/build/dist/function.zip"

# --- helpers ------------------------------------------------------------------
log() { printf '\n\033[1;34m==> %s\033[0m\n' "$*"; }

# terraform wrapper bound to the infra directory, with region passed through.
tf() {
  terraform -chdir="$INFRA_DIR" "$@"
}

require_aws() {
  local acct
  acct="$(aws sts get-caller-identity --query Account --output text 2>/dev/null)" || {
    echo "ERROR: AWS credentials for profile '$AWS_PROFILE' are not valid." >&2
    exit 1
  }
  log "AWS profile '$AWS_PROFILE' -> account $acct, region $REGION"
}
