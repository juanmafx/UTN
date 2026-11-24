package io.github.juanmafx.utn.commerce;

import java.math.BigDecimal;
import java.util.Locale;

public final class VehicleTax {
    public enum Kind {
        PRIVATE, TAXI, HIRED_CAR;

        public static Kind parse(String name) {
            return valueOf(name.trim().toUpperCase(Locale.ROOT));
        }
    }

    private static final int NEW_VEHICLE_TAX = 200;
    private static final int MIDDLE_AGE_TAX = 150;
    private static final int TAXI_LICENSE_FEE = 150;
    private static final int HIRED_CAR_TAX_PER_YEAR = 100;

    private VehicleTax() {
    }

    public static BigDecimal annualTax(Kind kind, int modelYear, int currentYear) {
        int age = Math.subtractExact(currentYear, modelYear);
        if (age < 0) {
            throw new IllegalArgumentException("model year cannot be in the future");
        }
        int tax = switch (kind) {
            case PRIVATE -> privateTax(age);
            case TAXI -> privateTax(age) + TAXI_LICENSE_FEE;
            case HIRED_CAR -> Math.multiplyExact(age, HIRED_CAR_TAX_PER_YEAR);
        };
        return BigDecimal.valueOf(tax);
    }

    private static int privateTax(int age) {
        if (age < 10) {
            return NEW_VEHICLE_TAX;
        }
        return age <= 20 ? MIDDLE_AGE_TAX : 0;
    }
}
