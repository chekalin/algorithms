package com.example.sorting;

import com.google.common.base.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import java.util.function.Function;

import static com.example.util.Matchers.sorted;
import static com.example.util.TestDataGenerator.createArrayOfRandomIntegers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortingPerformanceTest {

    @Test
    @Ignore("too slow")
    public void testBubbleSortPerformance() throws Exception {
        outputRunningTimes(BubbleSort::sort, 1000, 512000);
    }

    @Test
    public void testMergeSortPerformance() throws Exception {
        outputRunningTimes(MergeSort::sort, 1000, 512000);
    }

    @Test
    public void testHeapSortPerformance() throws Exception {
        outputRunningTimes(HeapSort::sort, 1000, 512000);
    }

    @Test
    public void testQuickSortPerformance() throws Exception {
        // running with default stack size you get StackOverflow at 137k
        outputRunningTimes(QuickSort::sort, 1000, 128000);
    }

    private void outputRunningTimes(Function<int[], int[]> sortingFunction, int startSize, int maxSize) {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        for (int size = startSize; size <= maxSize; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            stopwatch.start();
            testData = sortingFunction.apply(testData);
            stopwatch.stop();
            assertThat(testData, is(sorted()));
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

}