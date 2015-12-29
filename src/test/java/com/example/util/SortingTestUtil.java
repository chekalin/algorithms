package com.example.util;

import com.google.common.base.Function;

import java.util.Arrays;

import static com.example.util.Matchers.sorted;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortingTestUtil {

    public static void testSortCorrectnessOnBasicCases(Function<int[], int[]> sortingFunction) {
        int[][] testCases = {
                {1, 2},
                {2, 1},
                {1, 2, 3},
                {1, 3, 2},
                {2, 1, 3},
                {2, 3, 1},
                {3, 1, 2},
                {3, 2, 1}
        };
        for (int[] testCase : testCases) {
            assertThat("Test case: " + Arrays.toString(testCase), sortingFunction.apply(testCase), is(sorted()));
        }
    }
}
