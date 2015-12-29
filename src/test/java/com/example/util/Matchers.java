package com.example.util;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Matchers {

    public static BaseMatcher<int[]> sorted() {
        return new BaseMatcher<int[]>() {

            private int[] array;
            private int failedPosition;

            @Override
            public boolean matches(Object item) {
                this.array = (int[]) item;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        failedPosition = i + 1;
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("sorted; Failed at position " + failedPosition);
            }
        };
    }
}
