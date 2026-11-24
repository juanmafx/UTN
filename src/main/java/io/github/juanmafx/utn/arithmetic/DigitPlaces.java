package io.github.juanmafx.utn.arithmetic;

public record DigitPlaces(int hundreds, int tens, int units) {
    public DigitPlaces {
        if (hundreds < 0 || tens < 0 || tens > 9 || units < 0 || units > 9) {
            throw new IllegalArgumentException("digit places out of range");
        }
    }

    public static DigitPlaces of(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be non-negative");
        }
        return new DigitPlaces(value / 100, value / 10 % 10, value % 10);
    }

    public int toInt() {
        return Math.addExact(Math.multiplyExact(hundreds, 100), tens * 10 + units);
    }
}
