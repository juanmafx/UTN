package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ExpenseSplit {
    private ExpenseSplit() {
    }

    public static BigDecimal[] balances(BigDecimal... paid) {
        if (paid.length == 0) {
            throw new IllegalArgumentException("at least one participant is required");
        }
        long[] paidCents = new long[paid.length];
        long totalCents = 0;
        for (int index = 0; index < paid.length; index++) {
            if (paid[index].signum() < 0) {
                throw new IllegalArgumentException("payments must be non-negative");
            }
            paidCents[index] = paid[index].movePointRight(2).setScale(0, RoundingMode.HALF_EVEN).longValueExact();
            totalCents = Math.addExact(totalCents, paidCents[index]);
        }
        long baseShare = totalCents / paid.length;
        long extraCents = totalCents % paid.length;
        BigDecimal[] balances = new BigDecimal[paid.length];
        for (int index = 0; index < paid.length; index++) {
            long share = baseShare + (index < extraCents ? 1 : 0);
            balances[index] = BigDecimal.valueOf(share - paidCents[index], 2);
        }
        return balances;
    }
}
