package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.util.Locale;

public final class SupplierPayment {
    public enum Category {
        A, B;

        public static Category parse(String name) {
            return valueOf(name.trim().toUpperCase(Locale.ROOT));
        }
    }

    private static final BigDecimal CATEGORY_A_THRESHOLD = new BigDecimal("1000");
    private static final BigDecimal CATEGORY_A_FACTOR = new BigDecimal("0.95");
    private static final BigDecimal CATEGORY_B_LOWER = new BigDecimal("1500");
    private static final BigDecimal CATEGORY_B_UPPER = new BigDecimal("2500");
    private static final BigDecimal CATEGORY_B_FACTOR = new BigDecimal("0.98");

    private SupplierPayment() {
    }

    public static BigDecimal finalAmount(Category category, BigDecimal amount) {
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("amount must be non-negative");
        }
        boolean categoryADiscount = category == Category.A && amount.compareTo(CATEGORY_A_THRESHOLD) > 0;
        boolean categoryBDiscount = category == Category.B
                && amount.compareTo(CATEGORY_B_LOWER) >= 0
                && amount.compareTo(CATEGORY_B_UPPER) <= 0;
        if (categoryADiscount) {
            return Money.round(amount.multiply(CATEGORY_A_FACTOR));
        }
        return Money.round(categoryBDiscount ? amount.multiply(CATEGORY_B_FACTOR) : amount);
    }
}
