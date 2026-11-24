package io.github.juanmafx.utn.arithmetic;

import java.util.Locale;

public enum Calculator {
    ADD {
        @Override
        public double apply(double left, double right) {
            return left + right;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double left, double right) {
            return left - right;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double left, double right) {
            return left * right;
        }
    },
    DIVIDE {
        @Override
        public double apply(double left, double right) {
            if (right == 0) {
                throw new ArithmeticException("division by zero");
            }
            return left / right;
        }
    };

    public abstract double apply(double left, double right);

    public static Calculator parse(String name) {
        return valueOf(name.trim().toUpperCase(Locale.ROOT));
    }
}
