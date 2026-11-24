package io.github.juanmafx.utn.cli;

import io.github.juanmafx.utn.arithmetic.Arithmetic;
import io.github.juanmafx.utn.arithmetic.Calculator;
import io.github.juanmafx.utn.arithmetic.ClockDuration;
import io.github.juanmafx.utn.arithmetic.NumberBases;
import io.github.juanmafx.utn.collections.Matrices;
import io.github.juanmafx.utn.collections.SearchAlgorithms;
import io.github.juanmafx.utn.commerce.Money;
import io.github.juanmafx.utn.commerce.ShiftWage;
import io.github.juanmafx.utn.commerce.SupplierPayment;
import io.github.juanmafx.utn.commerce.VehicleTax;
import io.github.juanmafx.utn.education.Grading;
import io.github.juanmafx.utn.geometry.QuadraticEquation;
import io.github.juanmafx.utn.geometry.Triangle;
import io.github.juanmafx.utn.health.BodyMassIndex;
import io.github.juanmafx.utn.simulation.BingoCard;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SplittableRandom;
import java.util.function.Function;

public final class Main {
    private record Command(String usage, int minimumArguments, Function<String[], String> action) {
    }

    private static final int MAX_MATRIX_SIZE = 1000;
    private static final Map<String, Command> COMMANDS = buildCommands();

    private Main() {
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }

    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length == 0 || "list".equals(args[0])) {
            COMMANDS.forEach((name, command) -> out.println(name + " " + command.usage()));
            return 0;
        }
        Command command = COMMANDS.get(args[0]);
        if (command == null) {
            err.println("unknown command; run 'list' to see the catalog");
            return 2;
        }
        String[] arguments = Arrays.copyOfRange(args, 1, args.length);
        if (arguments.length < command.minimumArguments()) {
            err.println("usage: " + args[0] + " " + command.usage());
            return 2;
        }
        try {
            out.println(command.action().apply(arguments));
            return 0;
        } catch (IllegalArgumentException | ArithmeticException exception) {
            err.println("invalid input: " + exception.getMessage());
            return 2;
        }
    }

    private static Map<String, Command> buildCommands() {
        Map<String, Command> commands = new LinkedHashMap<>();
        commands.put("calculate", new Command("<add|subtract|multiply|divide> <left> <right>", 3,
                a -> String.valueOf(Calculator.parse(a[0]).apply(Double.parseDouble(a[1]), Double.parseDouble(a[2])))));
        commands.put("duration", new Command("<total-seconds>", 1,
                a -> ClockDuration.fromTotalSeconds(Long.parseLong(a[0])).format()));
        commands.put("binary-to-decimal", new Command("<binary>", 1,
                a -> String.valueOf(NumberBases.binaryToDecimal(a[0]))));
        commands.put("decimal-to-binary", new Command("<value>", 1,
                a -> NumberBases.decimalToBinary(Long.parseLong(a[0]))));
        commands.put("even-sum", new Command("<count>", 1,
                a -> String.valueOf(Arithmetic.sumOfFirstEvensClosedForm(Integer.parseInt(a[0])))));
        commands.put("quadratic", new Command("<a> <b> <c>", 3,
                a -> QuadraticEquation.solve(Double.parseDouble(a[0]), Double.parseDouble(a[1]), Double.parseDouble(a[2])).toString()));
        commands.put("triangle", new Command("<side-a> <side-b> <side-c>", 3, a -> {
            Triangle triangle = new Triangle(Double.parseDouble(a[0]), Double.parseDouble(a[1]), Double.parseDouble(a[2]));
            return "area=" + triangle.area() + " perimeter=" + triangle.perimeter() + " shortest=" + triangle.shortestSide();
        }));
        commands.put("vehicle-tax", new Command("<private|taxi|hired_car> <model-year> <current-year>", 3,
                a -> VehicleTax.annualTax(VehicleTax.Kind.parse(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2])).toPlainString()));
        commands.put("wage", new Command("<day|night> <hours>", 2,
                a -> ShiftWage.dailyWage(ShiftWage.Shift.parse(a[0]), Money.parse(a[1])).toPlainString()));
        commands.put("supplier-payment", new Command("<A|B> <amount>", 2,
                a -> SupplierPayment.finalAmount(SupplierPayment.Category.parse(a[0]), Money.parse(a[1])).toPlainString()));
        commands.put("standing", new Command("<first-exam> <second-exam> <practical>", 3,
                a -> Grading.standing(Double.parseDouble(a[0]), Double.parseDouble(a[1]), Double.parseDouble(a[2])).name()));
        commands.put("bmi", new Command("<weight-kg> <height-m>", 2, a -> {
            BodyMassIndex index = BodyMassIndex.of(Double.parseDouble(a[0]), Double.parseDouble(a[1]));
            return String.format(java.util.Locale.ROOT, "%.2f %s", index.value(), index.category());
        }));
        commands.put("search", new Command("<target> <sorted-values...>", 2, a -> {
            int[] values = Arrays.stream(a, 1, a.length).mapToInt(Integer::parseInt).toArray();
            return String.valueOf(SearchAlgorithms.binarySearch(values, Integer.parseInt(a[0])));
        }));
        commands.put("matrix", new Command("<seed> <size> <exclusive-bound>", 3, a -> {
            int size = Integer.parseInt(a[1]);
            if (size > MAX_MATRIX_SIZE) {
                throw new IllegalArgumentException("matrix size must not exceed " + MAX_MATRIX_SIZE);
            }
            return Matrices.format(Matrices.random(size, Integer.parseInt(a[2]), new SplittableRandom(Long.parseLong(a[0]))));
        }));
        commands.put("bingo", new Command("<seed> <pick-1> <pick-2> <pick-3>", 4, a -> {
            BingoCard card = BingoCard.generate(new SplittableRandom(Long.parseLong(a[0])));
            int[] picks = Arrays.stream(a, 1, a.length).mapToInt(Integer::parseInt).toArray();
            return "card=" + card.numbers() + " matched=" + card.countMatches(picks) + " " + card.resultMessage(picks);
        }));
        return commands;
    }
}
