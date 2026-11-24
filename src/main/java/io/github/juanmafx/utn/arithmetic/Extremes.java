package io.github.juanmafx.utn.arithmetic;

public record Extremes(double minimum, double maximum) {
    public static Extremes of(double... values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("at least one value is required");
        }
        double minimum = values[0];
        double maximum = values[0];
        for (double value : values) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("NaN is not comparable");
            }
            minimum = Math.min(minimum, value);
            maximum = Math.max(maximum, value);
        }
        return new Extremes(minimum, maximum);
    }

    public double range() {
        return maximum - minimum;
    }
}
