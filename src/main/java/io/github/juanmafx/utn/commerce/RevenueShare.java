package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public record RevenueShare(List<String> leaders, BigDecimal leaderRevenue, BigDecimal percentage) {
    public record Sale(String product, BigDecimal unitPrice, BigDecimal quantity) {
        public Sale {
            if (unitPrice.signum() < 0 || quantity.signum() < 0) {
                throw new IllegalArgumentException("price and quantity must be non-negative");
            }
        }

        BigDecimal revenue() {
            return unitPrice.multiply(quantity);
        }
    }

    public static RevenueShare of(List<Sale> sales) {
        BigDecimal total = sales.stream().map(Sale::revenue).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (total.signum() == 0) {
            throw new IllegalArgumentException("total revenue must be positive");
        }
        BigDecimal best = sales.stream().map(Sale::revenue).max(BigDecimal::compareTo).orElseThrow();
        List<String> leaders = sales.stream()
                .filter(sale -> sale.revenue().compareTo(best) == 0)
                .map(Sale::product)
                .toList();
        BigDecimal percentage = best.multiply(BigDecimal.valueOf(100)).divide(total, 2, RoundingMode.HALF_EVEN);
        return new RevenueShare(leaders, Money.round(best), percentage);
    }
}
