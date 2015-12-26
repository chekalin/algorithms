package com.example;

import com.google.common.base.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static com.example.Sorting.*;
import static com.example.util.IOUtils.readIntegersFromFile;
import static com.example.util.TestDataGenerator.createArrayOfRandomIntegers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortingTest {

    @Test
    public void testBubbleSortCorrectness() throws Exception {
        assertThat(bubbleSort(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(bubbleSort(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(bubbleSort(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(bubbleSort(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(bubbleSort(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
    }

    @Test
    public void testMergeSortCorrectness() throws Exception {
        assertThat(mergeSort(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(mergeSort(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(mergeSort(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(mergeSort(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(mergeSort(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
    }

    @Test
    public void testQuickSortCorrectness() throws Exception {
        assertThat(quickSort(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(quickSort(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(quickSort(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(quickSort(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(quickSort(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
    }

    @Test
    public void testQuickSortFinalCorrectness() throws Exception {
        assertThat(quickSortFinal(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(quickSortFinal(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(quickSortFinal(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(quickSortFinal(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(quickSortFinal(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
    }

    @Test
    public void testQuickSortMedianCorrectness() throws Exception {
        assertThat(quickSortMedianOfThree(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(quickSortMedianOfThree(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(quickSortMedianOfThree(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(quickSortMedianOfThree(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(quickSortMedianOfThree(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
        assertThat(quickSortMedianOfThree(new int[]{2, 1, 3}), is(new int[]{1, 2, 3}));
    }

    @Test
    @Ignore("too slow")
    public void testBubbleSortPerformance() throws Exception {
        for (int size = 1000; size <= 512000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            bubbleSort(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

    @Test
    public void testMergeSortPerformance() throws Exception {
        for (int size = 1000; size <= 512000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            mergeSort(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

    @Test
    public void testQuickSortPerformance() throws Exception {
        // running with default stack size you get StackOverflow at 137k
        for (int size = 1000; size <= 128000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            quickSort(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

    @Test
    public void testHomework2() throws Exception {
        quickSort(readIntegersFromFile("homework2/10.txt", 10));
        assertThat(Sorting.comparisonCount, is(25));
        quickSortFinal(readIntegersFromFile("homework2/10.txt", 10));
        assertThat(Sorting.comparisonCount, is(29));
        quickSortMedianOfThree(readIntegersFromFile("homework2/10.txt", 10));
        assertThat(Sorting.comparisonCount, is(21));

        quickSort(readIntegersFromFile("homework2/100.txt", 100));
        assertThat(Sorting.comparisonCount, is(615));
        quickSortFinal(readIntegersFromFile("homework2/100.txt", 100));
        assertThat(Sorting.comparisonCount, is(587));
        quickSortMedianOfThree(readIntegersFromFile("homework2/100.txt", 100));
        assertThat(Sorting.comparisonCount, is(518));

        quickSort(readIntegersFromFile("homework2/1000.txt", 1000));
        assertThat(Sorting.comparisonCount, is(10297));
        quickSortFinal(readIntegersFromFile("homework2/1000.txt", 1000));
        assertThat(Sorting.comparisonCount, is(10184));
        quickSortMedianOfThree(readIntegersFromFile("homework2/1000.txt", 1000));
        assertThat(Sorting.comparisonCount, is(8921));

        quickSort(readIntegersFromFile("homework2/QuickSort.txt", 10000));
        System.out.println("Comparisons with first element: " + Sorting.comparisonCount);
        quickSortFinal(readIntegersFromFile("homework2/QuickSort.txt", 10000));
        System.out.println("Comparisons with final element: " + Sorting.comparisonCount);
        quickSortMedianOfThree(readIntegersFromFile("homework2/QuickSort.txt", 10000));
        System.out.println("Comparisons with median-of-three element: " + Sorting.comparisonCount);
    }

    @Test
    public void testMedianOfThree() throws Exception {
        assertThat(findMedianIndex(new int[]{1, 2}, 0, 1), is(0));
        assertThat(findMedianIndex(new int[]{1, 2, 3}, 0, 2), is(1));
        assertThat(findMedianIndex(new int[]{3, 1, 2}, 0, 2), is(2));
        assertThat(findMedianIndex(new int[]{4, 5, 6, 7}, 0, 3), is(1));
        assertThat(findMedianIndex(new int[]{8, 2, 4, 5, 7, 1}, 0, 5), is(2));
        assertThat(findMedianIndex(new int[]{3, 9, 8, 4, 6, 10, 2, 5, 7, 1}, 0, 9), is(0));
        assertThat(findMedianIndex(new int[]{1, 2, 3, 4, 6, 9, 5, 7, 8}, 3, 8), is(8));
    }

}