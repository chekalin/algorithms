package com.example.sorting;

import com.google.common.base.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static com.example.sorting.Inversions.findInversions;
import static com.example.sorting.Inversions.findInversionsBruteForce;
import static com.example.util.IOUtils.readIntegersFromFile;
import static com.example.util.TestDataGenerator.createArrayOfRandomIntegers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InversionsTest {

    @Test
    public void testBruteForceCorrectness() throws Exception {
        assertThat(findInversionsBruteForce(new int[]{1, 2}), is(0L));
        assertThat(findInversionsBruteForce(new int[]{2, 1}), is(1L));
        assertThat(findInversionsBruteForce(new int[]{1, 2, 3}), is(0L));
        assertThat(findInversionsBruteForce(new int[]{1, 3, 2}), is(1L));
        assertThat(findInversionsBruteForce(new int[]{3, 1, 2}), is(2L));
        assertThat(findInversionsBruteForce(new int[]{3, 2, 1}), is(3L));
        assertThat(findInversionsBruteForce(new int[]{3, 2, 1}), is(3L));
        assertThat(findInversionsBruteForce(new int[]{1, 3, 5, 2, 4, 6}), is(3L));
        assertThat(findInversionsBruteForce(new int[]{1, 3, 6, 5, 4, 2}), is(7L));
        assertThat(findInversionsBruteForce(new int[]{6, 5, 4, 3, 2, 1}), is(15L));
    }

    @Test
    public void testCorrectness() throws Exception {
        assertThat(findInversions(new int[]{1, 2}), is(0L));
        assertThat(findInversions(new int[]{2, 1}), is(1L));
        assertThat(findInversions(new int[]{1, 2, 3}), is(0L));
        assertThat(findInversions(new int[]{1, 3, 2}), is(1L));
        assertThat(findInversions(new int[]{3, 1, 2}), is(2L));
        assertThat(findInversions(new int[]{3, 2, 1}), is(3L));
        assertThat(findInversions(new int[]{3, 2, 1}), is(3L));
        assertThat(findInversions(new int[]{1, 3, 5, 2, 4, 6}), is(3L));
        assertThat(findInversions(new int[]{1, 3, 6, 5, 4, 2}), is(7L));
        assertThat(findInversions(new int[]{6, 5, 4, 3, 2, 1}), is(15L));
    }

    @Test
    @Ignore("too slow")
    public void testBrutForcePerformance() throws Exception {
        for (int size = 1000; size <= 512000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            findInversionsBruteForce(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

    @Test
    public void testPerformance() throws Exception {
        for (int size = 10; size <= 4096000; size *= 2) {
            int[] testData = createArrayOfRandomIntegers(size);
            Stopwatch stopwatch = Stopwatch.createStarted();
            findInversions(testData);
            stopwatch.stop();
            System.out.printf("%7d %s\n", size, stopwatch);
        }
    }

    @Test
    public void testHomework1() throws Exception {
        int[] data = readIntegersFromFile("homework1/IntegerArray.txt", 100000);
        long result = findInversions(data);
        System.out.println("result = " + result);
    }

}