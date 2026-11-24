package io.github.juanmafx.utn.records;

import io.github.juanmafx.utn.commerce.Money;
import java.math.BigDecimal;
import java.util.Objects;

public record Employee(int fileNumber, String name, BigDecimal hoursWorked, BigDecimal hourlyRate) {
    public Employee {
        Objects.requireNonNull(name);
        if (hoursWorked.signum() < 0 || hourlyRate.signum() < 0) {
            throw new IllegalArgumentException("hours and rate must be non-negative");
        }
    }

    public BigDecimal salary() {
        return Money.round(hoursWorked.multiply(hourlyRate));
    }
}
