package io.github.juanmafx.utn.arithmetic;

public record ClockDuration(long hours, int minutes, int seconds) {
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int SECONDS_PER_HOUR = 3600;

    public ClockDuration {
        if (hours < 0 || minutes < 0 || minutes >= 60 || seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException("duration fields out of range");
        }
    }

    public static ClockDuration fromTotalSeconds(long totalSeconds) {
        if (totalSeconds < 0) {
            throw new IllegalArgumentException("total seconds must be non-negative");
        }
        long hours = totalSeconds / SECONDS_PER_HOUR;
        long remainder = totalSeconds % SECONDS_PER_HOUR;
        return new ClockDuration(hours, (int) (remainder / SECONDS_PER_MINUTE), (int) (remainder % SECONDS_PER_MINUTE));
    }

    public long toTotalSeconds() {
        return Math.addExact(Math.multiplyExact(hours, SECONDS_PER_HOUR), (long) minutes * SECONDS_PER_MINUTE + seconds);
    }

    public String format() {
        return hours + ":" + minutes + ":" + seconds;
    }
}
