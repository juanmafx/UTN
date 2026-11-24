package io.github.juanmafx.utn.simulation;

import java.util.Arrays;

public record WeatherStation(double[][] readings) {
    public record Peak(int location, int day, double temperature) {
    }

    public WeatherStation {
        if (readings.length == 0 || readings[0].length == 0) {
            throw new IllegalArgumentException("readings are required");
        }
        int days = readings[0].length;
        for (double[] row : readings) {
            if (row.length != days) {
                throw new IllegalArgumentException("every location needs the same number of days");
            }
            if (!Arrays.stream(row).allMatch(Double::isFinite)) {
                throw new IllegalArgumentException("readings must be finite");
            }
        }
        readings = deepCopy(readings);
    }

    @Override
    public double[][] readings() {
        return deepCopy(readings);
    }

    private static double[][] deepCopy(double[][] source) {
        return Arrays.stream(source).map(double[]::clone).toArray(double[][]::new);
    }

    public double overallMean() {
        return Arrays.stream(readings).flatMapToDouble(Arrays::stream).average().orElseThrow();
    }

    public double[] meanByLocation() {
        return Arrays.stream(readings).mapToDouble(row -> Arrays.stream(row).average().orElseThrow()).toArray();
    }

    public double[] meanByDay() {
        double[] means = new double[readings[0].length];
        for (double[] row : readings) {
            for (int day = 0; day < row.length; day++) {
                means[day] += row[day] / readings.length;
            }
        }
        return means;
    }

    public int hottestDay() {
        double[] means = meanByDay();
        int hottest = 0;
        for (int day = 1; day < means.length; day++) {
            if (means[day] > means[hottest]) {
                hottest = day;
            }
        }
        return hottest;
    }

    public Peak peak() {
        Peak peak = new Peak(0, 0, readings[0][0]);
        for (int location = 0; location < readings.length; location++) {
            for (int day = 0; day < readings[location].length; day++) {
                if (readings[location][day] > peak.temperature()) {
                    peak = new Peak(location, day, readings[location][day]);
                }
            }
        }
        return peak;
    }

    public static int[] readingsBelow(double threshold, int... temperatures) {
        return Arrays.stream(temperatures).filter(temperature -> temperature < threshold).toArray();
    }
}
