package com.example.sorting;

import org.junit.Test;

import static com.example.util.SortingTestUtil.testSortCorrectnessOnBasicCases;

public class BubbleSortTest {

    @Test
    public void testBubbleSortCorrectness() throws Exception {
        testSortCorrectnessOnBasicCases(BubbleSort::sort);
    }
}