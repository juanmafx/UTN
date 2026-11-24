# Security Audit

**Date:** 2026-11-23. **Scope:** the full working tree and a pattern scan of all git history. This was a manual review
supported by scripted searches. It was not an independent or professional penetration test.

## Summary

| Status | Count |
| --- | ---: |
| Fixed in the tree | 8 |
| Open, requires an owner decision | 2 |
| Accepted limitation | 3 |

## Fixed

| ID | Severity | Finding | Resolution |
| --- | --- | --- | --- |
| F-01 | High | SQL text was built by string concatenation in the data access helper | Replaced by an identifier allowlist and bound parameters |
| F-02 | High | Dozens of compiled executables and debug symbols were tracked | Removed and ignored; they were never executed during this work |
| F-03 | High | The stack targeted .NET Framework 4.5.2, which is end of life | Retired and ported to Java 17 |
| F-04 | Medium | IDE metadata exposed a local workstation account path | Removed from the tree and ignored |
| F-05 | Medium | A personal student identifier appeared in folder names and SQL identifiers | Removed from the tree |
| F-06 | Medium | Money used binary floating point | Replaced with `BigDecimal` |
| F-07 | Medium | Unvalidated input caused crashes and integer overflow | Validation at boundaries, checked arithmetic and bounded CLI input |
| F-08 | Low | CI and supply chain were undefined | Least-privilege workflow, pinned actions, no third-party dependencies |

## Open

| ID | Severity | Finding | Required decision |
| --- | --- | --- | --- |
| O-01 | Medium | Git history still contains the retired binaries, the workstation account path and the student identifier | Removing them requires a history rewrite and force-push, which only the owner can authorize |
| O-02 | Low | The repository declares no license, and course material may not be relicensable | Owner should choose a license for original code |

## Accepted limitations

| ID | Finding | Rationale |
| --- | --- | --- |
| A-01 | `SplittableRandom` is predictable | Reproducibility is the requirement and no security decision depends on it |
| A-02 | The custom test kit has no third-party review | Small, test-only code that keeps the dependency count at zero |
| A-03 | Schema constraints have only been verified on SQLite | The database is optional and unused at runtime |

## Secret scan

All commits were searched for private key headers, common cloud and GitHub token formats, and password assignments. There
were **no matches**. Pattern scanning cannot prove the absence of secrets.

No private key, certificate or key store file is tracked.

## Not applicable

The project has no web interface, sessions, authentication, file upload or network listener, so XSS, CSRF and session
controls do not apply. It uses no dynamic code evaluation and no unsafe deserialization.
