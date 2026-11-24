package io.github.juanmafx.utn.education;

import java.util.List;

public final class Applicants {
    public enum Level {
        SUPERIOR, MEDIUM, REGULAR, BELOW_LEVEL
    }

    public record Applicant(String name, int correctAnswers, int totalQuestions) {
        public Applicant {
            if (totalQuestions <= 0 || correctAnswers < 0 || correctAnswers > totalQuestions) {
                throw new IllegalArgumentException("answer counts are inconsistent");
            }
        }

        public double percentage() {
            return correctAnswers * 100.0 / totalQuestions;
        }

        public Level level() {
            double percentage = percentage();
            if (percentage >= 90) {
                return Level.SUPERIOR;
            }
            if (percentage >= 75) {
                return Level.MEDIUM;
            }
            return percentage >= 50 ? Level.REGULAR : Level.BELOW_LEVEL;
        }
    }

    private Applicants() {
    }

    public static List<Applicant> winners(List<Applicant> applicants) {
        double best = applicants.stream().mapToDouble(Applicant::percentage).max()
                .orElseThrow(() -> new IllegalArgumentException("at least one applicant is required"));
        return applicants.stream().filter(applicant -> applicant.percentage() == best).toList();
    }
}
