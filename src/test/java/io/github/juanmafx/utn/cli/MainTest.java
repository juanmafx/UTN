package io.github.juanmafx.utn.cli;

import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MainTest {
    private record Result(int status, String out, String err) {
    }

    private static Result run(String... args) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int status = Main.run(args, new PrintStream(out, true, StandardCharsets.UTF_8),
                new PrintStream(err, true, StandardCharsets.UTF_8));
        return new Result(status, out.toString(StandardCharsets.UTF_8).trim(), err.toString(StandardCharsets.UTF_8).trim());
    }

    @Test
    void listsCatalog() {
        Result result = run("list");
        Check.equal(0, result.status());
        Check.isTrue(result.out().contains("quadratic"), "catalog");
    }

    @Test
    void runsDuration() {
        Check.equal("1:14:12", run("duration", "4452").out());
    }

    @Test
    void runsVehicleTaxWithExplicitYear() {
        Check.equal("350", run("vehicle-tax", "taxi", "2020", "2026").out());
    }

    @Test
    void runsSearch() {
        Check.equal("2", run("search", "5", "1", "3", "5", "7").out());
    }

    @Test
    void bingoIsReproducibleForSeed() {
        Check.equal(run("bingo", "42", "1", "2", "3").out(), run("bingo", "42", "1", "2", "3").out());
    }

    @Test
    void matrixIsReproducibleForSeed() {
        Check.equal(run("matrix", "7", "5", "100").out(), run("matrix", "7", "5", "100").out());
    }

    @Test
    void unknownCommandFails() {
        Check.equal(2, run("nope").status());
    }

    @Test
    void missingArgumentsFail() {
        Check.equal(2, run("quadratic", "1").status());
    }

    @Test
    void malformedNumberFailsCleanly() {
        Result result = run("duration", "abc");
        Check.equal(2, result.status());
        Check.isTrue(result.err().startsWith("invalid input"), "message");
    }

    @Test
    void hugeExponentIsRejectedBeforeRounding() {
        Check.equal(2, run("wage", "day", "1e999999999").status());
        Check.equal(2, run("supplier-payment", "A", "1e-999999999").status());
    }

    @Test
    void oversizedMatrixIsRejected() {
        Check.equal(2, run("matrix", "1", "1001", "10").status());
    }

    @Test
    void yearOverflowIsRejected() {
        Check.equal(2, run("vehicle-tax", "private", "-2147483648", "2147483647").status());
    }

    @Test
    void divisionByZeroFailsCleanly() {
        Check.equal(2, run("calculate", "divide", "1", "0").status());
    }
}
