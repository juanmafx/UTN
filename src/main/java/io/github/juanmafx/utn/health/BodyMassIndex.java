package io.github.juanmafx.utn.health;

public record BodyMassIndex(double value, Category category) {
    public enum Category {
        SEVERE_THINNESS, UNDERWEIGHT, LOW_WEIGHT, HEALTHY, OVERWEIGHT_GRADE_I, OBESITY_GRADE_II, OBESITY_GRADE_III,
        OBESITY_GRADE_IV
    }

    public static BodyMassIndex of(double weightKilograms, double heightMeters) {
        if (!(weightKilograms > 0) || !(heightMeters > 0)
                || !Double.isFinite(weightKilograms) || !Double.isFinite(heightMeters)) {
            throw new IllegalArgumentException("weight and height must be positive");
        }
        double value = weightKilograms / (heightMeters * heightMeters);
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("body mass index is outside the double range");
        }
        return new BodyMassIndex(value, classify(value));
    }

    public static Category classify(double value) {
        if (value <= 16) {
            return Category.SEVERE_THINNESS;
        }
        if (value <= 17) {
            return Category.UNDERWEIGHT;
        }
        if (value <= 18) {
            return Category.LOW_WEIGHT;
        }
        if (value <= 26) {
            return Category.HEALTHY;
        }
        if (value < 30) {
            return Category.OVERWEIGHT_GRADE_I;
        }
        if (value <= 35) {
            return Category.OBESITY_GRADE_II;
        }
        return value <= 40 ? Category.OBESITY_GRADE_III : Category.OBESITY_GRADE_IV;
    }
}
