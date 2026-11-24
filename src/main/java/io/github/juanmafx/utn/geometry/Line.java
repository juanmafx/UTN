package io.github.juanmafx.utn.geometry;

import java.util.Locale;
import java.util.Objects;

public record Line(double slope, Point anchor) {
    private static final double TOLERANCE = 1e-9;

    public Line {
        Objects.requireNonNull(anchor);
        if (!Double.isFinite(slope)) {
            throw new IllegalArgumentException("slope must be finite");
        }
    }

    public static Line horizontalThroughOrigin() {
        return new Line(0, Point.ORIGIN);
    }

    public double yIntercept() {
        return anchor.y() - slope * anchor.x();
    }

    public boolean contains(Point point) {
        double expected = slope * point.x() + yIntercept();
        double scale = Math.max(1, Math.max(Math.abs(expected), Math.abs(point.y())));
        return Math.abs(expected - point.y()) <= TOLERANCE * scale;
    }

    public String equation() {
        double intercept = yIntercept();
        String sign = intercept < 0 ? "- " : "+ ";
        return String.format(Locale.ROOT, "y = %sx %s%s", slope, sign, Math.abs(intercept));
    }
}
