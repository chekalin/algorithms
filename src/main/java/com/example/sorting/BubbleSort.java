package com.example.sorting;

import com.example.util.ArrayUtils;

public class BubbleSort {

    public static int[] sort(int[] array) {
        boolean swapped;
        int sortedItemsAtTheBack = 0;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1 - sortedItemsAtTheBack; i++) {
                if (array[i] > array[i + 1]) {
                    ArrayUtils.swap(array, i, i + 1);
                    swapped = true;
                }
            }
            sortedItemsAtTheBack++;
        } while (swapped);
        return array;
    }
}
