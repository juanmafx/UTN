package io.github.juanmafx.utn.collections;

public final class Histogram {
    private Histogram() {
    }

    public static int[] count(int[] values, int exclusiveBound) {
        if (exclusiveBound <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        int[] counts = new int[exclusiveBound];
        for (int value : values) {
            if (value < 0 || value >= exclusiveBound) {
                throw new IllegalArgumentException("value outside histogram range");
            }
            counts[value]++;
        }
        return counts;
    }

    public static double chiSquaredAgainstUniform(int[] counts) {
        if (counts.length == 0) {
            throw new IllegalArgumentException("counts are required");
        }
        long total = 0;
        for (int count : counts) {
            total += count;
        }
        if (total == 0) {
            throw new IllegalArgumentException("at least one observation is required");
        }
        double expected = (double) total / counts.length;
        double statistic = 0;
        for (int count : counts) {
            double difference = count - expected;
            statistic += difference * difference / expected;
        }
        return statistic;
    }
}
