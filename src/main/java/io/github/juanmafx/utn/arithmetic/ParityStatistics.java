package io.github.juanmafx.utn.arithmetic;

public record ParityStatistics(int evens, int odds, int total, long sum) {
    public static ParityStatistics of(int... values) {
        int evens = 0;
        long sum = 0;
        for (int value : values) {
            if ((value & 1) == 0) {
                evens++;
            }
            sum += value;
        }
        return new ParityStatistics(evens, values.length - evens, values.length, sum);
    }
}
