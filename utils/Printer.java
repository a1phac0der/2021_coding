package utils;

import java.util.stream.IntStream;

public class Printer {
    public static void print(Object[] array) {
        System.out.println("---------------");
        IntStream.range(0, array.length).forEach(i -> System.out.println(array[i]));
        System.out.println("---------------");
    }
}
