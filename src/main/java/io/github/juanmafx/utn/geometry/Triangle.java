package io.github.juanmafx.utn.geometry;

import java.util.Arrays;

public record Triangle(double sideA, double sideB, double sideC) {
    public Triangle {
        double[] sides = sorted(sideA, sideB, sideC);
        if (!Double.isFinite(sides[2]) || sides[0] <= 0 || sides[0] + sides[1] <= sides[2]) {
            throw new IllegalArgumentException("sides do not form a triangle");
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
        double c = sides[0];
        double b = sides[1];
        double a = sides[2];
        return Math.sqrt((a + (b + c)) * (c - (a - b)) * (c + (a - b)) * (a + (b - c))) / 4;
    }

    private static double[] sorted(double first, double second, double third) {
        double[] sides = {first, second, third};
        Arrays.sort(sides);
        return sides;
    }
}
