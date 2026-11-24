package io.github.juanmafx.utn.collections;

import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.SplittableRandom;

public class CollectionsTest {
    @Test
    void binarySearchAgreesWithLinearOnSortedInput() {
        Check.forAll(301L, 3000, random -> {
            int[] values = random.ints(random.nextInt(0, 60), -30, 30).sorted().toArray();
            return new int[][] {values, {random.nextInt(-35, 35)}};
        }, sample -> {
            int[] values = sample[0];
            int target = sample[1][0];
            Check.equal(SearchAlgorithms.linearSearch(values, target), SearchAlgorithms.binarySearch(values, target));
        });
    }

    @Test
    void binarySearchRejectsUnsortedInput() {
        Check.throwsType(IllegalArgumentException.class, () -> SearchAlgorithms.binarySearch(new int[] {3, 1}, 1));
    }

    @Test
    void binarySearchHandlesExtremeValues() {
        int[] values = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        Check.equal(0, SearchAlgorithms.binarySearch(values, Integer.MIN_VALUE));
        Check.equal(2, SearchAlgorithms.binarySearch(values, Integer.MAX_VALUE));
        Check.equal(SearchAlgorithms.NOT_FOUND, SearchAlgorithms.binarySearch(values, 5));
    }

    @Test
    void allPositionsFindsEveryOccurrence() {
        Check.equal(new int[] {0, 2}, SearchAlgorithms.allPositions(new int[] {4, 1, 4}, 4));
    }

    @Test
    void histogramConservesCount() {
        Check.forAll(302L, 500, random -> random.ints(random.nextInt(0, 200), 0, 10).toArray(), values -> {
            int[] counts = Histogram.count(values, 10);
            Check.equal(values.length, Arrays.stream(counts).sum());
        });
    }

    @Test
    void histogramRejectsOutOfRange() {
        Check.throwsType(IllegalArgumentException.class, () -> Histogram.count(new int[] {10}, 10));
    }

    @Test
    void chiSquaredIsZeroForPerfectlyUniformCounts() {
        Check.near(0, Histogram.chiSquaredAgainstUniform(new int[] {5, 5, 5, 5}), 1e-12);
    }

    @Test
    void chiSquaredDetectsSkew() {
        Check.isTrue(Histogram.chiSquaredAgainstUniform(new int[] {100, 0, 0, 0}) > 16.266, "skew must exceed critical value");
    }

    @Test
    void seededGeneratorPassesUniformityAtAlphaPointZeroZeroOne() {
        int[] values = new SplittableRandom(303L).ints(100_000, 0, 10).toArray();
        Check.isTrue(Histogram.chiSquaredAgainstUniform(Histogram.count(values, 10)) < 27.877, "df=9 critical value");
    }

    @Test
    void matrixSumsAreConsistent() {
        Check.forAll(304L, 300, random -> Matrices.random(random.nextInt(1, 12), 100, random), matrix -> {
            long byRows = Arrays.stream(Matrices.rowSums(matrix)).sum();
            long byColumns = Arrays.stream(Matrices.columnSums(matrix)).sum();
            Check.equal(byRows, byColumns);
        });
    }

    @Test
    void randomMatrixIsSeedReproducible() {
        Check.equal(Matrices.random(5, 100, new SplittableRandom(9)), Matrices.random(5, 100, new SplittableRandom(9)));
    }

    @Test
    void sequentialEvensMatchesStatement() {
        Check.equal(new int[][] {{2, 4, 6, 8}, {10, 12, 14, 16}, {18, 20, 22, 24}, {26, 28, 30, 32}},
                Matrices.sequentialEvens(4));
    }

    @Test
    void diagonalsAndLeftHalf() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Check.equal(new int[] {1, 5, 9}, Matrices.mainDiagonal(matrix));
        Check.equal(new int[] {3, 5, 7}, Matrices.antiDiagonal(matrix));
        Check.equal(12L, Matrices.leftHalfSum(matrix));
    }

    @Test
    void nonSquareMatrixIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> Matrices.rowSums(new int[][] {{1, 2}}));
    }

    @Test
    void vectorStatisticsReport() {
        VectorStatistics statistics = VectorStatistics.of(VectorStatistics.untilZero(4, 15, -2, 15, 0, 99));
        Check.equal(4, statistics.count());
        Check.equal(3, statistics.positives());
        Check.isTrue(statistics.containsFifteen(), "fifteen");
        Check.near(1, statistics.evenMean(), 1e-12);
        Check.equal(15, statistics.maximum());
        Check.equal(2, statistics.maximumOccurrences());
        Check.equal(-2, statistics.minimum());
        Check.equal(32L, statistics.sum());
        Check.equal(BigInteger.valueOf(-1800), statistics.product());
    }

    @Test
    void vectorProductDoesNotOverflow() {
        int[] values = new int[20];
        Arrays.fill(values, Integer.MAX_VALUE);
        Check.equal(BigInteger.valueOf(Integer.MAX_VALUE).pow(20), VectorStatistics.of(values).product());
    }

    @Test
    void meanPartitionExcludesOnlyValuesEqualToMean() {
        Check.forAll(305L, 500, random -> random.ints(random.nextInt(1, 40), -100, 100).toArray(), values -> {
            VectorStatistics statistics = VectorStatistics.of(values);
            long equalToMean = Arrays.stream(values).filter(v -> v == statistics.mean()).count();
            Check.equal((long) values.length, statistics.aboveMean().length + statistics.belowMean().length + equalToMean);
        });
    }

    @Test
    void ageStatisticsIncludeBoundsAndMode() {
        AgeStatistics statistics = AgeStatistics.of(17, 20, 20, 32);
        Check.near(22.25, statistics.mean(), 1e-12);
        Check.equal(1, statistics.aboveMean());
        Check.equal(20, statistics.mode());
    }

    @Test
    void ageOutsideRangeIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> AgeStatistics.of(16));
        Check.throwsType(IllegalArgumentException.class, () -> AgeStatistics.of(33));
    }
}
