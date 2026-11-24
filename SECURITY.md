# Security Policy

## Supported versions

| Version | Supported |
| --- | --- |
| 1.x | Yes |
| Earlier unversioned coursework | No |

## Reporting a vulnerability

Please report privately through the repository's GitHub **Security** tab. If private reporting is unavailable, open an
issue asking for a contact channel and do not include vulnerability details in it.

This is a personal study project maintained on a best-effort basis, so no response time is guaranteed.

## Security posture

- No runtime or build dependencies beyond the JDK and the Python standard library.
- No network access, authentication, stored credentials or secrets.
- Input validated at domain boundaries.
- SQL identifiers restricted to an allowlist, and SQL values bound as parameters.
- CI runs with read-only repository permissions and actions pinned to commit hashes.
- `SplittableRandom` is used for reproducibility only and must never be used for security purposes.

See [docs/SECURITY_AUDIT.md](docs/SECURITY_AUDIT.md) for the audit.
