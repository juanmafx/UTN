#!/usr/bin/env bash
set -euo pipefail

root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
build="${UTN_BUILD_DIR:-$root/build}"

mkdir -p "$build/classes"
find "$root/src/main/java" -name '*.java' > "$build/main-sources.txt"
javac --release 17 -d "$build/classes" "@$build/main-sources.txt"
exec java -cp "$build/classes" io.github.juanmafx.utn.cli.Main "$@"
