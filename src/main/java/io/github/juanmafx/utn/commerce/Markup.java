package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;

public final class Markup {
    public static final BigDecimal DEFAULT_FACTOR = new BigDecimal("1.2");

    private Markup() {
    }

    public static BigDecimal salePrice(BigDecimal cost) {
        if (cost.signum() < 0) {
            throw new IllegalArgumentException("cost must be non-negative");
        }
        return Money.round(cost.multiply(DEFAULT_FACTOR));
    }
}
