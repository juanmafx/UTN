package io.github.juanmafx.utn.education;

import io.github.juanmafx.utn.education.Applicants.Applicant;
import io.github.juanmafx.utn.education.ClassQuota.Classroom;
import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.util.List;

public class EducationTest {
    @Test
    void standingBoundaries() {
        Check.equal(Grading.Standing.NOT_ELIGIBLE, Grading.classify(3.99));
        Check.equal(Grading.Standing.REGULAR, Grading.classify(4));
        Check.equal(Grading.Standing.REGULAR, Grading.classify(7.99));
        Check.equal(Grading.Standing.PROMOTED, Grading.classify(8));
    }

    @Test
    void meanKeepsFraction() {
        Check.near(7.0 + 2.0 / 3.0, Grading.mean(7, 8, 8), 1e-12);
    }

    @Test
    void gradeOutsideScaleIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> Grading.mean(11, 1, 1));
        Check.throwsType(IllegalArgumentException.class, () -> Grading.band(Double.NaN));
    }

    @Test
    void bandBoundaries() {
        Check.equal(Grading.Band.FAILED, Grading.band(3.9));
        Check.equal(Grading.Band.GOOD, Grading.band(4));
        Check.equal(Grading.Band.VERY_GOOD, Grading.band(6));
        Check.equal(Grading.Band.VERY_GOOD, Grading.band(8));
        Check.equal(Grading.Band.EXCELLENT, Grading.band(8.5));
    }

    @Test
    void standingIsMonotonicInMean() {
        Check.forAll(501L, 3000, random -> new double[] {random.nextDouble(0, 10), random.nextDouble(0, 10)}, pair -> {
            double low = Math.min(pair[0], pair[1]);
            double high = Math.max(pair[0], pair[1]);
            Check.isTrue(Grading.classify(low).ordinal() <= Grading.classify(high).ordinal(), "monotonic");
        });
    }

    @Test
    void cohortSummaryCountsEveryStudent() {
        CohortSummary summary = CohortSummary.of(List.of(new double[] {2, 2, 2}, new double[] {5, 5, 5}, new double[] {9, 9, 9}));
        Check.equal(3, summary.students());
        Check.equal(1, summary.notEligible());
        Check.equal(1, summary.regular());
        Check.equal(1, summary.promoted());
        Check.near(16.0 / 3.0, summary.overallMean(), 1e-12);
    }

    @Test
    void applicantLevels() {
        Check.equal(Applicants.Level.SUPERIOR, new Applicant("a", 9, 10).level());
        Check.equal(Applicants.Level.MEDIUM, new Applicant("b", 75, 100).level());
        Check.equal(Applicants.Level.REGULAR, new Applicant("c", 1, 2).level());
        Check.equal(Applicants.Level.BELOW_LEVEL, new Applicant("d", 49, 100).level());
    }

    @Test
    void applicantWinnersIncludeTies() {
        List<Applicant> applicants = List.of(new Applicant("a", 9, 10), new Applicant("b", 18, 20), new Applicant("c", 1, 10));
        Check.equal(2, Applicants.winners(applicants).size());
    }

    @Test
    void inconsistentApplicantIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> new Applicant("a", 11, 10));
        Check.throwsType(IllegalArgumentException.class, () -> new Applicant("a", 0, 0));
    }

    @Test
    void classQuotaReport() {
        List<Classroom> rooms = List.of(new Classroom("1A", 10, 10), new Classroom("1B", 5, 5), new Classroom("1C", 15, 16));
        Check.equal("1B", ClassQuota.leastEnrolled(rooms));
        Check.near(61.0 / 3.0, ClassQuota.meanEnrollment(rooms), 1e-12);
        Check.equal(List.of("1C"), ClassQuota.needingNewDivision(rooms, 30));
        Check.near(50, rooms.get(0).girlsPercentage(), 1e-12);
    }

    @Test
    void classroomPercentagesSumToHundred() {
        Check.forAll(502L, 1000, random -> new Classroom("x", random.nextInt(0, 40), random.nextInt(1, 40)),
                room -> Check.near(100, room.girlsPercentage() + room.boysPercentage(), 1e-9));
    }

    @Test
    void formStateTransitions() {
        StudentFormState editing = StudentFormState.idle().startEditing();
        Check.isTrue(editing.fieldsEnabled() && editing.saveEnabled() && !editing.newEnabled(), "editing");
        Check.equal(StudentFormState.idle(), editing.finish());
    }

    @Test
    void formStateRejectsInvalidTransitions() {
        Check.throwsType(IllegalStateException.class, () -> StudentFormState.idle().finish());
        Check.throwsType(IllegalStateException.class, () -> StudentFormState.idle().startEditing().startEditing());
    }
}
