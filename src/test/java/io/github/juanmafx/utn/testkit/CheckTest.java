package io.github.juanmafx.utn.testkit;

public class CheckTest {
    @Test
    void nearFailsWhenActualIsNaN() {
        Check.throwsType(AssertionError.class, () -> Check.near(1, Double.NaN, 1));
        Check.throwsType(AssertionError.class, () -> Check.near(Double.NaN, 1, 1));
    }

    @Test
    void nearFailsOutsideTolerance() {
        Check.throwsType(AssertionError.class, () -> Check.near(1, 1.6, 0.5));
    }

    @Test
    void nearAcceptsValuesWithinToleranceAndEqualInfinities() {
        Check.near(1, 1.5, 0.5);
        Check.near(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }
}
