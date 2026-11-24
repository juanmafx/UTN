package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;

public final class ElectricityBill {
    private static final BigDecimal RATE_PER_UNIT = new BigDecimal("2");
    private static final BigDecimal VALUE_ADDED_TAX = new BigDecimal("0.21");
    private static final BigDecimal EXTRA_LEVY = new BigDecimal("0.5");
    private static final BigDecimal FIXED_FEE = new BigDecimal("5");
    private static final BigDecimal DAILY_COST = new BigDecimal("1.2");
    private static final int DAYS_IN_BILLING_MONTH = 30;

    private ElectricityBill() {
    }

    public static BigDecimal total(BigDecimal previousReading, BigDecimal currentReading, int dayOfMonth) {
        if (currentReading.compareTo(previousReading) < 0) {
            throw new IllegalArgumentException("current reading cannot be lower than the previous one");
        }
        if (dayOfMonth < 1 || dayOfMonth > DAYS_IN_BILLING_MONTH) {
            throw new IllegalArgumentException("day of month must be between 1 and 30");
        }
        BigDecimal energy = currentReading.subtract(previousReading).multiply(RATE_PER_UNIT);
        BigDecimal remainingDays = BigDecimal.valueOf((long) DAYS_IN_BILLING_MONTH - dayOfMonth);
        return Money.round(energy
                .add(energy.multiply(VALUE_ADDED_TAX))
                .add(energy.multiply(EXTRA_LEVY))
                .add(FIXED_FEE)
                .add(remainingDays.multiply(DAILY_COST)));
    }
}
