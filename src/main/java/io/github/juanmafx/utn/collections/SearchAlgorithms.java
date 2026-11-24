package io.github.juanmafx.utn.collections;

import java.util.stream.IntStream;

public final class SearchAlgorithms {
    public static final int NOT_FOUND = -1;

    private SearchAlgorithms() {
    }

    public static int linearSearch(int[] values, int target) {
        for (int index = 0; index < values.length; index++) {
            if (values[index] == target) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    public static int[] allPositions(int[] values, int target) {
        return IntStream.range(0, values.length).filter(index -> values[index] == target).toArray();
    }

    public static int binarySearch(int[] sortedValues, int target) {
        for (int index = 1; index < sortedValues.length; index++) {
            if (sortedValues[index - 1] > sortedValues[index]) {
                throw new IllegalArgumentException("values must be sorted in ascending order");
            }
        }
        int low = 0;
        int high = sortedValues.length;
        while (low < high) {
            int middle = (low + high) >>> 1;
            if (sortedValues[middle] < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low < sortedValues.length && sortedValues[low] == target ? low : NOT_FOUND;
    }
}
