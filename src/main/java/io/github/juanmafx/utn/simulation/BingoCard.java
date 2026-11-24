package io.github.juanmafx.utn.simulation;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public record BingoCard(List<Integer> numbers) {
    public static final int CARD_SIZE = 15;
    public static final int MAXIMUM_NUMBER = 100;

    public BingoCard {
        numbers = List.copyOf(numbers);
        if (numbers.size() != CARD_SIZE || numbers.stream().distinct().count() != CARD_SIZE
                || numbers.stream().anyMatch(number -> !inRange(number))) {
            throw new IllegalArgumentException("a card needs 15 distinct numbers from 1 to 100");
        }
    }

    public static BingoCard generate(RandomGenerator generator) {
        int[] pool = IntStream.rangeClosed(1, MAXIMUM_NUMBER).toArray();
        Shuffling.partialFisherYates(pool, CARD_SIZE, generator);
        int[] drawn = Arrays.copyOf(pool, CARD_SIZE);
        Arrays.sort(drawn);
        return new BingoCard(Arrays.stream(drawn).boxed().toList());
    }

    public int countMatches(int... picks) {
        if (Arrays.stream(picks).anyMatch(pick -> !inRange(pick))) {
            throw new IllegalArgumentException("picks must be between 1 and 100");
        }
        return (int) Arrays.stream(picks).distinct().filter(numbers::contains).count();
    }

    public String resultMessage(int... picks) {
        int matches = countMatches(picks);
        return matches == 0 ? "No luck: the player did not mark any number." : "The player marked " + matches + " number(s).";
    }

    private static boolean inRange(int number) {
        return number >= 1 && number <= MAXIMUM_NUMBER;
    }
}
