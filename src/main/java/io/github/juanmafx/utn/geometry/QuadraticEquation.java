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
        double discriminant = b * b - 4 * a * c;
        if (discriminant == 0) {
            return new OneReal(vertex(a, b));
        }
        if (discriminant < 0) {
            return new ComplexPair(vertex(a, b), Math.sqrt(-discriminant) / (2 * Math.abs(a)));
        }
        double root = Math.sqrt(discriminant);
        double stable = -(b + (b >= 0 ? root : -root)) / 2;
        double first = stable / a;
        double second = c / stable;
        return new TwoReal(Math.min(first, second), Math.max(first, second));
    }

    private static double vertex(double a, double b) {
        return -b / (2 * a) + 0.0;
    }
}
