package com.example;

import com.google.common.base.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static com.example.Sorting.bubbleSort;
import static com.example.Sorting.mergeSort;
import static com.example.util.TestDataGenerator.createArrayOfRandomIntegers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortingTest {

    @Test
    public void testBubbleCorrectness() throws Exception {
        assertThat(bubbleSort(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(bubbleSort(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(bubbleSort(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(bubbleSort(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(bubbleSort(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
    }

    @Test
    public void testMergeCorrectness() throws Exception {
        assertThat(mergeSort(new int[]{1, 2}), is(new int[]{1, 2}));
        assertThat(mergeSort(new int[]{2, 1}), is(new int[]{1, 2}));
        assertThat(mergeSort(new int[]{1, 3, 2}), is(new int[]{1, 2, 3}));
        assertThat(mergeSort(new int[]{3, 1, 2}), is(new int[]{1, 2, 3}));
        assertThat(mergeSort(new int[]{3, 2, 1}), is(new int[]{1, 2, 3}));
    }

    @Test
    @Ignore("too slow")
    public void testBubblePerformance() throws Exception {
        for (int size = 1000; size <= 512000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            bubbleSort(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

    @Test
    public void testMergePerformance() throws Exception {
        for (int size = 1000; size <= 512000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            mergeSort(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

}