package io.github.juanmafx.utn.geometry;

import java.util.Arrays;

public record Triangle(double sideA, double sideB, double sideC) {
    public Triangle {
        double[] sides = sorted(sideA, sideB, sideC);
        if (!Double.isFinite(sides[2]) || sides[0] <= 0 || sides[0] + sides[1] <= sides[2]) {
            throw new IllegalArgumentException("sides do not form a triangle");
        }
        if (!Double.isFinite(sides[0] + sides[1] + sides[2])) {
            throw new IllegalArgumentException("perimeter is outside the double range");
        }
    }

    public double perimeter() {
        return sideA + sideB + sideC;
    }

    public double shortestSide() {
        return Math.min(sideA, Math.min(sideB, sideC));
    }

    public double area() {
        double[] sides = sorted(sideA, sideB, sideC);
        int exponent = Math.getExponent(sides[2]);
        double c = Math.scalb(sides[0], -exponent);
        double b = Math.scalb(sides[1], -exponent);
        double a = Math.scalb(sides[2], -exponent);
        double scaledArea = Math.sqrt((a + (b + c)) * (c - (a - b)) * (c + (a - b)) * (a + (b - c))) / 4;
        double area = Math.scalb(scaledArea, 2 * exponent);
        if (!Double.isFinite(area)) {
            throw new ArithmeticException("area is outside the double range");
        }
        return area;
    }

    private static double[] sorted(double first, double second, double third) {
        double[] sides = {first, second, third};
        Arrays.sort(sides);
        return sides;
    }
}
