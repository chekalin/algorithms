package com.example.sorting;

import org.junit.Test;

import static com.example.sorting.QuickSort.findMedianIndex;
import static com.example.sorting.QuickSort.sort;
import static com.example.sorting.QuickSort.sortUsingFinalPivot;
import static com.example.sorting.QuickSort.sortUsingMedianOfThree;
import static com.example.util.IOUtils.readIntegersFromFile;
import static com.example.util.SortingTestUtil.testSortCorrectnessOnBasicCases;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QuickSortTest {

    @Test
    public void testQuickSortCorrectness() throws Exception {
        testSortCorrectnessOnBasicCases(QuickSort::sort);
    }

    @Test
    public void testQuickSortFinalCorrectness() throws Exception {
        testSortCorrectnessOnBasicCases(QuickSort::sortUsingFinalPivot);
    }

    @Test
    public void testQuickSortMedianCorrectness() throws Exception {
        testSortCorrectnessOnBasicCases(QuickSort::sortUsingMedianOfThree);
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


    @Test
    public void testHomework2() throws Exception {
        sort(readIntegersFromFile("homework2/10.txt", 10));
        assertThat(QuickSort.comparisonCount, is(25));
        sortUsingFinalPivot(readIntegersFromFile("homework2/10.txt", 10));
        assertThat(QuickSort.comparisonCount, is(29));
        sortUsingMedianOfThree(readIntegersFromFile("homework2/10.txt", 10));
        assertThat(QuickSort.comparisonCount, is(21));

        sort(readIntegersFromFile("homework2/100.txt", 100));
        assertThat(QuickSort.comparisonCount, is(615));
        sortUsingFinalPivot(readIntegersFromFile("homework2/100.txt", 100));
        assertThat(QuickSort.comparisonCount, is(587));
        sortUsingMedianOfThree(readIntegersFromFile("homework2/100.txt", 100));
        assertThat(QuickSort.comparisonCount, is(518));

        sort(readIntegersFromFile("homework2/1000.txt", 1000));
        assertThat(QuickSort.comparisonCount, is(10297));
        sortUsingFinalPivot(readIntegersFromFile("homework2/1000.txt", 1000));
        assertThat(QuickSort.comparisonCount, is(10184));
        sortUsingMedianOfThree(readIntegersFromFile("homework2/1000.txt", 1000));
        assertThat(QuickSort.comparisonCount, is(8921));

        sort(readIntegersFromFile("homework2/QuickSort.txt", 10000));
        System.out.println("Comparisons with first element: " + QuickSort.comparisonCount);
        sortUsingFinalPivot(readIntegersFromFile("homework2/QuickSort.txt", 10000));
        System.out.println("Comparisons with final element: " + QuickSort.comparisonCount);
        sortUsingMedianOfThree(readIntegersFromFile("homework2/QuickSort.txt", 10000));
        System.out.println("Comparisons with median-of-three element: " + QuickSort.comparisonCount);
    }

}