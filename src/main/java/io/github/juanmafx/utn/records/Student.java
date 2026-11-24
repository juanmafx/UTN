package io.github.juanmafx.utn.records;

import io.github.juanmafx.utn.education.Grading;
import java.util.Objects;

public record Student(int fileNumber, Person person, double firstGrade, double secondGrade, double thirdGrade) {
    public Student {
        Objects.requireNonNull(person);
        Grading.mean(firstGrade, secondGrade, thirdGrade);
    }

    public double mean() {
        return Grading.mean(firstGrade, secondGrade, thirdGrade);
    }

    public Grading.Standing standing() {
        return Grading.classify(mean());
    }
}
