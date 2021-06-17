package sort.radix;

import java.util.stream.IntStream;

public class AbstractCountingSort<T> {
    protected T[] array;

    public void print() {
        System.out.println();
        IntStream.range(0, array.length).forEach(i -> System.out.print(array[i] + " "));
    }
}
