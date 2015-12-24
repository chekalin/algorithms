package com.example;

public class Sorting {

    public static int[] bubbleSort(int[] array) {
        boolean swapped;
        int sortedItemsAtTheBack = 0;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1 - sortedItemsAtTheBack; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
            sortedItemsAtTheBack++;
        } while (swapped);
        return array;
    }

    public static int[] mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1, new int[array.length]);
        return array;
    }

    private static void mergeSort(int[] input, int lowBoundary, int highBoundary, int[] helperArray) {
        if (lowBoundary >= highBoundary) return;
        int middleBoundary = lowBoundary + (highBoundary - lowBoundary) / 2;
        mergeSort(input, lowBoundary, middleBoundary, helperArray);
        mergeSort(input, middleBoundary + 1, highBoundary, helperArray);
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

    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length -1);
        return array;
    }

    private static void quickSort(int[] array, int start, int end) {
        if (end <= start) return;
        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivotElement = array[start];
        int boundary = start + 1;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < pivotElement) {
                swap(array, i, boundary);
                boundary++;
            }
        }
        swap(array, start, boundary - 1);
        return boundary - 1;
    }

    private static void swap(int[] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }
}
