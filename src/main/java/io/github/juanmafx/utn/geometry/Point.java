package io.github.juanmafx.utn.geometry;

import java.util.Locale;

public record Point(double x, double y) {
    public static final Point ORIGIN = new Point(0, 0);

    public enum Quadrant {
        I, II, III, IV, ON_AXIS
    }

    public Point {
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("coordinates must be finite");
        }
    }

    public double distanceToOrigin() {
        return Math.hypot(x, y);
    }

    public Quadrant quadrant() {
        if (x == 0 || y == 0) {
            return Quadrant.ON_AXIS;
        }
        if (x > 0) {
            return y > 0 ? Quadrant.I : Quadrant.IV;
        }
        return y > 0 ? Quadrant.II : Quadrant.III;
    }

    public String format() {
        return String.format(Locale.ROOT, "(%s;%s)", x, y);
    }
}
