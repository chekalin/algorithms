package com.example;

public class Inversions {

    public static long findInversionsBruteForce(int[] input) {
        long count = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static long findInversions(int[] input) {
        return findInversions(input, 0, input.length - 1, new int[input.length]);
    }

    private static long findInversions(int[] input, int lowBoundary, int highBoundary, int[] helperArray) {
        if (lowBoundary >= highBoundary) return 0;
        int middleBoundary = lowBoundary + (highBoundary - lowBoundary) / 2;
        long leftResult = findInversions(input, lowBoundary, middleBoundary, helperArray);
        long rightResult = findInversions(input, middleBoundary + 1, highBoundary, helperArray);
        long splitResult = countSplit(input, lowBoundary, middleBoundary, highBoundary, helperArray);
        return leftResult + rightResult + splitResult;
    }

    private static long countSplit(int[] input, int lowBoundary, int middleBoundary, int highBoundary, int[] helperArray) {
        long count = 0;
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
                // all items left on the left side are inversions
                count += middleBoundary + 1 - leftPos;
            } else {
                input[k] = helperArray[leftPos];
                leftPos++;
            }
        }

        return count;
    }

}
