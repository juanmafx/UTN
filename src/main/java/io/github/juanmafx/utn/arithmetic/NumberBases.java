package io.github.juanmafx.utn.arithmetic;

public final class NumberBases {
    private static final int MAX_BITS = 63;

    private NumberBases() {
    }

    public static long binaryToDecimal(String binary) {
        if (binary == null || binary.isEmpty()) {
            throw new IllegalArgumentException("binary text is required");
        }
        long value = 0;
        int significantBits = 0;
        for (int index = 0; index < binary.length(); index++) {
            char digit = binary.charAt(index);
            if (digit != '0' && digit != '1') {
                throw new IllegalArgumentException("only 0 and 1 are valid binary digits");
            }
            if (significantBits > 0 || digit == '1') {
                significantBits++;
            }
            if (significantBits > MAX_BITS) {
                throw new ArithmeticException("binary value exceeds 63 bits");
            }
            value = (value << 1) | (digit - '0');
        }
        return value;
    }

    public static String decimalToBinary(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be non-negative");
        }
        if (value == 0) {
            return "0";
        }
        StringBuilder digits = new StringBuilder();
        for (long remaining = value; remaining > 0; remaining >>>= 1) {
            digits.append((char) ('0' + (remaining & 1)));
        }
        return digits.reverse().toString();
    }
}
