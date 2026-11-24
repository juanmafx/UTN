package io.github.juanmafx.utn.collections;

import java.math.BigInteger;
import java.util.Arrays;

public record VectorStatistics(
        int count,
        int positives,
        boolean containsFifteen,
        double evenMean,
        int maximum,
        int maximumOccurrences,
        int minimum,
        long sum,
        BigInteger product,
        double mean,
        int[] aboveMean,
        int[] belowMean) {

    public static int[] untilZero(int... values) {
        int end = 0;
        while (end < values.length && values[end] != 0) {
            end++;
        }
        return Arrays.copyOf(values, end);
    }

    public static VectorStatistics of(int... values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("at least one value is required");
        }
        int positives = 0;
        int evenCount = 0;
        long evenSum = 0;
        long sum = 0;
        boolean containsFifteen = false;
        int maximum = values[0];
        int minimum = values[0];
        int maximumOccurrences = 0;
        BigInteger product = BigInteger.ONE;
        for (int value : values) {
            positives += value > 0 ? 1 : 0;
            containsFifteen |= value == 15;
            if ((value & 1) == 0) {
                evenCount++;
                evenSum += value;
            }
            sum += value;
            product = product.multiply(BigInteger.valueOf(value));
            minimum = Math.min(minimum, value);
            if (value > maximum) {
                maximum = value;
                maximumOccurrences = 1;
            } else if (value == maximum) {
                maximumOccurrences++;
            }
        }
        double mean = (double) sum / values.length;
        double evenMean = evenCount == 0 ? Double.NaN : (double) evenSum / evenCount;
        int[] aboveMean = Arrays.stream(values).filter(value -> value > mean).toArray();
        int[] belowMean = Arrays.stream(values).filter(value -> value < mean).toArray();
        return new VectorStatistics(values.length, positives, containsFifteen, evenMean, maximum, maximumOccurrences,
                minimum, sum, product, mean, aboveMean, belowMean);
    }
}
