# Architecture

## Goals

1. Keep domain rules pure, deterministic and independently testable.
2. Confine parsing, printing and process exit codes to one adapter.
3. Build and test with only a JDK and Python, so the supply chain stays empty.

## Module structure

```text
io.github.juanmafx.utn
|-- cli            Adapter: maps arguments to domain calls and errors to exit codes
|-- arithmetic     Calculator, ClockDuration, DigitPlaces, Extremes, NumberBases, ParityStatistics
|-- collections    SearchAlgorithms, Histogram, Matrices, VectorStatistics, AgeStatistics
|-- commerce       Money, VehicleTax, ShiftWage, SupplierPayment, RevenueShare, ExpenseSplit, and others
|-- education      Grading, CohortSummary, Applicants, ClassQuota, StudentFormState
|-- geometry       Point, Line, QuadraticEquation, Triangle
|-- health         BodyMassIndex
|-- simulation     BingoCard, Shuffling, Elevator, PlayingCard, WeatherStation
|-- sports         Competition, LeagueAverage
|-- banking        Account
|-- records        Person, Student, Employee, LibraryMember
`-- persistence    SafeQueries
```

## Dependency rules

```text
cli -> domain packages -> java.base
records -> education, commerce
banking -> commerce (Money)
persistence -> java.sql
```

Domain packages never read standard input, print, read the clock or use a global random source. Time arrives as a
parameter such as `currentYear`, and randomness arrives as a `RandomGenerator`.

## Design decisions

| Decision | Reason |
| --- | --- |
| Records for values | Input records validate in their constructors and copy mutable arrays; result records such as `CohortSummary` come from factory methods and do not re-validate their components |
| `BigDecimal` for money | Binary floating point cannot represent most cents exactly |
| Injected `RandomGenerator` | Runs are reproducible from a seed |
| Custom test kit | Keeps the build free of third-party code |
| Sealed `Roots` type | Callers handle each root shape explicitly |

## Frontend and backend

The project has no server, so there is no `frontend/` and `backend/` split. The original Windows Forms screens were
unfinished and held no business logic. Their one real behavior, button enablement while editing, is modeled by
`StudentFormState`, so a future user interface can reuse it.

## Recommended target architecture

If this code ever needs to serve users, keep the domain packages unchanged and add adapters around them:

```text
frontend/              Web client
backend/
  api/                 HTTP adapter with authentication and input validation
  application/         Use cases and transactions
  domain/              The existing pure packages
  infrastructure/      JDBC repositories built on SafeQueries
db/                    Versioned migrations derived from schema.sql
```

## Data model

```text
document_types 1--* people 1--0..1 students
                          1--0..1 employees
                          1--0..1 library_members
brands 1--* products *--1 product_types
championships 1--* match_days 1--* matches *--1 match_statuses
                                   matches *--2 teams
```
