package com.example.util;

import java.util.Random;

public class TestDataGenerator {

    public static int[] createArrayOfRandomIntegers(int size) {
        int[] input = new int[size];
        Random random = new Random(1L);
        for (int i = 0; i < size; i++) {
            input[i] = random.nextInt(10);
        }
        return input;
    }

}
