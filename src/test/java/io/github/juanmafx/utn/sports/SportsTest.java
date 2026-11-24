package io.github.juanmafx.utn.sports;

import io.github.juanmafx.utn.sports.Competition.Entry;
import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.util.List;

public class SportsTest {
    @Test
    void competitionDeskCheckFromStatement() {
        Competition result = Competition.summarize(List.of(new Entry(2, "Rosa", 14), new Entry(9, "Alejandro", 16),
                new Entry(23, "Julieta", 5), new Entry(20, "Ramiro", 4), new Entry(15, "Susana", 10), new Entry(6, "Juan", 20)));
        Check.equal(6, result.competitors());
        Check.near(69.0 / 6.0, result.meanScore(), 1e-12);
        Check.equal(2, result.scoresBelowTen());
        Check.equal(List.of("Juan"), result.winners());
    }

    @Test
    void competitionReportsTiedWinners() {
        Competition result = Competition.summarize(List.of(new Entry(1, "A", 5), new Entry(2, "B", 5)));
        Check.equal(List.of("A", "B"), result.winners());
    }

    @Test
    void competitionRejectsEmptyAndInvalid() {
        Check.throwsType(IllegalArgumentException.class, () -> Competition.summarize(List.of()));
        Check.throwsType(IllegalArgumentException.class, () -> new Entry(0, "A", 1));
    }

    @Test
    void leagueAverageCountsEveryMatch() {
        LeagueAverage average = LeagueAverage.of(2, 1, 1);
        Check.equal(4, average.matches());
        Check.near(2, average.pointsPerMatch(), 1e-12);
    }

    @Test
    void leagueAverageRejectsNoMatches() {
        Check.throwsType(IllegalArgumentException.class, () -> LeagueAverage.of(0, 0, 0));
    }

    @Test
    void leagueAverageStaysWithinPointBounds() {
        Check.forAll(901L, 2000, random -> new int[] {random.nextInt(0, 40), random.nextInt(0, 40), random.nextInt(1, 40)}, s -> {
            double value = LeagueAverage.of(s[0], s[1], s[2]).pointsPerMatch();
            Check.isTrue(value >= 0 && value <= LeagueAverage.WIN_POINTS, "bounds");
        });
    }
}
