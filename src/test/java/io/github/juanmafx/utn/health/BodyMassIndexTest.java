package io.github.juanmafx.utn.health;

import io.github.juanmafx.utn.health.BodyMassIndex.Category;
import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;

public class BodyMassIndexTest {
    @Test
    void computesFromKilogramsAndMeters() {
        Check.near(22.857, BodyMassIndex.of(70, 1.75).value(), 1e-3);
    }

    @Test
    void categoryBoundaries() {
        Check.equal(Category.SEVERE_THINNESS, BodyMassIndex.classify(16));
        Check.equal(Category.UNDERWEIGHT, BodyMassIndex.classify(17));
        Check.equal(Category.LOW_WEIGHT, BodyMassIndex.classify(18));
        Check.equal(Category.HEALTHY, BodyMassIndex.classify(26));
        Check.equal(Category.OVERWEIGHT_GRADE_I, BodyMassIndex.classify(29.9));
        Check.equal(Category.OBESITY_GRADE_II, BodyMassIndex.classify(30));
        Check.equal(Category.OBESITY_GRADE_II, BodyMassIndex.classify(35));
        Check.equal(Category.OBESITY_GRADE_III, BodyMassIndex.classify(40));
        Check.equal(Category.OBESITY_GRADE_IV, BodyMassIndex.classify(40.1));
    }

    @Test
    void invalidInputIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> BodyMassIndex.of(0, 1.7));
        Check.throwsType(IllegalArgumentException.class, () -> BodyMassIndex.of(70, Double.NaN));
    }

    @Test
    void categoryIsMonotonic() {
        Check.forAll(701L, 3000, random -> new double[] {random.nextDouble(5, 80), random.nextDouble(5, 80)}, pair -> {
            double low = Math.min(pair[0], pair[1]);
            double high = Math.max(pair[0], pair[1]);
            Check.isTrue(BodyMassIndex.classify(low).ordinal() <= BodyMassIndex.classify(high).ordinal(), "monotonic");
        });
    }
}
