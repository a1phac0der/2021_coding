package sort;

import java.util.stream.IntStream;

public class InsertionSort {
    private static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            sortFrom(array, i);
        }
    }

    public static void sortFrom(int[] array, int i) {
        int j = i;
        while (j > 0 && array[j] < array[j - 1]) {
            int temp = array[j];
            array[j] = array[j - 1];
            array[j - 1] = temp;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{53, 10, 2, 45, 4, 7, 18, 4, 3,11, 53};
        sort(array);
        IntStream.range(0, array.length).forEach(i -> System.out.print(array[i] + " "));
    }
}
