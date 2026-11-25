# java-coursework-library

A tested Java library and command-line tool that rebuilds a collection of university programming coursework as one
coherent, dependency-free codebase.

> **Project history:** originally created in 2018 as coursework, modernized in 2026. The modern code in this tree was
> written in 2026. The published git history begins with the 2026 modernization; the original files are not part of it.

## Purpose

This repository started as the exercise archive from two study tracks in Cordoba, Argentina: the Programming Technician
degree at the National Technological University (UTN, Cordoba Regional Faculty) and the national **111Mil** Java
programming program. It now expresses those same exercises as correct, testable modules.

## Why this project exists

The original archive held 157 Java, C# and SQL files, 24 of them IDE-generated C#. It included 21 byte-identical copies
of Java files, 28 of its 115 Java files did not compile when checked with JDK 17, and much of its logic disagreed with
its own problem statements. Rebuilding it serves as a worked example of how to take legacy code and:

- separate domain logic from console input and output,
- turn informal statements into explicit, enforced rules,
- replace hand-checked results with property-based and statistical tests,
- document every behavior that changed, and why, in [docs/CORRECTIONS.md](docs/CORRECTIONS.md).

This is a study project. It has no production deployment and no users beyond its author.

## What it does

| Package | Capabilities |
| --- | --- |
| `arithmetic` | Calculator, duration and digit-place conversion, binary conversion, parity and extremes |
| `collections` | Linear and binary search, histograms, chi-squared statistic, matrix and vector statistics |
| `commerce` | Vehicle tax, wages, supplier discounts, revenue share, expense splitting, billing, quality control |
| `education` | Grade standing, cohort summaries, applicant ranking, class quotas, form state machine |
| `geometry` | Points, lines, numerically stable quadratic solver, stable triangle area |
| `health` | Body mass index using the course's thresholds; **not medical advice** |
| `simulation` | Seeded bingo cards, Fisher-Yates shuffling, elevator model, weather station statistics |
| `sports` | Competition results and league averages |
| `banking` | Account with exact decimal balance and overdraft protection |
| `records` | Person, student, employee and library member models |
| `persistence` | SQL identifier allowlist and parameter binding |
| `cli` | Command-line entry point for the most useful operations |

## Architecture

```text
src/main/java/io/github/juanmafx/utn/
  cli/            input parsing and exit codes
  <domain>/       pure domain packages with no console or file access
  persistence/    SQL safety helpers
src/test/java/    test suites and a small dependency-free test kit
db/               schema, queries and schema tests
scripts/          build, run and test entry points
reports/tests/    committed results of a real test run
docs/             architecture, algorithms, corrections, attribution, audit
```

There is no client/server split because the project has no server. Details are in
[docs/ARCHITECTURE.md](docs/ARCHITECTURE.md), and complexity notes are in [docs/ALGORITHMS.md](docs/ALGORITHMS.md).

## Quick start

Requirements: JDK 17, Python 3 and Bash (tested with OpenJDK 17 and Python 3.14). There are no third-party dependencies to install.

```bash
git clone https://github.com/juanmafx/UTN.git
cd java-coursework-library
bash scripts/test.sh
bash scripts/run.sh list
```

## Usage guide

```bash
bash scripts/run.sh duration 4452
bash scripts/run.sh quadratic 1 -5 6
bash scripts/run.sh vehicle-tax taxi 2020 2026
bash scripts/run.sh supplier-payment B 2000
bash scripts/run.sh standing 7 8 9
bash scripts/run.sh search 5 1 3 5 7
bash scripts/run.sh matrix 42 5 100
bash scripts/run.sh bingo 42 7 21 63
```

Commands that use randomness take an explicit seed, so the same arguments produce the same output on a given JDK.
Each command takes exactly the arguments shown by `list`; only `search` accepts a variable number of values. Invalid
input, including extra arguments, non-finite numbers, out-of-range bingo picks and results outside the `double` range,
prints a message to standard error and exits with status `2`.

## Configuration

| Variable | Default | Purpose |
| --- | --- | --- |
| `UTN_BUILD_DIR` | `build/` | Output directory for compiled classes |

The application reads no other configuration and needs no secrets.

## Database

[db/schema.sql](db/schema.sql) contains real `CREATE` statements for the course domain: people, students, employees,
library members, accounts, products, vehicles and a championship model. [db/queries.sql](db/queries.sql) holds
parameterized versions of the original practice queries.

The database is **optional**: the library and CLI do not use it. Load it with:

```bash
sqlite3 utn.db < db/schema.sql
```

The schema uses a portable SQL subset, but it has only been executed on SQLite. SQLite enforces the foreign keys only
on connections that run `PRAGMA foreign_keys = ON`, as the schema tests do, and it does not enforce `VARCHAR` lengths or
`NUMERIC` precision.

## Testing

```bash
bash scripts/test.sh
```

The script compiles with all lint warnings treated as errors, runs the Java and database suites, and rewrites the report
in [reports/tests/SUMMARY.md](reports/tests/SUMMARY.md). It deletes the previous reports first, so a runner that crashes
leaves no stale summary behind. Property-based tests use fixed seeds and report the failing seed
and sample. Statistical tests compare chi-squared statistics against critical values at alpha 0.001. Line coverage is not
measured.

## Security notes

The code validates input at its boundaries, rejects non-finite numbers, uses exact decimals for money and rejects
fractions of a cent in account operations, and binds SQL parameters. It has no network
access and no runtime dependencies. See [SECURITY.md](SECURITY.md) and [docs/SECURITY_AUDIT.md](docs/SECURITY_AUDIT.md).

## Attribution and license

The exercise statements come from course material and are not the author's original work. Credits are in
[docs/ATTRIBUTION.md](docs/ATTRIBUTION.md), and [docs/PROVENANCE.md](docs/PROVENANCE.md) maps modern modules to original
files. The repository has never declared a license, so none is granted here.
