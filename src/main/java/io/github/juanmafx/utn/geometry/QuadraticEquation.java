package io.github.juanmafx.utn.geometry;

public final class QuadraticEquation {
    public sealed interface Roots permits TwoReal, OneReal, ComplexPair {
    }

    public record TwoReal(double smaller, double larger) implements Roots {
    }

    public record OneReal(double root) implements Roots {
    }

    public record ComplexPair(double real, double imaginary) implements Roots {
    }

    private QuadraticEquation() {
    }

    public static Roots solve(double a, double b, double c) {
        if (a == 0 || !Double.isFinite(a) || !Double.isFinite(b) || !Double.isFinite(c)) {
            throw new IllegalArgumentException("coefficients must be finite and a must be non-zero");
        }
        int exponent = Math.getExponent(Math.max(Math.abs(a), Math.max(Math.abs(b), Math.abs(c))));
        double scaledA = Math.scalb(a, -exponent);
        double scaledB = Math.scalb(b, -exponent);
        double scaledC = Math.scalb(c, -exponent);
        double discriminant = scaledB * scaledB - 4 * scaledA * scaledC;
        if (discriminant == 0) {
            return new OneReal(vertex(scaledA, scaledB));
        }
        if (discriminant < 0) {
            return new ComplexPair(vertex(scaledA, scaledB), finite(Math.sqrt(-discriminant) / (2 * Math.abs(scaledA))));
        }
        double root = Math.sqrt(discriminant);
        double stable = -(scaledB + (scaledB >= 0 ? root : -root)) / 2;
        double first = finite(stable / scaledA);
        double second = finite(scaledC / stable);
        return new TwoReal(Math.min(first, second), Math.max(first, second));
    }

    private static double vertex(double a, double b) {
        return finite(-b / (2 * a));
    }

    private static double finite(double value) {
        if (!Double.isFinite(value)) {
            throw new ArithmeticException("root is outside the double range");
        }
        return value + 0.0;
    }
}
