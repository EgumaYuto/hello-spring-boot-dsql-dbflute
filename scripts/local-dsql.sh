#!/usr/bin/env bash
#
# Manage the "local" environment's DSQL-only stack (infra/local) used to run DBFlute
# AlterCheck against a real Aurora DSQL from a developer's machine. No Lambda etc.
#
#   ./scripts/local-dsql.sh up     # create the DSQL cluster, print its JDBC URL
#   ./scripts/local-dsql.sh url    # print connection info (endpoint / JDBC URL)
#   ./scripts/local-dsql.sh down   # destroy the cluster
#
# Idle cost is ~$0 (DSQL scales to zero); run `down` when you no longer need it.
#
set -euo pipefail
source "$(dirname "${BASH_SOURCE[0]}")/lib.sh"

LOCAL_INFRA_DIR="$ROOT/infra/local"

# terraform wrapper bound to the local (DSQL-only) infra directory.
tflocal() {
  terraform -chdir="$LOCAL_INFRA_DIR" "$@"
}

print_connection() {
  local ep
  ep="$(tflocal output -raw dsql_endpoint)"
  echo "    endpoint : ${ep}"
  echo "    jdbc url : jdbc:aws-dsql:postgresql://${ep}:5432/postgres?user=admin"
}

cmd="${1:-}"
case "$cmd" in
  up)
    require_aws
    log "Creating local DSQL cluster (region: $REGION)"
    tflocal init -input=false >/dev/null
    tflocal apply -auto-approve -input=false -var "region=$REGION"
    log "Local DSQL ready (for DBFlute AlterCheck)."
    print_connection
    ;;
  url)
    print_connection
    ;;
  down)
    require_aws
    log "Destroying local DSQL cluster"
    tflocal destroy -auto-approve -input=false -var "region=$REGION"
    log "Local DSQL removed."
    ;;
  *)
    echo "usage: $0 {up|url|down}" >&2
    exit 1
    ;;
esac
