#!/usr/bin/env bash
set -euo pipefail

root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
build="${UTN_BUILD_DIR:-$root/build}"
reports="$root/reports/tests"

rm -rf "$build/classes" "$build/test-classes"
rm -f "$reports/java-tests.xml" "$reports/database-tests.json" "$reports/SUMMARY.md"
mkdir -p "$build/classes" "$build/test-classes" "$reports"

find "$root/src/main/java" -name '*.java' > "$build/main-sources.txt"
find "$root/src/test/java" -name '*.java' > "$build/test-sources.txt"

javac --release 17 -Xlint:all -Werror -d "$build/classes" "@$build/main-sources.txt"
javac --release 17 -Xlint:all -Werror -cp "$build/classes" -d "$build/test-classes" "@$build/test-sources.txt"

java_status=0
java -ea -cp "$build/classes:$build/test-classes" io.github.juanmafx.utn.testkit.TestRunner \
    "$build/test-classes" "$reports/java-tests.xml" || java_status=$?

database_status=0
python3 -I "$root/db/tests/test_schema.py" "$reports/database-tests.json" || database_status=$?

if [ ! -s "$reports/java-tests.xml" ] || [ ! -s "$reports/database-tests.json" ]; then
    echo "a test runner did not produce its report; no summary was written" >&2
    exit 1
fi

python3 -I "$root/scripts/write_test_summary.py" "$reports" "$(java -version 2>&1 | head -n 1)"

if [ "$java_status" -ne 0 ] || [ "$database_status" -ne 0 ]; then
    exit 1
fi
