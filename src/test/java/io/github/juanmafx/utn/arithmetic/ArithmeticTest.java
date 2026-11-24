package io.github.juanmafx.utn.arithmetic;

import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.util.Arrays;

public class ArithmeticTest {
    @Test
    void halvesSumAboveTen() {
        Check.equal(6L, Arithmetic.sumThenHalveOrCube(4, 4, 4));
    }

    @Test
    void cubesSumAtMostTen() {
        Check.equal(1000L, Arithmetic.sumThenHalveOrCube(5, 3, 2));
    }

    @Test
    void cubeOverflowIsRejected() {
        Check.throwsType(ArithmeticException.class, () -> Arithmetic.sumThenHalveOrCube(Long.MIN_VALUE / 2, 0, 0));
    }

    @Test
    void incrementTraceMatchesJavaSemantics() {
        Check.equal(new int[] {3, 4, 5, 5, 6}, Arithmetic.incrementOperatorTrace());
    }

    @Test
    void rangeEstimateMultiplies() {
        Check.near(12.5, Arithmetic.estimatedRangeKilometers(5, 2.5), 1e-12);
    }

    @Test
    void firstEvensStartAtTwo() {
        Check.equal(new int[] {2, 4, 6}, Arithmetic.firstEvenNumbers(3));
    }

    @Test
    void allEvenSumStrategiesAgree() {
        Check.forAll(101L, 500, random -> random.nextInt(0, 5000), count -> {
            long expected = Arrays.stream(Arithmetic.firstEvenNumbers(count)).asLongStream().sum();
            Check.equal(expected, Arithmetic.sumOfFirstEvensClosedForm(count));
            Check.equal(expected, Arithmetic.sumOfFirstEvensForLoop(count));
            Check.equal(expected, Arithmetic.sumOfFirstEvensWhileLoop(count));
            Check.equal(expected, Arithmetic.sumOfFirstEvensDoWhileLoop(count));
        });
    }

    @Test
    void negativeCountIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> Arithmetic.firstEvenNumbers(-1));
    }

    @Test
    void calculatorOperations() {
        Check.near(5, Calculator.ADD.apply(2, 3), 0);
        Check.near(-1, Calculator.SUBTRACT.apply(2, 3), 0);
        Check.near(6, Calculator.MULTIPLY.apply(2, 3), 0);
        Check.near(2, Calculator.parse(" divide ").apply(6, 3), 0);
    }

    @Test
    void calculatorRejectsDivisionByZero() {
        Check.throwsType(ArithmeticException.class, () -> Calculator.DIVIDE.apply(1, 0));
    }

    @Test
    void durationStatementExamples() {
        Check.equal("1:14:12", ClockDuration.fromTotalSeconds(4452).format());
        Check.equal("1:35:33", ClockDuration.fromTotalSeconds(5733).format());
        Check.equal("2:28:21", ClockDuration.fromTotalSeconds(8901).format());
        Check.equal("4:22:14", ClockDuration.fromTotalSeconds(15734).format());
        Check.equal("0:14:57", ClockDuration.fromTotalSeconds(897).format());
        Check.equal(16568L, new ClockDuration(4, 36, 8).toTotalSeconds());
    }

    @Test
    void durationRoundTrips() {
        Check.forAll(102L, 5000, random -> random.nextLong(0, 1L << 40),
                total -> Check.equal(total, ClockDuration.fromTotalSeconds(total).toTotalSeconds()));
    }

    @Test
    void digitPlacesRoundTrip() {
        Check.forAll(103L, 5000, random -> random.nextInt(0, 1_000_000),
                value -> Check.equal(value, DigitPlaces.of(value).toInt()));
    }

    @Test
    void binaryKnownValues() {
        Check.equal(10L, NumberBases.binaryToDecimal("1010"));
        Check.equal("1010", NumberBases.decimalToBinary(10));
        Check.equal("0", NumberBases.decimalToBinary(0));
        Check.equal(Long.MAX_VALUE, NumberBases.binaryToDecimal(Long.toBinaryString(Long.MAX_VALUE)));
    }

    @Test
    void binaryRejectsInvalidInput() {
        Check.throwsType(IllegalArgumentException.class, () -> NumberBases.binaryToDecimal("102"));
        Check.throwsType(IllegalArgumentException.class, () -> NumberBases.binaryToDecimal(""));
        Check.throwsType(ArithmeticException.class, () -> NumberBases.binaryToDecimal("1".repeat(64)));
    }

    @Test
    void binaryIgnoresLeadingZerosForWidth() {
        Check.equal(1L, NumberBases.binaryToDecimal("0".repeat(100) + "1"));
    }

    @Test
    void binaryRoundTripsAndMatchesJdk() {
        Check.forAll(104L, 5000, random -> random.nextLong(0, Long.MAX_VALUE), value -> {
            String binary = NumberBases.decimalToBinary(value);
            Check.equal(Long.toBinaryString(value), binary);
            Check.equal(value, NumberBases.binaryToDecimal(binary));
        });
    }

    @Test
    void parityPartitionsInput() {
        Check.forAll(105L, 500, random -> random.ints(random.nextInt(0, 50), -1000, 1000).toArray(), values -> {
            ParityStatistics statistics = ParityStatistics.of(values);
            Check.equal(values.length, statistics.evens() + statistics.odds());
            Check.equal(Arrays.stream(values).asLongStream().sum(), statistics.sum());
        });
    }

    @Test
    void parityHandlesNegativeOdds() {
        Check.equal(new ParityStatistics(1, 2, 3, -2), ParityStatistics.of(-3, 2, -1));
    }

    @Test
    void extremesBoundEveryValue() {
        Check.forAll(106L, 500, random -> random.doubles(random.nextInt(1, 40), -1e6, 1e6).toArray(), values -> {
            Extremes extremes = Extremes.of(values);
            Check.isTrue(Arrays.stream(values).allMatch(v -> v >= extremes.minimum() && v <= extremes.maximum()), "bounds");
            Check.isTrue(extremes.range() >= 0, "range");
        });
    }

    @Test
    void extremesRejectEmptyAndNaN() {
        Check.throwsType(IllegalArgumentException.class, Extremes::of);
        Check.throwsType(IllegalArgumentException.class, () -> Extremes.of(1, Double.NaN));
    }
}
