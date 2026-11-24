package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {
    private static final int MAX_DIGITS = 30;

    private Money() {
    }

    public static BigDecimal parse(String text) {
        BigDecimal value = new BigDecimal(text.trim());
        if (value.signum() != 0 && (value.scale() > MAX_DIGITS || value.precision() - value.scale() > MAX_DIGITS)) {
            throw new IllegalArgumentException("amount is outside the supported range");
        }
        return value;
    }

    public static BigDecimal round(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
