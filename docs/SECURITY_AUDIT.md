# Security Audit

**Date:** 2026-11-23, re-audited 2026-11-24. **Scope:** the full working tree and all git history. This was a manual
review supported by scripted searches, edge-case probes and hand-written mutation checks. It was not a professional
penetration test.

## Summary

| Status | Count |
| --- | ---: |
| Fixed in the tree | 16 |
| Resolved by the 2026-11-24 history rewrite | 1 |
| Open, requires an owner decision | 1 |
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
| F-09 | Medium | The amount size guard used `int` arithmetic, so exponents near `Integer.MAX_VALUE` overflowed and bypassed it, and `Money.round` had no guard, so a huge exponent forced a very large `BigInteger` rescale | The guard uses `long` arithmetic and also runs inside `Money.round` |
| F-10 | Medium | Account deposits and withdrawals rounded sub-cent amounts, so 0.01 + 0.005 produced 0.02 | Amounts must be whole cents |
| F-11 | Medium | The three loop variants of the even-number sum never terminated for `Integer.MAX_VALUE` because the `int` counter overflowed | `long` counters |
| F-12 | Medium | The quadratic, triangle, body mass index and calculator code returned `NaN`, `Infinity` or `-0.0` for extreme input | Exact power-of-two scaling where it applies, and rejection of non-finite operands and results |
| F-13 | Low | Unknown enum options echoed fully qualified internal class names in error messages | Fixed messages such as `unknown vehicle kind` |
| F-14 | Low | The CLI ignored extra arguments, and bingo accepted picks outside 1 to 100 | Exact arity and range checks |
| F-15 | Low | `WeatherStation.readings()` returned its internal array and accepted `NaN` readings | Deep copies and finite-value validation |
| F-16 | Low | The test kit's `Check.near` passed when the actual value was `NaN`, and `scripts/test.sh` could rewrite the summary from a stale report after a runner crash | `NaN` fails the assertion, and old reports are deleted before each run |

## Resolved by history rewrite

| ID | Severity | Finding | Resolution |
| --- | --- | --- | --- |
| O-01 | Medium | Git history contained the retired binaries, the workstation account path and the student identifier | Resolved by the 2026-11-24 history rewrite: those commits are no longer in any branch or tag. Old commits may stay reachable by direct SHA on GitHub until unreferenced objects are purged, so anything that was ever published should still be treated as exposed |

## Open

| ID | Severity | Finding | Required decision |
| --- | --- | --- | --- |
| O-02 | Low | The repository declares no license, and course material may not be relicensable | Owner should choose a license for original code |

## Accepted limitations

| ID | Finding | Rationale |
| --- | --- | --- |
| A-01 | `SplittableRandom` is predictable | Reproducibility is the requirement and no security decision depends on it |
| A-02 | The custom test kit has no third-party review | Small, test-only code that keeps the dependency count at zero |
| A-03 | Schema constraints have only been verified on SQLite | The database is optional and unused at runtime |

## Secret scan

All commits were searched for private key headers, common cloud and GitHub token formats, and password assignments. There
were **no matches**. On 2026-11-24, gitleaks 8.30.1 with `--redact` also reported no findings on the working tree, on the
`modernize/2026` commits and on the full history. Pattern scanning cannot prove the absence of secrets.

No private key, certificate or key store file is tracked.

## Dependencies and CI

The project has no dependency manifest and no third-party libraries, so there is nothing for an ecosystem audit tool to
scan. The workflow requests `contents: read`, disables persisted checkout credentials, and pins each action to a commit
that was checked against its upstream release tag on 2026-11-24:

| Action | Release | Commit |
| --- | --- | --- |
| `actions/checkout` | v7.0.1 | `3d3c42e5aac5ba805825da76410c181273ba90b1` |
| `actions/setup-java` | v6.3.0 | `d1374f8ac74474200d16ef8ecee22c7ae7a51880` |
| `actions/setup-python` | v7.1.0 | `6d4fd22b20465ccc0f7354476588e8df7c9ca0e0` |

The JDK and Python patch versions installed by CI are not pinned.

## Not applicable

The project has no web interface, contact form, sessions, authentication, file upload or network listener, so XSS, CSRF,
session controls and two-factor authentication do not apply. It uses no cryptography, no dynamic code evaluation and no
unsafe deserialization.
