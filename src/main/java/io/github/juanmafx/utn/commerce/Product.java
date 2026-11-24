package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record Product(int code, String detail, BigDecimal price, int typeId, int brandId, LocalDate date) {
    public Product {
        Objects.requireNonNull(detail);
        Objects.requireNonNull(date);
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("price must be non-negative");
        }
    }

    public String label() {
        return code + " - " + detail;
    }
}
