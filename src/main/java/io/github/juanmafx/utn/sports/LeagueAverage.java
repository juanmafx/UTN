package io.github.juanmafx.utn.sports;

public record LeagueAverage(int matches, double pointsPerMatch) {
    public static final int WIN_POINTS = 3;
    public static final int DRAW_POINTS = 2;

    public static LeagueAverage of(int won, int drawn, int lost) {
        if (won < 0 || drawn < 0 || lost < 0) {
            throw new IllegalArgumentException("match counts must be non-negative");
        }
        int matches = Math.addExact(Math.addExact(won, drawn), lost);
        if (matches == 0) {
            throw new IllegalArgumentException("at least one match is required");
        }
        double points = (double) won * WIN_POINTS + (double) drawn * DRAW_POINTS;
        return new LeagueAverage(matches, points / matches);
    }
}
