package io.github.juanmafx.utn.education;

public final class Grading {
    public enum Standing {
        NOT_ELIGIBLE, REGULAR, PROMOTED
    }

    public enum Band {
        FAILED, GOOD, VERY_GOOD, EXCELLENT
    }

    private static final double PASS_MARK = 4;
    private static final double PROMOTION_MARK = 8;
    private static final double VERY_GOOD_MARK = 6;
    private static final double MAXIMUM_GRADE = 10;

    private Grading() {
    }

    public static double mean(double first, double second, double third) {
        requireGrade(first);
        requireGrade(second);
        requireGrade(third);
        return (first + second + third) / 3;
    }

    public static Standing standing(double first, double second, double third) {
        return classify(mean(first, second, third));
    }

    public static Standing classify(double mean) {
        requireGrade(mean);
        if (mean < PASS_MARK) {
            return Standing.NOT_ELIGIBLE;
        }
        return mean < PROMOTION_MARK ? Standing.REGULAR : Standing.PROMOTED;
    }

    public static Band band(double grade) {
        requireGrade(grade);
        if (grade < PASS_MARK) {
            return Band.FAILED;
        }
        if (grade < VERY_GOOD_MARK) {
            return Band.GOOD;
        }
        return grade <= PROMOTION_MARK ? Band.VERY_GOOD : Band.EXCELLENT;
    }

    private static void requireGrade(double grade) {
        if (Double.isNaN(grade) || grade < 0 || grade > MAXIMUM_GRADE) {
            throw new IllegalArgumentException("grade must be between 0 and 10");
        }
    }
}
