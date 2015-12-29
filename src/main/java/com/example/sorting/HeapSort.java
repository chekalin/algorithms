package com.example.sorting;

import static com.example.util.ArrayUtils.swap;

public class HeapSort {

    public static int[] sort(int[] array) {
        array = createMaxHeap(array);
        for (int j = array.length - 1; j >= 0; j--) {
            swap(array, 0, j);
            maxHeapify(array, 0, j);
        }
        return array;
    }

    static int[] createMaxHeap(int[] array) {
        for (int i = (array.length / 2) - 1; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
        return array;
    }

    private static void maxHeapify(int[] array, int subTreeRootIndex, int heapSize) {
        int largest;
        int leftChildIndex = leftChildIndex(subTreeRootIndex);
        if (leftChildIndex < heapSize && array[leftChildIndex] > array[subTreeRootIndex]) {
            largest = leftChildIndex;
        } else {
            largest = subTreeRootIndex;
        }
        int rightChildIndex = rightChildIndex(subTreeRootIndex);
        if (rightChildIndex < heapSize && array[rightChildIndex] > array[largest]) {
            largest = rightChildIndex;
        }
        if (largest != subTreeRootIndex) {
            swap(array, largest, subTreeRootIndex);
            maxHeapify(array, largest, heapSize);
        }
    }

    static int leftChildIndex(int i) {
        return i * 2 + 1;
    }

    static int rightChildIndex(int i) {
        return leftChildIndex(i) + 1;
    }
}
