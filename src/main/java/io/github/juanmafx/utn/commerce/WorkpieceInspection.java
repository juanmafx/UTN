package io.github.juanmafx.utn.commerce;

public record WorkpieceInspection(int accepted, int rejected, double acceptedMeanLength) {
    private static final double MINIMUM_LENGTH_METERS = 1.20;
    private static final double MAXIMUM_LENGTH_METERS = 1.25;

    public static WorkpieceInspection of(double... lengths) {
        int accepted = 0;
        double acceptedSum = 0;
        for (double length : lengths) {
            if (length <= 0 || Double.isNaN(length)) {
                throw new IllegalArgumentException("lengths must be positive");
            }
            if (length >= MINIMUM_LENGTH_METERS && length <= MAXIMUM_LENGTH_METERS) {
                accepted++;
                acceptedSum += length;
            }
        }
        double mean = accepted == 0 ? Double.NaN : acceptedSum / accepted;
        return new WorkpieceInspection(accepted, lengths.length - accepted, mean);
    }
}
