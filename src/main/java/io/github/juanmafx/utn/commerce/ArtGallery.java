package io.github.juanmafx.utn.commerce;

import java.util.Arrays;

public record ArtGallery(boolean allBeforeTwentiethCentury, int recentCount, boolean renewStock) {
    private static final int LAST_YEAR_BEFORE_TWENTIETH_CENTURY = 1900;
    private static final int RECENT_AGE_LIMIT = 10;

    public static ArtGallery assess(int currentYear, int... creationYears) {
        if (creationYears.length == 0) {
            throw new IllegalArgumentException("at least one painting is required");
        }
        if (Arrays.stream(creationYears).anyMatch(year -> year > currentYear)) {
            throw new IllegalArgumentException("creation year cannot be in the future");
        }
        boolean allBefore = Arrays.stream(creationYears).allMatch(year -> year <= LAST_YEAR_BEFORE_TWENTIETH_CENTURY);
        int recent = (int) Arrays.stream(creationYears).filter(year -> (long) currentYear - year < RECENT_AGE_LIMIT).count();
        return new ArtGallery(allBefore, recent, recent == 0);
    }
}
