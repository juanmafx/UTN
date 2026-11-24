# Behavior Corrections

The 2026 rewrite follows the original problem statements. Where the original code disagreed with its statement, the
statement won. Each difference is listed here.

## Defects fixed

| Module | Original behavior | Current behavior |
| --- | --- | --- |
| `ShiftWage` | Day and night rates were swapped | Day 35.50, night 40.60 |
| `VehicleTax` | Hired cars paid 150 per year; age 10 matched two rules | 100 per year; each age matches one rule |
| `SupplierPayment` | Category B got 5%; only lowercase input matched; bounds were exclusive | 2%, case-insensitive, inclusive |
| `Grading` | Integer division truncated the mean; a mean of 4 fell through to promoted | Untruncated floating-point mean and correct bands |
| `Grading.band` | Labels were shifted by one band | Labels match the statement |
| `CohortSummary` | Accumulators mixed up; a mean of exactly 8 was uncounted | Every student counted once |
| `QuadraticEquation` | Wrong formula in one version; integer division; zero discriminant reported as complex | Stable, correct roots |
| `Point` | One version reported quadrant IV for quadrant III; axes were unhandled | All cases handled |
| `SearchAlgorithms` | Binary search compared the wrong index and its bounds were set before the length was read, so it never searched | Correct lower-bound search |
| `Matrices` | Anti-diagonal printed the main diagonal in one version | Correct anti-diagonal |
| `VectorStatistics` | Even mean divided by the count of all values; the zero terminator was counted | Correct mean and count |
| `AgeStatistics` | Ages 17 and 32 rejected; integer mean; mode missing | Inclusive range, untruncated mean, mode implemented |
| `WeatherStation` | "Below 20" tested above 20; ties broke extremes; report checked one reading | Correct comparisons |
| `Applicants` | Ties produced no winner | All tied applicants are reported |
| `RevenueShare` | Ties produced no result | All tied products are reported |
| `ArtGallery` | Typo compared a year with 200; century boundary was 2000 | Boundary is 1900 as stated |
| `WorkpieceInspection` | Mean divided by all pieces | Mean of accepted pieces |
| `BodyMassIndex` | Asked for millimeters, printed several categories, never recomputed the index inside its loop | Meters, exactly one category |
| `Calculator` | Only addition produced a result | All four operations, with division-by-zero handling |
| `LeagueAverage` | Total matches counted losses twice | Won + drawn + lost |
| `LibraryMember`, `Student` | Constructors discarded their arguments | Fields are stored and validated |
| `Line` | Default slope was 1 although the statement says 0 | Default slope is 0 |
| `BingoCard` | Generated 1 to 40 and never read the player's picks | 15 distinct numbers from 1 to 100, with matching |

## Interpretations

These are judgment calls where the source was ambiguous.

| Module | Decision |
| --- | --- |
| `Grading.classify` | A mean of exactly 8 is promoted. One statement says "greater than 8", another says "at least 8". |
| `Grading.band` | A grade of exactly 8 is "very good", and only grades above 8 are "excellent". |
| `LeagueAverage` | The original weight of 2 points per draw is retained because no statement defines it. |
| `Matrices.leftHalfSum` | The statement text is garbled; it is read as the columns left of center. |
| `Account.TypeCode` | Codes `I` and `T` are retained because the original never defined them. |
| `TransitCard` | A negative top-up is clamped to zero. |
| `BodyMassIndex` | The course thresholds are retained. They differ from World Health Organization categories. |

## Additions

`Account.deposit`, `Account.withdraw`, `Elevator` (the original was only a statement), `ClassQuota`, `Triangle` and the
weather station matrix report implement statements that had no working code.

## Removed

The Windows Forms screens, OLE DB helper and .NET Framework projects were not carried over. Their logic was ported to
Java, and the originals remain in git history.
