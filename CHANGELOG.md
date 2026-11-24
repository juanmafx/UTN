# Changelog

This project follows [Semantic Versioning](https://semver.org/).

## [1.0.0] - Unreleased

First versioned release. The repository had no earlier tags or versions.

### Changed

- **Breaking:** replaced the unstructured coursework tree with one Java 17 codebase organized by domain.
- **Breaking:** renamed all code, files, folders and user-facing text to English.
- Rewrote SQL exercises as a constrained schema and parameterized queries.

### Added

- Command-line interface with seeded, reproducible commands.
- Property-based and statistical test suites, plus a committed test report.
- Architecture, algorithm, correction, attribution, provenance and security documents.
- GitHub Actions workflow with read-only permissions and pinned actions.

### Fixed

- Logic defects listed in [docs/CORRECTIONS.md](docs/CORRECTIONS.md).

### Removed

- Tracked binaries, build output and IDE metadata.
- End-of-life .NET Framework Windows Forms projects and the OLE DB helper. Their logic was ported to Java.

### Security

- Replaced concatenated SQL with an identifier allowlist and bound parameters.
- Replaced floating-point money with exact decimals.
