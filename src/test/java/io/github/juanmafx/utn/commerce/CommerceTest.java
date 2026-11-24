package io.github.juanmafx.utn.commerce;

import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CommerceTest {
    private static BigDecimal money(String text) {
        return new BigDecimal(text);
    }

    @Test
    void vehicleTaxBoundaries() {
        Check.money("200", VehicleTax.annualTax(VehicleTax.Kind.PRIVATE, 2017, 2026));
        Check.money("150", VehicleTax.annualTax(VehicleTax.Kind.PRIVATE, 2016, 2026));
        Check.money("150", VehicleTax.annualTax(VehicleTax.Kind.PRIVATE, 2006, 2026));
        Check.money("0", VehicleTax.annualTax(VehicleTax.Kind.PRIVATE, 2005, 2026));
    }

    @Test
    void taxiAddsLicenseFee() {
        Check.money("350", VehicleTax.annualTax(VehicleTax.Kind.TAXI, 2020, 2026));
        Check.money("150", VehicleTax.annualTax(VehicleTax.Kind.TAXI, 1990, 2026));
    }

    @Test
    void hiredCarPaysOneHundredPerYear() {
        Check.money("600", VehicleTax.annualTax(VehicleTax.Kind.HIRED_CAR, 2020, 2026));
    }

    @Test
    void futureModelYearIsRejected() {
        Check.throwsType(IllegalArgumentException.class, () -> VehicleTax.annualTax(VehicleTax.Kind.TAXI, 2027, 2026));
    }

    @Test
    void shiftRatesFollowStatement() {
        Check.money("355.00", ShiftWage.dailyWage(ShiftWage.Shift.DAY, money("10")));
        Check.money("406.00", ShiftWage.dailyWage(ShiftWage.Shift.NIGHT, money("10")));
    }

    @Test
    void supplierCategoryADiscountBoundary() {
        Check.money("1000.00", SupplierPayment.finalAmount(SupplierPayment.Category.A, money("1000")));
        Check.money("950.95", SupplierPayment.finalAmount(SupplierPayment.Category.A, money("1001")));
    }

    @Test
    void supplierCategoryBDiscountBoundaries() {
        Check.money("1499.99", SupplierPayment.finalAmount(SupplierPayment.Category.B, money("1499.99")));
        Check.money("1470.00", SupplierPayment.finalAmount(SupplierPayment.Category.B, money("1500")));
        Check.money("2450.00", SupplierPayment.finalAmount(SupplierPayment.Category.B, money("2500")));
        Check.money("2500.01", SupplierPayment.finalAmount(SupplierPayment.Category.B, money("2500.01")));
    }

    @Test
    void supplierDiscountNeverRaisesAmount() {
        Check.forAll(401L, 2000, random -> BigDecimal.valueOf(random.nextLong(0, 1_000_000), 2), amount -> {
            for (SupplierPayment.Category category : SupplierPayment.Category.values()) {
                BigDecimal result = SupplierPayment.finalAmount(category, amount);
                Check.isTrue(result.compareTo(amount) <= 0 && result.signum() >= 0, "discount bounds");
            }
        });
    }

    @Test
    void revenueShareFindsLeader() {
        RevenueShare share = RevenueShare.of(List.of(
                new RevenueShare.Sale("alpha", money("10"), money("1")),
                new RevenueShare.Sale("beta", money("30"), money("1")),
                new RevenueShare.Sale("gamma", money("10"), money("1"))));
        Check.equal(List.of("beta"), share.leaders());
        Check.money("60.00", share.percentage());
    }

    @Test
    void revenueShareReportsTies() {
        RevenueShare share = RevenueShare.of(List.of(
                new RevenueShare.Sale("alpha", money("10"), money("2")),
                new RevenueShare.Sale("beta", money("20"), money("1"))));
        Check.equal(List.of("alpha", "beta"), share.leaders());
    }

    @Test
    void revenueShareRejectsZeroRevenue() {
        Check.throwsType(IllegalArgumentException.class, () -> RevenueShare.of(
                List.of(new RevenueShare.Sale("alpha", BigDecimal.ZERO, BigDecimal.ONE))));
    }

    @Test
    void expenseBalancesSumToZero() {
        Check.forAll(402L, 2000, random -> {
            BigDecimal[] paid = new BigDecimal[random.nextInt(1, 9)];
            Arrays.setAll(paid, index -> BigDecimal.valueOf(random.nextLong(0, 1_000_000), 2));
            return paid;
        }, paid -> {
            BigDecimal total = Arrays.stream(ExpenseSplit.balances(paid)).reduce(BigDecimal.ZERO, BigDecimal::add);
            Check.money("0", total);
        });
    }

    @Test
    void expenseSplitExample() {
        BigDecimal[] balances = ExpenseSplit.balances(money("30"), money("0"), money("0"));
        Check.money("-20.00", balances[0]);
        Check.money("10.00", balances[1]);
    }

    @Test
    void markupAppliesTwentyPercent() {
        Check.money("120.00", Markup.salePrice(money("100")));
    }

    @Test
    void electricityBillFormula() {
        Check.money("278.60", ElectricityBill.total(money("100"), money("180"), 30));
        Check.money("284.60", ElectricityBill.total(money("100"), money("180"), 25));
    }

    @Test
    void electricityBillRejectsDecreasingReading() {
        Check.throwsType(IllegalArgumentException.class, () -> ElectricityBill.total(money("10"), money("9"), 1));
    }

    @Test
    void transitTopUpAddsMargin() {
        Check.money("48.00", TransitCard.requiredTopUp(money("10"), 5, money("10")));
    }

    @Test
    void transitTopUpIsNeverNegative() {
        Check.money("0.00", TransitCard.requiredTopUp(money("1000"), 1, money("10")));
    }

    @Test
    void artGalleryCenturyBoundary() {
        Check.isTrue(ArtGallery.assess(2026, 1500, 1900).allBeforeTwentiethCentury(), "1900 precedes the century");
        Check.isTrue(!ArtGallery.assess(2026, 1500, 1901).allBeforeTwentiethCentury(), "1901 is inside the century");
    }

    @Test
    void artGalleryRenewsStockWhenNothingRecent() {
        Check.isTrue(ArtGallery.assess(2026, 1500, 2016).renewStock(), "age ten is not recent");
        Check.equal(1, ArtGallery.assess(2026, 1500, 2017).recentCount());
    }

    @Test
    void workpieceInspectionUsesAcceptedPiecesOnly() {
        WorkpieceInspection inspection = WorkpieceInspection.of(1.20, 1.25, 1.30);
        Check.equal(2, inspection.accepted());
        Check.equal(1, inspection.rejected());
        Check.near(1.225, inspection.acceptedMeanLength(), 1e-12);
    }

    @Test
    void productLabel() {
        Check.equal("7 - Hammer", new Product(7, "Hammer", money("9.90"), 1, 2, LocalDate.of(2026, 1, 1)).label());
    }

    @Test
    void moneyRejectsExponentOverflow() {
        for (String text : List.of("1e2147483647", "123e2147483645", "1e-2147483647")) {
            Check.throwsType(IllegalArgumentException.class, () -> Money.parse(text));
        }
    }

    @Test
    void moneyNormalizesZeroWithExtremeExponent() {
        Check.money("0", ShiftWage.dailyWage(ShiftWage.Shift.DAY, Money.parse("0e-2147483647")));
    }

    @Test
    void moneyRoundsHalfToEven() {
        Check.equal(new BigDecimal("0.12"), Money.round(new BigDecimal("0.125")));
        Check.equal(new BigDecimal("0.14"), Money.round(new BigDecimal("0.135")));
        Check.equal(new BigDecimal("-0.12"), Money.round(new BigDecimal("-0.125")));
    }

    @Test
    void moneyRoundRejectsHugeMagnitudeBeforeRescaling() {
        Check.throwsType(IllegalArgumentException.class, () -> Money.round(new BigDecimal("1e500000000")));
        Check.throwsType(IllegalArgumentException.class, () -> Money.round(new BigDecimal("1e-500000000")));
    }

    @Test
    void unknownOptionsDoNotExposeClassNames() {
        List<Runnable> parsers = List.of(
                () -> VehicleTax.Kind.parse("boat"),
                () -> ShiftWage.Shift.parse("dusk"),
                () -> SupplierPayment.Category.parse("c"));
        for (Runnable parser : parsers) {
            try {
                parser.run();
                throw new AssertionError("expected rejection");
            } catch (IllegalArgumentException expected) {
                Check.isTrue(!expected.getMessage().contains("io.github"), expected.getMessage());
            }
        }
    }
}
