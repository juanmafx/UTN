package io.github.juanmafx.utn.simulation;

public record PlayingCard(Suit suit, int rank) {
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }

    public PlayingCard {
        if (suit == null || rank < 2 || rank > 14) {
            throw new IllegalArgumentException("rank must be between 2 and 14");
        }
    }

    public String rankLabel() {
        return switch (rank) {
            case 14 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            default -> String.valueOf(rank);
        };
    }
}
