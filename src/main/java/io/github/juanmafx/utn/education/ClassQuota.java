package io.github.juanmafx.utn.education;

import java.util.Comparator;
import java.util.List;

public final class ClassQuota {
    public record Classroom(String code, int girls, int boys) {
        public Classroom {
            if (girls < 0 || boys < 0) {
                throw new IllegalArgumentException("enrollment must be non-negative");
            }
        }

        public int enrolled() {
            return girls + boys;
        }

        public double girlsPercentage() {
            return enrolled() == 0 ? 0 : girls * 100.0 / enrolled();
        }

        public double boysPercentage() {
            return enrolled() == 0 ? 0 : boys * 100.0 / enrolled();
        }
    }

    private ClassQuota() {
    }

    public static String leastEnrolled(List<Classroom> classrooms) {
        return classrooms.stream().min(Comparator.comparingInt(Classroom::enrolled))
                .orElseThrow(() -> new IllegalArgumentException("at least one classroom is required"))
                .code();
    }

    public static double meanEnrollment(List<Classroom> classrooms) {
        return classrooms.stream().mapToInt(Classroom::enrolled).average()
                .orElseThrow(() -> new IllegalArgumentException("at least one classroom is required"));
    }

    public static List<String> needingNewDivision(List<Classroom> classrooms, int maximumQuota) {
        if (maximumQuota <= 0) {
            throw new IllegalArgumentException("quota must be positive");
        }
        return classrooms.stream().filter(room -> room.enrolled() > maximumQuota).map(Classroom::code).toList();
    }
}
