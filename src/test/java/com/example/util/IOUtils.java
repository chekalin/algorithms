package com.example.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOUtils {

    public static int[] readIntegersFromFile(String filename, int numberOfIntegers) throws FileNotFoundException {
        int[] data = new int[numberOfIntegers];
        Scanner scanner = new Scanner(new File(IOUtils.class.getClassLoader().getResource(filename).getFile()));
        for (int i = 0; i < numberOfIntegers; i++) {
            data[i] = scanner.nextInt();
        }
        return data;

    }
}
