package com.example.sorting;

import org.junit.Test;

import static com.example.util.SortingTestUtil.testSortCorrectnessOnBasicCases;

public class MergeSortTest {

    @Test
    public void testMergeSortCorrectness() throws Exception {
        testSortCorrectnessOnBasicCases(MergeSort::sort);
    }

}