package com.example.sorting;

import com.example.util.SortingTestUtil;
import org.junit.Test;

import static com.example.sorting.HeapSort.createMaxHeap;
import static com.example.sorting.HeapSort.leftChildIndex;
import static com.example.sorting.HeapSort.rightChildIndex;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HeapSortTest {

    @Test
    public void testHeapSortCorrectness() throws Exception {
        SortingTestUtil.testSortCorrectnessOnBasicCases(HeapSort::sort);
    }

    @Test
    public void shouldCreateMaxHeap() throws Exception {
        assertThat(createMaxHeap(new int[]{1, 3, 2}), is(new int[]{3, 1, 2}));
    }

    @Test
    public void shouldReturnChildrenIndexGivenParentIndex() throws Exception {
        assertThat(leftChildIndex(0), is(1));
        assertThat(leftChildIndex(1), is(3));
        assertThat(leftChildIndex(2), is(5));
        assertThat(leftChildIndex(3), is(7));
        assertThat(rightChildIndex(0), is(2));
        assertThat(rightChildIndex(1), is(4));
        assertThat(rightChildIndex(2), is(6));
        assertThat(rightChildIndex(3), is(8));
    }
}