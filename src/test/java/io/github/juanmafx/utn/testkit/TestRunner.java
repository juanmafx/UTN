package io.github.juanmafx.utn.testkit;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public final class TestRunner {
    private record Outcome(String suite, String name, double seconds, String failure) {
    }

    private TestRunner() {
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("usage: TestRunner <test-classes-dir> <xml-report>");
        }
        Path classes = Path.of(args[0]);
        List<Outcome> outcomes = new ArrayList<>();
        for (String className : discover(classes)) {
            outcomes.addAll(runSuite(Class.forName(className)));
        }
        writeReport(Path.of(args[1]), outcomes);
        long failed = outcomes.stream().filter(outcome -> outcome.failure() != null).count();
        outcomes.stream().filter(outcome -> outcome.failure() != null)
                .forEach(outcome -> System.out.println("FAILED " + outcome.suite() + "." + outcome.name()
                        + ": " + outcome.failure()));
        System.out.println("tests=" + outcomes.size() + " passed=" + (outcomes.size() - failed) + " failed=" + failed);
        if (failed > 0 || outcomes.isEmpty()) {
            System.exit(1);
        }
    }

    private static List<String> discover(Path classes) throws IOException {
        try (Stream<Path> paths = Files.walk(classes)) {
            return paths.filter(path -> path.getFileName().toString().endsWith("Test.class"))
                    .map(path -> classes.relativize(path).toString())
                    .map(name -> name.substring(0, name.length() - ".class".length()).replace('/', '.'))
                    .filter(name -> !name.endsWith(".Test"))
                    .sorted()
                    .toList();
        }
    }

    private static List<Outcome> runSuite(Class<?> suite) throws ReflectiveOperationException {
        List<Outcome> outcomes = new ArrayList<>();
        List<Method> methods = Stream.of(suite.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparing(Method::getName))
                .toList();
        for (Method method : methods) {
            method.setAccessible(true);
            Object instance = suite.getDeclaredConstructor().newInstance();
            long start = System.nanoTime();
            String failure = null;
            try {
                method.invoke(instance);
            } catch (InvocationTargetException exception) {
                failure = String.valueOf(exception.getCause());
            }
            outcomes.add(new Outcome(suite.getName(), method.getName(), (System.nanoTime() - start) / 1e9, failure));
        }
        return outcomes;
    }

    private static void writeReport(Path report, List<Outcome> outcomes) throws IOException {
        long failures = outcomes.stream().filter(outcome -> outcome.failure() != null).count();
        double total = outcomes.stream().mapToDouble(Outcome::seconds).sum();
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append(String.format(Locale.ROOT, "<testsuite name=\"utn\" tests=\"%d\" failures=\"%d\" errors=\"0\" skipped=\"0\" time=\"%.3f\" timestamp=\"%s\">%n",
                outcomes.size(), failures, total, Instant.now().truncatedTo(ChronoUnit.SECONDS)));
        for (Outcome outcome : outcomes) {
            xml.append(String.format(Locale.ROOT, "  <testcase classname=\"%s\" name=\"%s\" time=\"%.4f\"",
                    escape(outcome.suite()), escape(outcome.name()), outcome.seconds()));
            if (outcome.failure() == null) {
                xml.append("/>\n");
            } else {
                xml.append(">\n    <failure message=\"").append(escape(outcome.failure())).append("\"/>\n  </testcase>\n");
            }
        }
        xml.append("</testsuite>\n");
        Files.createDirectories(report.toAbsolutePath().getParent());
        Files.writeString(report, xml.toString(), StandardCharsets.UTF_8);
    }

    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
