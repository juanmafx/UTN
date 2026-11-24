package io.github.juanmafx.utn.records;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public record LibraryMember(int number, String name, LocalDate birthDate) {
    public LibraryMember {
        Objects.requireNonNull(name);
        Objects.requireNonNull(birthDate);
    }

    public int ageOn(LocalDate date) {
        if (date.isBefore(birthDate)) {
            throw new IllegalArgumentException("date precedes birth date");
        }
        return Period.between(birthDate, date).getYears();
    }
}
