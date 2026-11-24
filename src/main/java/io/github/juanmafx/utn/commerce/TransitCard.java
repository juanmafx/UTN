package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;

public final class TransitCard {
    private static final BigDecimal SAFETY_MARGIN_FARES = new BigDecimal("0.8");

    private TransitCard() {
    }

    public static BigDecimal requiredTopUp(BigDecimal balance, int plannedTrips, BigDecimal fare) {
        if (plannedTrips < 0 || fare.signum() < 0) {
            throw new IllegalArgumentException("trips and fare must be non-negative");
        }
        BigDecimal required = fare.multiply(BigDecimal.valueOf(plannedTrips))
                .subtract(balance)
                .add(fare.multiply(SAFETY_MARGIN_FARES));
        return Money.round(required.max(BigDecimal.ZERO));
    }
}
