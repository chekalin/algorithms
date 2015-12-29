package com.example.sorting;

import com.example.util.ArrayUtils;

import java.util.Arrays;

public class QuickSort {

    public static int comparisonCount = 0;

    public static int[] sort(int[] array) {
        comparisonCount = 0;
        sort(array, 0, array.length - 1);
        return array;
    }

    private static void sort(int[] array, int start, int end) {
        if (end <= start) {
            return;
        }
        int pivotIndex = partition(array, start, end);
        sort(array, start, pivotIndex - 1);
        sort(array, pivotIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        comparisonCount += end - start;
        int pivotElement = array[start];
        int boundary = start + 1;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < pivotElement) {
                ArrayUtils.swap(array, i, boundary);
                boundary++;
            }
        }
        ArrayUtils.swap(array, start, boundary - 1);
        return boundary - 1;
    }

    public static int[] sortUsingFinalPivot(int[] array) {
        comparisonCount = 0;
        sortUsingFinalPivot(array, 0, array.length - 1);
        return array;
    }

    private static void sortUsingFinalPivot(int[] array, int start, int end) {
        if (end <= start) {
            return;
        }
        ArrayUtils.swap(array, start, end);
        int pivotIndex = partition(array, start, end);
        sortUsingFinalPivot(array, start, pivotIndex - 1);
        sortUsingFinalPivot(array, pivotIndex + 1, end);
    }

    public static int[] sortUsingMedianOfThree(int[] array) {
        comparisonCount = 0;
        sortUsingMedianOfThree(array, 0, array.length - 1);
        return array;
    }

    private static void sortUsingMedianOfThree(int[] array, int start, int end) {
        if (end <= start) {
            return;
        }
        int median = findMedianIndex(array, start, end);
        ArrayUtils.swap(array, start, median);
        int pivotIndex = partition(array, start, end);
        sortUsingMedianOfThree(array, start, pivotIndex - 1);
        sortUsingMedianOfThree(array, pivotIndex + 1, end);
    }

    static int findMedianIndex(int[] array, int start, int end) {
        int[] medianCandidates = new int[3];
        medianCandidates[0] = array[start];
        medianCandidates[1] = array[(start + end) / 2];
        medianCandidates[2] = array[end];
        Arrays.sort(medianCandidates);
        int median = medianCandidates[1];
        if (array[start] == median) {
            return start;
        }
        if (array[(start + end) / 2] == median) {
            return (start + end) / 2;
        } else {
            return end;
        }
    }
}
