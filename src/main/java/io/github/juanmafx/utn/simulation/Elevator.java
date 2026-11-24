package io.github.juanmafx.utn.simulation;

public final class Elevator {
    public static final int LOWEST_FLOOR = 0;
    public static final int HIGHEST_FLOOR = 20;
    public static final int CAPACITY = 8;

    public record Statistics(int trips, long floorsTraveled, long passengersCarried, int rejectedTrips) {
    }

    private int currentFloor = LOWEST_FLOOR;
    private int trips;
    private long floorsTraveled;
    private long passengersCarried;
    private int rejectedTrips;

    public void call(int floor) {
        requireFloor(floor);
        moveTo(floor);
    }

    public boolean travel(int destinationFloor, int passengers) {
        requireFloor(destinationFloor);
        if (passengers < 1) {
            throw new IllegalArgumentException("a trip needs at least one passenger");
        }
        if (passengers > CAPACITY) {
            rejectedTrips++;
            return false;
        }
        moveTo(destinationFloor);
        trips++;
        passengersCarried += passengers;
        return true;
    }

    public int currentFloor() {
        return currentFloor;
    }

    public Statistics statistics() {
        return new Statistics(trips, floorsTraveled, passengersCarried, rejectedTrips);
    }

    private void moveTo(int floor) {
        floorsTraveled += Math.abs(floor - currentFloor);
        currentFloor = floor;
    }

    private static void requireFloor(int floor) {
        if (floor < LOWEST_FLOOR || floor > HIGHEST_FLOOR) {
            throw new IllegalArgumentException("floor must be between 0 and 20");
        }
    }
}
