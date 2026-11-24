package io.github.juanmafx.utn.arithmetic;

import java.util.Arrays;
import java.util.Locale;

public enum Calculator {
    ADD {
        @Override
        double compute(double left, double right) {
            return left + right;
        }
    },
    SUBTRACT {
        @Override
        double compute(double left, double right) {
            return left - right;
        }
    },
    MULTIPLY {
        @Override
        double compute(double left, double right) {
            return left * right;
        }
    },
    DIVIDE {
        @Override
        double compute(double left, double right) {
            if (right == 0) {
                throw new ArithmeticException("division by zero");
            }
            return left / right;
        }
    };

    abstract double compute(double left, double right);

    public double apply(double left, double right) {
        if (!Double.isFinite(left) || !Double.isFinite(right)) {
            throw new IllegalArgumentException("operands must be finite");
        }
        double result = compute(left, right);
        if (!Double.isFinite(result)) {
            throw new ArithmeticException("result is outside the double range");
        }
        return result;
    }

    public static Calculator parse(String name) {
        String normalized = name.trim().toUpperCase(Locale.ROOT);
        return Arrays.stream(values()).filter(operation -> operation.name().equals(normalized)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown operation"));
    }
}
