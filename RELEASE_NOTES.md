# Release Notes: 1.0.0

**Status:** prepared, not yet tagged or published.

## Summary

Version 1.0.0 rebuilds the 2018 university coursework archive as a single, tested Java 17 codebase. The original files
remain available in git history.

## Highlights

- Domain packages with no console, clock or global random access.
- Numerically stable quadratic and triangle calculations.
- Exact decimal money handling.
- Seeded, reproducible simulations with property-based and chi-squared tests.
- A constrained SQL schema with parameterized queries.
- No third-party dependencies.

## Breaking changes

All paths, class names and identifiers changed. The C# Windows Forms projects were retired and their logic was ported to
Java. Several results differ from the original programs because defects were fixed; see
[docs/CORRECTIONS.md](docs/CORRECTIONS.md).

## Verification

Run `bash scripts/test.sh`. Results from the release candidate are in [reports/tests/SUMMARY.md](reports/tests/SUMMARY.md).

## Known limitations

- The schema has only been executed on SQLite.
- Line coverage is not measured.
- Git history still contains the retired files; see [docs/SECURITY_AUDIT.md](docs/SECURITY_AUDIT.md).
- No license has been declared.
