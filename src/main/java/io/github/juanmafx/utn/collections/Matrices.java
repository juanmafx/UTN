package io.github.juanmafx.utn.collections;

import java.util.random.RandomGenerator;

public final class Matrices {
    private Matrices() {
    }

    public static int[][] random(int size, int exclusiveBound, RandomGenerator generator) {
        if (size <= 0 || exclusiveBound <= 0) {
            throw new IllegalArgumentException("size and bound must be positive");
        }
        int[][] matrix = new int[size][size];
        for (int[] row : matrix) {
            for (int column = 0; column < size; column++) {
                row[column] = generator.nextInt(exclusiveBound);
            }
        }
        return matrix;
    }

    public static int[][] sequentialEvens(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        int[][] matrix = new int[size][size];
        int value = 2;
        for (int[] row : matrix) {
            for (int column = 0; column < size; column++) {
                row[column] = value;
                value += 2;
            }
        }
        return matrix;
    }

    public static long[] rowSums(int[][] matrix) {
        requireSquare(matrix);
        long[] sums = new long[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int value : matrix[row]) {
                sums[row] += value;
            }
        }
        return sums;
    }

    public static long[] columnSums(int[][] matrix) {
        requireSquare(matrix);
        long[] sums = new long[matrix.length];
        for (int[] row : matrix) {
            for (int column = 0; column < row.length; column++) {
                sums[column] += row[column];
            }
        }
        return sums;
    }

    public static int[] mainDiagonal(int[][] matrix) {
        requireSquare(matrix);
        int[] diagonal = new int[matrix.length];
        for (int index = 0; index < matrix.length; index++) {
            diagonal[index] = matrix[index][index];
        }
        return diagonal;
    }

    public static int[] antiDiagonal(int[][] matrix) {
        requireSquare(matrix);
        int[] diagonal = new int[matrix.length];
        for (int index = 0; index < matrix.length; index++) {
            diagonal[index] = matrix[index][matrix.length - 1 - index];
        }
        return diagonal;
    }

    public static long leftHalfSum(int[][] matrix) {
        requireSquare(matrix);
        long sum = 0;
        for (int[] row : matrix) {
            for (int column = 0; column < row.length / 2; column++) {
                sum += row[column];
            }
        }
        return sum;
    }

    public static String format(int[][] matrix) {
        requireSquare(matrix);
        StringBuilder text = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                text.append(String.format(java.util.Locale.ROOT, "| %4d ", value));
            }
            text.append("|").append(System.lineSeparator());
        }
        return text.toString().stripTrailing();
    }

    private static void requireSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("matrix must not be empty");
        }
        for (int[] row : matrix) {
            if (row == null || row.length != matrix.length) {
                throw new IllegalArgumentException("matrix must be square");
            }
        }
    }
}
