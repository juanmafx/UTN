package io.github.juanmafx.utn.banking;

import io.github.juanmafx.utn.commerce.Money;
import java.math.BigDecimal;
import java.util.Objects;

public final class Account {
    public enum TypeCode {
        I, T
    }

    private final int number;
    private final TypeCode typeCode;
    private BigDecimal balance;

    public Account(int number, BigDecimal openingBalance, TypeCode typeCode) {
        if (number < 0) {
            throw new IllegalArgumentException("account number must be non-negative");
        }
        requireNonNegative(openingBalance);
        this.number = number;
        this.typeCode = Objects.requireNonNull(typeCode);
        this.balance = Money.round(openingBalance);
    }

    public static Account withNumber(int number) {
        return new Account(number, BigDecimal.ZERO, TypeCode.T);
    }

    public void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = Money.round(balance.add(amount));
    }

    public void withdraw(BigDecimal amount) {
        requirePositive(amount);
        BigDecimal remaining = balance.subtract(amount);
        if (remaining.signum() < 0) {
            throw new IllegalStateException("insufficient funds");
        }
        balance = Money.round(remaining);
    }

    public int number() {
        return number;
    }

    public TypeCode typeCode() {
        return typeCode;
    }

    public BigDecimal balance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account " + number + " balance " + balance.toPlainString() + " type " + typeCode;
    }

    private static void requirePositive(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
    }

    private static void requireNonNegative(BigDecimal amount) {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("amount must be non-negative");
        }
    }
}
