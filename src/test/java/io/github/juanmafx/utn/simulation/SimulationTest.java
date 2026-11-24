package io.github.juanmafx.utn.simulation;

import io.github.juanmafx.utn.collections.Histogram;
import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.SplittableRandom;
import java.util.stream.IntStream;

public class SimulationTest {
    @Test
    void bingoCardHasFifteenDistinctNumbersInRange() {
        Check.forAll(801L, 2000, random -> BingoCard.generate(random), card -> {
            Check.equal(BingoCard.CARD_SIZE, new HashSet<>(card.numbers()).size());
            Check.isTrue(card.numbers().stream().allMatch(n -> n >= 1 && n <= BingoCard.MAXIMUM_NUMBER), "range");
        });
    }

    @Test
    void bingoCardIsSeedReproducible() {
        Check.equal(BingoCard.generate(new SplittableRandom(5)), BingoCard.generate(new SplittableRandom(5)));
    }

    @Test
    void bingoNumbersAreUniformlyDrawn() {
        SplittableRandom random = new SplittableRandom(802L);
        int[] counts = new int[BingoCard.MAXIMUM_NUMBER];
        for (int trial = 0; trial < 20_000; trial++) {
            BingoCard.generate(random).numbers().forEach(number -> counts[number - 1]++);
        }
        Check.isTrue(Histogram.chiSquaredAgainstUniform(counts) < 148.230, "df=99 critical value at alpha 0.001");
    }

    @Test
    void bingoMatchesCountDistinctPicks() {
        BingoCard card = BingoCard.generate(new SplittableRandom(1));
        int hit = card.numbers().get(0);
        Check.equal(1, card.countMatches(hit, hit, hit));
    }

    @Test
    void bingoReportsNoLuck() {
        BingoCard card = new BingoCard(IntStream.rangeClosed(1, 15).boxed().toList());
        Check.isTrue(card.resultMessage(50, 60, 70).startsWith("No luck"), "message");
        Check.equal("The player marked 2 number(s).", card.resultMessage(1, 2, 70));
    }

    @Test
    void fisherYatesPreservesElements() {
        Check.forAll(803L, 1000, random -> {
            int[] values = IntStream.range(0, random.nextInt(1, 200)).toArray();
            Shuffling.partialFisherYates(values, values.length, random);
            return values;
        }, values -> {
            int[] sorted = values.clone();
            Arrays.sort(sorted);
            Check.equal(IntStream.range(0, values.length).toArray(), sorted);
        });
    }

    @Test
    void fisherYatesIsUnbiasedOverPermutations() {
        SplittableRandom random = new SplittableRandom(804L);
        int[] counts = new int[24];
        for (int trial = 0; trial < 240_000; trial++) {
            int[] values = {0, 1, 2, 3};
            Shuffling.partialFisherYates(values, 4, random);
            counts[permutationRank(values)]++;
        }
        Check.isTrue(Histogram.chiSquaredAgainstUniform(counts) < 49.728, "df=23 critical value at alpha 0.001");
    }

    @Test
    void shuffleRejectsInvalidCount() {
        Check.throwsType(IllegalArgumentException.class,
                () -> Shuffling.partialFisherYates(new int[2], 3, new SplittableRandom(1)));
    }

    @Test
    void elevatorCarriesWithinCapacity() {
        Elevator elevator = new Elevator();
        elevator.call(3);
        Check.isTrue(elevator.travel(10, 8), "full load travels");
        Check.equal(new Elevator.Statistics(1, 10, 8, 0), elevator.statistics());
        Check.equal(10, elevator.currentFloor());
    }

    @Test
    void overloadedElevatorDoesNotMove() {
        Elevator elevator = new Elevator();
        Check.isTrue(!elevator.travel(5, 9), "overload rejected");
        Check.equal(0, elevator.currentFloor());
        Check.equal(1, elevator.statistics().rejectedTrips());
    }

    @Test
    void elevatorRejectsInvalidFloors() {
        Elevator elevator = new Elevator();
        Check.throwsType(IllegalArgumentException.class, () -> elevator.call(21));
        Check.throwsType(IllegalArgumentException.class, () -> elevator.travel(-1, 1));
        Check.throwsType(IllegalArgumentException.class, () -> elevator.travel(1, 0));
    }

    @Test
    void elevatorStaysInBoundsUnderRandomOperations() {
        Check.forAll(805L, 200, random -> random.ints(200, 0, 21).toArray(), floors -> {
            Elevator elevator = new Elevator();
            SplittableRandom load = new SplittableRandom(floors[0]);
            long expectedDistance = 0;
            int position = 0;
            for (int floor : floors) {
                if (elevator.travel(floor, load.nextInt(1, 12))) {
                    expectedDistance += Math.abs(floor - position);
                    position = floor;
                }
                Check.equal(position, elevator.currentFloor());
            }
            Check.equal(expectedDistance, elevator.statistics().floorsTraveled());
        });
    }

    @Test
    void playingCardLabels() {
        Check.equal("A", new PlayingCard(PlayingCard.Suit.SPADES, 14).rankLabel());
        Check.equal("10", new PlayingCard(PlayingCard.Suit.HEARTS, 10).rankLabel());
        Check.throwsType(IllegalArgumentException.class, () -> new PlayingCard(PlayingCard.Suit.CLUBS, 1));
    }

    @Test
    void weatherStationReport() {
        WeatherStation station = new WeatherStation(new double[][] {{10, 20}, {30, 40}});
        Check.near(25, station.overallMean(), 1e-12);
        Check.equal(new double[] {15, 35}, station.meanByLocation());
        Check.equal(new double[] {20, 30}, station.meanByDay());
        Check.equal(1, station.hottestDay());
        Check.equal(new WeatherStation.Peak(1, 1, 40), station.peak());
    }

    @Test
    void weatherStationIsDefensivelyCopied() {
        double[][] source = {{1, 2}};
        WeatherStation station = new WeatherStation(source);
        source[0][0] = 99;
        Check.near(1.5, station.overallMean(), 1e-12);
    }

    @Test
    void weatherMeansAgree() {
        Check.forAll(806L, 300, random -> {
            double[][] readings = new double[random.nextInt(1, 6)][random.nextInt(1, 9)];
            for (double[] row : readings) {
                Arrays.setAll(row, index -> random.nextDouble(-20, 45));
            }
            return readings;
        }, readings -> {
            WeatherStation station = new WeatherStation(readings);
            Check.near(station.overallMean(), Arrays.stream(station.meanByDay()).average().orElseThrow(), 1e-9);
            Check.near(station.overallMean(), Arrays.stream(station.meanByLocation()).average().orElseThrow(), 1e-9);
        });
    }

    @Test
    void readingsBelowThreshold() {
        Check.equal(new int[] {19, 5}, WeatherStation.readingsBelow(20, 19, 20, 21, 5));
    }

    private static int permutationRank(int[] permutation) {
        int rank = 0;
        for (int left = 0; left < permutation.length; left++) {
            int smaller = 0;
            for (int right = left + 1; right < permutation.length; right++) {
                if (permutation[right] < permutation[left]) {
                    smaller++;
                }
            }
            rank = rank * (permutation.length - left) + smaller;
        }
        return rank;
    }
}
