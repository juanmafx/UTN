package io.github.juanmafx.utn.collections;

public record AgeStatistics(double mean, int aboveMean, int mode) {
    public static final int MINIMUM_AGE = 17;
    public static final int MAXIMUM_AGE = 32;

    public static AgeStatistics of(int... ages) {
        if (ages.length == 0) {
            throw new IllegalArgumentException("at least one age is required");
        }
        int[] counts = new int[MAXIMUM_AGE - MINIMUM_AGE + 1];
        long sum = 0;
        for (int age : ages) {
            if (age < MINIMUM_AGE || age > MAXIMUM_AGE) {
                throw new IllegalArgumentException("age must be between 17 and 32");
            }
            counts[age - MINIMUM_AGE]++;
            sum += age;
        }
        double mean = (double) sum / ages.length;
        int aboveMean = 0;
        int modeIndex = 0;
        for (int index = 0; index < counts.length; index++) {
            if (index + MINIMUM_AGE > mean) {
                aboveMean += counts[index];
            }
            if (counts[index] > counts[modeIndex]) {
                modeIndex = index;
            }
        }
        return new AgeStatistics(mean, aboveMean, modeIndex + MINIMUM_AGE);
    }
}
