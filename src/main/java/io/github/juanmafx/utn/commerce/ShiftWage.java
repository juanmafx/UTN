package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;

public final class ShiftWage {
    public enum Shift {
        DAY(new BigDecimal("35.50")),
        NIGHT(new BigDecimal("40.60"));

        private final BigDecimal hourlyRate;

        Shift(BigDecimal hourlyRate) {
            this.hourlyRate = hourlyRate;
        }

        public BigDecimal hourlyRate() {
            return hourlyRate;
        }

        public static Shift parse(String name) {
            String normalized = name.trim().toUpperCase(Locale.ROOT);
            return Arrays.stream(values()).filter(value -> value.name().equals(normalized)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("unknown shift"));
        }
    }

    private ShiftWage() {
    }

    public static BigDecimal dailyWage(Shift shift, BigDecimal hours) {
        if (hours.signum() < 0) {
            throw new IllegalArgumentException("hours must be non-negative");
        }
        return Money.round(shift.hourlyRate().multiply(hours));
    }
}
