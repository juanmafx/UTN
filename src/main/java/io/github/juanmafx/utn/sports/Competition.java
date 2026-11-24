package io.github.juanmafx.utn.sports;

import java.util.List;

public record Competition(int competitors, double meanScore, int scoresBelowTen, List<String> winners) {
    public record Entry(int number, String name, int score) {
        public Entry {
            if (number <= 0) {
                throw new IllegalArgumentException("competitor number must be positive");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("competitor name is required");
            }
        }
    }

    public static Competition summarize(List<Entry> entries) {
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("at least one competitor is required");
        }
        int best = entries.stream().mapToInt(Entry::score).max().orElseThrow();
        double mean = entries.stream().mapToInt(Entry::score).average().orElseThrow();
        int belowTen = (int) entries.stream().filter(entry -> entry.score() < 10).count();
        List<String> winners = entries.stream().filter(entry -> entry.score() == best).map(Entry::name).toList();
        return new Competition(entries.size(), mean, belowTen, winners);
    }
}
