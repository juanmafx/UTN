package io.github.juanmafx.utn.records;

import java.time.LocalDate;
import java.util.Objects;

public record Person(String firstName, String lastName, int documentTypeId, int documentNumber, LocalDate birthDate) {
    public Person {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(birthDate);
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}
