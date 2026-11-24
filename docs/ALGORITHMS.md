# Algorithms and Complexity

`n` is the input length, `k` the value range, and `b` the bit length.

| Algorithm | Location | Time | Extra space | Notes |
| --- | --- | --- | --- | --- |
| Linear search | `SearchAlgorithms.linearSearch` | O(n) | O(1) | Returns the first match |
| Binary search (lower bound) | `SearchAlgorithms.binarySearch` | O(n) with validation, O(log n) search | O(1) | Finds the first occurrence; midpoint cannot overflow |
| Histogram | `Histogram.count` | O(n + k) | O(k) | Rejects out-of-range values |
| Chi-squared statistic | `Histogram.chiSquaredAgainstUniform` | O(k) | O(1) | Goodness of fit against uniform |
| Age mode and mean | `AgeStatistics.of` | O(n + k) | O(k) | Counting approach, k = 16 |
| Partial Fisher-Yates | `Shuffling.partialFisherYates` | O(c) | O(1) | Unbiased selection of `c` items |
| Bingo card | `BingoCard.generate` | O(m + c log c) | O(m) | `m` = 100, `c` = 15 |
| Matrix row and column sums | `Matrices` | O(s^2) | O(s) | `s` is the side length |
| Sum of first evens | `Arithmetic.sumOfFirstEvensClosedForm` | O(1) | O(1) | Uses `n(n + 1)` |
| Base conversion | `NumberBases` | O(b) | O(b) | Limited to 63 bits |
| Quadratic roots | `QuadraticEquation.solve` | O(1) | O(1) | Stable form below |
| Triangle area | `Triangle.area` | O(1) | O(1) | Kahan's formula below |

## Numerically stable quadratic roots

The textbook formula subtracts nearly equal values when `b^2` is much larger than `4ac`, losing the small root. The solver
computes the larger-magnitude root first:

```text
q  = -(b + sign(b) * sqrt(b^2 - 4ac)) / 2
r1 = q / a
r2 = c / q
```

For `x^2 - 1e8 x + 1 = 0`, a test confirms the small root is recovered close to `1e-8`.

The discriminant is still computed directly in double precision, so inputs where `b^2` and `4ac` nearly cancel can be
classified with rounding error. This is a known limit.

## Stable triangle area

With sides sorted so that `a >= b >= c`, Kahan's arrangement of Heron's formula keeps precision on needle-shaped
triangles:

```text
area = sqrt((a + (b + c)) * (c - (a - b)) * (c + (a - b)) * (a + (b - c))) / 4
```

## Unbiased shuffling

For each position `i`, Fisher-Yates swaps with a uniformly chosen index in `[i, n)`. Each of the `n!` orderings is equally
likely, assuming an ideal generator.

## Exact money allocation

`ExpenseSplit` works in integer cents. The first `total mod n` participants carry one extra cent, so balances always sum to
exactly zero.

## Reproducibility and statistical testing

Randomized code takes a `RandomGenerator`, and tests and CLI commands use `SplittableRandom` with an explicit seed. Because
seeds are fixed, the statistical tests are deterministic and cannot flake. They are regression checks at alpha 0.001 and
are not proof of randomness quality.

| Test | Degrees of freedom | Critical value (alpha 0.001) |
| --- | ---: | ---: |
| Generator uniformity over 10 values | 9 | 27.877 |
| Permutations of 4 elements | 23 | 49.728 |
| Bingo number frequency | 99 | 148.230 |

The bingo frequency test is an approximation: numbers on a card are drawn without replacement, so cell counts are slightly
dependent.

`SplittableRandom` is not cryptographically secure and must not be used for security decisions.
