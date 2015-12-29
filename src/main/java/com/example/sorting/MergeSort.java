package com.example.sorting;

public class MergeSort {

    public static int[] sort(int[] array) {
        sort(array, 0, array.length - 1, new int[array.length]);
        return array;
    }

    private static void sort(int[] input, int lowBoundary, int highBoundary, int[] helperArray) {
        if (lowBoundary >= highBoundary) {
            return;
        }
        int middleBoundary = lowBoundary + (highBoundary - lowBoundary) / 2;
        sort(input, lowBoundary, middleBoundary, helperArray);
        sort(input, middleBoundary + 1, highBoundary, helperArray);
        merge(input, lowBoundary, middleBoundary, highBoundary, helperArray);
    }

    private static void merge(int[] input, int lowBoundary, int middleBoundary, int highBoundary, int[] helperArray) {
        int leftPos = lowBoundary;
        int rightPos = middleBoundary + 1;

        System.arraycopy(input, lowBoundary, helperArray, lowBoundary, highBoundary + 1 - lowBoundary);

        for (int k = lowBoundary; k <= highBoundary; k++) {
            if (leftPos > middleBoundary) {
                input[k] = helperArray[rightPos];
                rightPos++;
            } else if (rightPos > highBoundary) {
                input[k] = helperArray[leftPos];
                leftPos++;
            } else if (helperArray[leftPos] > helperArray[rightPos]) {
                input[k] = helperArray[rightPos];
                rightPos++;
            } else {
                input[k] = helperArray[leftPos];
                leftPos++;
            }
        }
    }
}
