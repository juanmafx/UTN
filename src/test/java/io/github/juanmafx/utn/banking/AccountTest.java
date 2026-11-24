package io.github.juanmafx.utn.banking;

import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.math.BigDecimal;

public class AccountTest {
    @Test
    void depositAndWithdrawKeepExactCents() {
        Account account = new Account(200, new BigDecimal("1000"), Account.TypeCode.I);
        account.deposit(new BigDecimal("0.10"));
        account.deposit(new BigDecimal("0.20"));
        account.withdraw(new BigDecimal("0.30"));
        Check.money("1000.00", account.balance());
    }

    @Test
    void overdraftIsRejectedAndBalanceUnchanged() {
        Account account = Account.withNumber(7);
        Check.throwsType(IllegalStateException.class, () -> account.withdraw(BigDecimal.ONE));
        Check.money("0", account.balance());
        Check.equal(Account.TypeCode.T, account.typeCode());
    }

    @Test
    void nonPositiveAmountsAreRejected() {
        Account account = Account.withNumber(7);
        Check.throwsType(IllegalArgumentException.class, () -> account.deposit(BigDecimal.ZERO));
        Check.throwsType(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("-1")));
    }

    @Test
    void balanceNeverGoesNegative() {
        Check.forAll(201L, 200, random -> random.ints(50, -500, 500).toArray(), operations -> {
            Account account = new Account(1, new BigDecimal("100"), Account.TypeCode.I);
            for (int operation : operations) {
                BigDecimal amount = BigDecimal.valueOf(Math.abs(operation) + 1L, 2);
                if (operation >= 0) {
                    account.deposit(amount);
                } else if (account.balance().compareTo(amount) >= 0) {
                    account.withdraw(amount);
                }
                Check.isTrue(account.balance().signum() >= 0, "negative balance");
            }
        });
    }

    @Test
    void subCentAmountsAreRejectedAndBalanceUnchanged() {
        Account account = new Account(3, new BigDecimal("0.01"), Account.TypeCode.T);
        Check.throwsType(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("0.005")));
        Check.throwsType(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("0.004")));
        Check.money("0.01", account.balance());
        Check.throwsType(IllegalArgumentException.class, () -> new Account(4, new BigDecimal("0.001"), Account.TypeCode.T));
    }

    @Test
    void trailingZerosBeyondCentsAreAccepted() {
        Account account = Account.withNumber(5);
        account.deposit(new BigDecimal("1.2500"));
        Check.money("1.25", account.balance());
        Check.equal(2, account.balance().scale());
    }
}
