package io.github.juanmafx.utn.simulation;

import java.util.random.RandomGenerator;

public final class Shuffling {
    private Shuffling() {
    }

    public static void partialFisherYates(int[] values, int count, RandomGenerator generator) {
        if (count < 0 || count > values.length) {
            throw new IllegalArgumentException("count outside array bounds");
        }
        for (int index = 0; index < count; index++) {
            int swapWith = index + generator.nextInt(values.length - index);
            int held = values[index];
            values[index] = values[swapWith];
            values[swapWith] = held;
        }
    }
}
