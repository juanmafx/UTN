package io.github.juanmafx.utn.education;

import java.util.List;

public record CohortSummary(int students, int notEligible, int regular, int promoted, double overallMean) {
    public static CohortSummary of(List<double[]> gradeSets) {
        int notEligible = 0;
        int regular = 0;
        int promoted = 0;
        double total = 0;
        for (double[] grades : gradeSets) {
            if (grades.length != 3) {
                throw new IllegalArgumentException("each student needs exactly three grades");
            }
            double mean = Grading.mean(grades[0], grades[1], grades[2]);
            total += mean;
            switch (Grading.classify(mean)) {
                case NOT_ELIGIBLE -> notEligible++;
                case REGULAR -> regular++;
                case PROMOTED -> promoted++;
            }
        }
        double overall = gradeSets.isEmpty() ? Double.NaN : total / gradeSets.size();
        return new CohortSummary(gradeSets.size(), notEligible, regular, promoted, overall);
    }
}
