package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {
    private static final int MAX_DIGITS = 30;
    private static final int MAX_ROUNDING_DIGITS = 100;

    private Money() {
    }

    public static BigDecimal parse(String text) {
        BigDecimal value = new BigDecimal(text.trim());
        if (value.signum() == 0) {
            return BigDecimal.ZERO;
        }
        requireDigits(value, MAX_DIGITS);
        return value;
    }

    public static BigDecimal round(BigDecimal amount) {
        if (amount.signum() == 0) {
            return BigDecimal.ZERO.setScale(2);
        }
        requireDigits(amount, MAX_ROUNDING_DIGITS);
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }

    private static void requireDigits(BigDecimal value, int maximumDigits) {
        long integerDigits = (long) value.precision() - value.scale();
        if (value.scale() > maximumDigits || integerDigits > maximumDigits) {
            throw new IllegalArgumentException("amount is outside the supported range");
        }
    }
}
