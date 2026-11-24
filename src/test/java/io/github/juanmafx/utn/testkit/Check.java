package io.github.juanmafx.utn.testkit;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.SplittableRandom;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Check {
    private Check() {
    }

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void equal(Object expected, Object actual) {
        if (!Objects.deepEquals(expected, actual)) {
            throw new AssertionError("expected " + describe(expected) + " but was " + describe(actual));
        }
    }

    public static void money(String expected, BigDecimal actual) {
        if (new BigDecimal(expected).compareTo(actual) != 0) {
            throw new AssertionError("expected " + expected + " but was " + actual);
        }
    }

    public static void near(double expected, double actual, double tolerance) {
        if (Double.compare(expected, actual) != 0 && !(Math.abs(expected - actual) <= tolerance)) {
            throw new AssertionError("expected " + expected + " +/- " + tolerance + " but was " + actual);
        }
    }

    public static void throwsType(Class<? extends Throwable> type, Runnable action) {
        try {
            action.run();
        } catch (Throwable thrown) {
            if (type.isInstance(thrown)) {
                return;
            }
            throw new AssertionError("expected " + type.getSimpleName() + " but got " + thrown, thrown);
        }
        throw new AssertionError("expected " + type.getSimpleName() + " but nothing was thrown");
    }

    public static <T> void forAll(long seed, int trials, Function<SplittableRandom, T> generator, Consumer<T> property) {
        SplittableRandom random = new SplittableRandom(seed);
        for (int trial = 0; trial < trials; trial++) {
            T sample = generator.apply(random);
            try {
                property.accept(sample);
            } catch (RuntimeException | AssertionError failure) {
                throw new AssertionError("property failed at seed=" + seed + " trial=" + trial
                        + " sample=" + describe(sample), failure);
            }
        }
    }

    private static String describe(Object value) {
        if (value instanceof Object[] array) {
            return Arrays.deepToString(array);
        }
        if (value instanceof int[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof long[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof double[] array) {
            return Arrays.toString(array);
        }
        return String.valueOf(value);
    }
}
