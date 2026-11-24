package io.github.juanmafx.utn.records;

import io.github.juanmafx.utn.education.Grading;
import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RecordsTest {
    private static final Person PERSON = new Person("Ada", "Lovelace", 1, 1000, LocalDate.of(2000, 2, 29));

    @Test
    void personFullName() {
        Check.equal("Ada Lovelace", PERSON.fullName());
    }

    @Test
    void studentMeanUsesAllThreeGrades() {
        Student student = new Student(1, PERSON, 6, 8, 10);
        Check.near(8, student.mean(), 1e-12);
        Check.equal(Grading.Standing.PROMOTED, student.standing());
    }

    @Test
    void studentRejectsInvalidGrade() {
        Check.throwsType(IllegalArgumentException.class, () -> new Student(1, PERSON, 11, 8, 10));
    }

    @Test
    void employeeSalaryIsExact() {
        Check.money("35.70", new Employee(1, "Grace", new BigDecimal("3"), new BigDecimal("11.90")).salary());
    }

    @Test
    void memberAgeHandlesBirthdayBoundary() {
        LibraryMember member = new LibraryMember(1, "Ada", LocalDate.of(2000, 3, 15));
        Check.equal(25, member.ageOn(LocalDate.of(2026, 3, 14)));
        Check.equal(26, member.ageOn(LocalDate.of(2026, 3, 15)));
    }

    @Test
    void memberAgeHandlesLeapDayBirth() {
        LibraryMember member = new LibraryMember(1, "Ada", LocalDate.of(2000, 2, 29));
        Check.equal(25, member.ageOn(LocalDate.of(2026, 2, 27)));
        Check.equal(26, member.ageOn(LocalDate.of(2026, 3, 1)));
    }

    @Test
    void memberAgeRejectsDateBeforeBirth() {
        LibraryMember member = new LibraryMember(1, "Ada", LocalDate.of(2000, 1, 1));
        Check.throwsType(IllegalArgumentException.class, () -> member.ageOn(LocalDate.of(1999, 1, 1)));
    }
}
