package io.github.juanmafx.utn.arithmetic;

import java.util.stream.IntStream;

public final class Arithmetic {
    private Arithmetic() {
    }

    public static long sumThenHalveOrCube(long first, long second, long third) {
        long sum = Math.addExact(Math.addExact(first, second), third);
        return sum > 10 ? sum / 2 : Math.multiplyExact(Math.multiplyExact(sum, sum), sum);
    }

    public static int[] incrementOperatorTrace() {
        int value = 2;
        int[] trace = new int[5];
        value++;
        trace[0] = value;
        ++value;
        trace[1] = value;
        trace[2] = ++value;
        trace[3] = value++;
        trace[4] = value;
        return trace;
    }

    public static double estimatedRangeKilometers(double meters, double kilometersPerMeter) {
        if (meters < 0 || kilometersPerMeter < 0) {
            throw new IllegalArgumentException("inputs must be non-negative");
        }
        return meters * kilometersPerMeter;
    }

    public static int[] firstEvenNumbers(int count) {
        requireNonNegative(count);
        return IntStream.rangeClosed(1, count).map(index -> Math.multiplyExact(index, 2)).toArray();
    }

    public static long sumOfFirstEvensClosedForm(int count) {
        requireNonNegative(count);
        return (long) count * (count + 1L);
    }

    public static long sumOfFirstEvensForLoop(int count) {
        requireNonNegative(count);
        long sum = 0;
        for (long index = 1; index <= count; index++) {
            sum += 2L * index;
        }
        return sum;
    }

    public static long sumOfFirstEvensWhileLoop(int count) {
        requireNonNegative(count);
        long sum = 0;
        long index = 1;
        while (index <= count) {
            sum += 2L * index;
            index++;
        }
        return sum;
    }

    public static long sumOfFirstEvensDoWhileLoop(int count) {
        requireNonNegative(count);
        if (count == 0) {
            return 0;
        }
        long sum = 0;
        long index = 1;
        do {
            sum += 2L * index;
            index++;
        } while (index <= count);
        return sum;
    }

    private static void requireNonNegative(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count must be non-negative");
        }
    }
}
