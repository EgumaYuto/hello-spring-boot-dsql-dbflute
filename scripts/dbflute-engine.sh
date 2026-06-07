#!/usr/bin/env bash
#
# Download the DBFlute engine (code generator) into mydbflute/ if it's not there.
# The engine is large and regenerable, so mydbflute/ is git-ignored — run this
# once after cloning, before `dbflute_hellodb/manage.sh ...` or `./gradlew dbfluteGenerate`.
#
set -euo pipefail

DBFLUTE_VERSION="${DBFLUTE_VERSION:-1.3.1}"
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
ENGINE_DIR="$ROOT/mydbflute/dbflute-$DBFLUTE_VERSION"
URL="https://github.com/dbflute/dbflute-core/releases/download/dbflute-${DBFLUTE_VERSION}/dbflute-${DBFLUTE_VERSION}.zip"

if [ -f "$ENGINE_DIR/etc/cmd/_df-manage.sh" ]; then
  echo "DBFlute engine already present: $ENGINE_DIR"
  exit 0
fi

echo "Downloading DBFlute engine $DBFLUTE_VERSION ..."
mkdir -p "$ENGINE_DIR"
tmp="$(mktemp -t dbflute-engine).zip"
curl -fsSL "$URL" -o "$tmp"
unzip -oq "$tmp" -d "$ENGINE_DIR"
rm -f "$tmp"
echo "Installed DBFlute engine into $ENGINE_DIR"
