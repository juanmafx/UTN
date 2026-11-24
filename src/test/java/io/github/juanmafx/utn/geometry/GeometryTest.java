package io.github.juanmafx.utn.geometry;

import io.github.juanmafx.utn.geometry.QuadraticEquation.ComplexPair;
import io.github.juanmafx.utn.geometry.QuadraticEquation.OneReal;
import io.github.juanmafx.utn.geometry.QuadraticEquation.Roots;
import io.github.juanmafx.utn.geometry.QuadraticEquation.TwoReal;
import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;

public class GeometryTest {
    @Test
    void quadrantsAndAxes() {
        Check.equal(Point.Quadrant.I, new Point(1, 1).quadrant());
        Check.equal(Point.Quadrant.II, new Point(-1, 1).quadrant());
        Check.equal(Point.Quadrant.III, new Point(-1, -1).quadrant());
        Check.equal(Point.Quadrant.IV, new Point(1, -1).quadrant());
        Check.equal(Point.Quadrant.ON_AXIS, new Point(0, 3).quadrant());
    }

    @Test
    void distanceDoesNotOverflow() {
        Check.near(5e300, new Point(3e300, 4e300).distanceToOrigin(), 1e287);
    }

    @Test
    void pointFormat() {
        Check.equal("(1.5;-2.0)", new Point(1.5, -2).format());
    }

    @Test
    void defaultLineIsHorizontal() {
        Line line = Line.horizontalThroughOrigin();
        Check.near(0, line.slope(), 0);
        Check.isTrue(line.contains(new Point(5, 0)), "on line");
    }

    @Test
    void lineInterceptAndEquation() {
        Line line = new Line(2, new Point(1, 1));
        Check.near(-1, line.yIntercept(), 1e-12);
        Check.equal("y = 2.0x - 1.0", line.equation());
        Check.isTrue(line.contains(new Point(3, 5)), "contains");
        Check.isTrue(!line.contains(new Point(3, 6)), "excludes");
    }

    @Test
    void generatedLinePointsAreContained() {
        Check.forAll(601L, 3000, random -> new double[] {random.nextDouble(-100, 100), random.nextDouble(-100, 100),
                random.nextDouble(-100, 100), random.nextDouble(-100, 100)}, s -> {
            Line line = new Line(s[0], new Point(s[1], s[2]));
            Check.isTrue(line.contains(new Point(s[3], s[0] * s[3] + line.yIntercept())), "point on line");
        });
    }

    @Test
    void quadraticTwoRealRoots() {
        Check.equal(new TwoReal(2, 3), QuadraticEquation.solve(1, -5, 6));
    }

    @Test
    void quadraticOneRoot() {
        Check.equal(new OneReal(-1), QuadraticEquation.solve(1, 2, 1));
    }

    @Test
    void quadraticComplexRoots() {
        Check.equal(new ComplexPair(0, 1), QuadraticEquation.solve(1, 0, 1));
    }

    @Test
    void quadraticIsStableUnderCancellation() {
        TwoReal roots = (TwoReal) QuadraticEquation.solve(1, -1e8, 1);
        Check.near(1e-8, roots.smaller(), 1e-20);
        Check.near(1e8, roots.larger(), 1e-6);
    }

    @Test
    void quadraticRejectsDegenerateInput() {
        Check.throwsType(IllegalArgumentException.class, () -> QuadraticEquation.solve(0, 1, 1));
        Check.throwsType(IllegalArgumentException.class, () -> QuadraticEquation.solve(1, Double.NaN, 1));
    }

    @Test
    void quadraticRecoversConstructedRoots() {
        Check.forAll(602L, 5000, random -> new int[] {random.nextInt(-200, 200), random.nextInt(-200, 200)}, r -> {
            Roots roots = QuadraticEquation.solve(1, -(r[0] + r[1]), (double) r[0] * r[1]);
            if (r[0] == r[1]) {
                Check.near(r[0], ((OneReal) roots).root(), 1e-9);
            } else {
                TwoReal two = (TwoReal) roots;
                Check.near(Math.min(r[0], r[1]), two.smaller(), 1e-9);
                Check.near(Math.max(r[0], r[1]), two.larger(), 1e-9);
            }
        });
    }

    @Test
    void triangleMeasures() {
        Triangle triangle = new Triangle(3, 4, 5);
        Check.near(6, triangle.area(), 1e-12);
        Check.near(12, triangle.perimeter(), 1e-12);
        Check.near(3, triangle.shortestSide(), 0);
    }

    @Test
    void invalidTrianglesAreRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> new Triangle(1, 2, 3));
        Check.throwsType(IllegalArgumentException.class, () -> new Triangle(-1, 2, 2));
        Check.throwsType(IllegalArgumentException.class, () -> new Triangle(Double.NaN, 2, 2));
    }

    @Test
    void needleTriangleKeepsPrecision() {
        Check.near(0.5, new Triangle(1e8, 1e8, 1e-8).area(), 1e-6);
    }

    @Test
    void triangleAreaIsPermutationInvariant() {
        Check.forAll(603L, 2000, random -> {
            double a = random.nextDouble(1, 100);
            double b = random.nextDouble(1, 100);
            double c = random.nextDouble(Math.abs(a - b) + 1e-3, a + b - 1e-3);
            return new double[] {a, b, c};
        }, s -> {
            double area = new Triangle(s[0], s[1], s[2]).area();
            Check.near(area, new Triangle(s[2], s[0], s[1]).area(), 1e-9);
            Check.isTrue(area > 0, "positive area");
        });
    }

    @Test
    void quadraticHandlesExtremeMagnitudes() {
        Check.isTrue(QuadraticEquation.solve(1e308, 1e308, 1e308) instanceof ComplexPair, "huge coefficients");
        Check.isTrue(QuadraticEquation.solve(1e-200, 1e-200, 1e-200) instanceof ComplexPair, "tiny coefficients");
        TwoReal roots = (TwoReal) QuadraticEquation.solve(1, 1e200, 1);
        Check.near(-1e200, roots.smaller(), 1e186);
        Check.near(-1e-200, roots.larger(), 1e-214);
    }

    @Test
    void quadraticNeverReportsNegativeZero() {
        Check.equal(new TwoReal(-5.0, 0.0), QuadraticEquation.solve(1, 5, 0));
    }

    @Test
    void quadraticRejectsRootsOutsideDoubleRange() {
        Check.throwsType(ArithmeticException.class, () -> QuadraticEquation.solve(1e-300, 1e300, 1));
    }

    @Test
    void triangleRejectsOverflowingPerimeter() {
        Check.throwsType(IllegalArgumentException.class, () -> new Triangle(1e308, 1e308, 1e308));
    }

    @Test
    void triangleAreaAvoidsIntermediateOverflow() {
        Check.near(Math.sqrt(3) / 4, new Triangle(1e153, 1e153, 1e153).area() / 1e306, 1e-12);
        Check.throwsType(ArithmeticException.class, () -> new Triangle(1e200, 1e200, 1e200).area());
    }
}
