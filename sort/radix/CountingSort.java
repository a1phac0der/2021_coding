package sort.radix;

import java.util.Arrays;

import utils.Printer;

public class CountingSort<T> {
    private int          keyRange;
    private BitwiseUtils bitwiseUtils;
    private int          bitsInWord;


    public CountingSort(int bitsInWord, int bitsInRadix) {
        this.keyRange = (1 << bitsInRadix);
        this.bitsInWord = bitsInWord;
        this.bitwiseUtils = new BitwiseUtils(bitsInWord, bitsInRadix);
    }

    private int getKey(T item, int position) {
        int digit = 0;
        if (item instanceof String && ((position - 1) / bitsInWord) < ((String) item).length()) {
            digit = ((String) item).charAt((position - 1) / bitsInWord);
        }
        else if (item instanceof Integer) {
            digit = (Integer) item;
        }
        return bitwiseUtils.getDigit(digit, ((position - 1) % bitsInWord) + 1);
    }

    public int[] sort(T[] array, int position, int start, int end) {
        if (array.length == 0) { return null; }
        int[] keyCounts = new int[keyRange + 1];
        for (int i = start; i <= end; i++) {
            keyCounts[getKey(array[i], position) + 1]++;
        }

        for (int i = 1; i <= keyRange; i++) {
            keyCounts[i] += keyCounts[i - 1];
        }

        int[] buckets = Arrays.copyOf(keyCounts, keyCounts.length);

        T[] auxiliaryArray = Arrays.copyOf(array, array.length);
        for (int i = start; i <= end; i++) {
            auxiliaryArray[start + keyCounts[getKey(array[i], position)]++] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = auxiliaryArray[i];
        }

        return buckets;
    }

    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort(7, 7);
        String[] strArr = new String[]{"phone", "bottle", "table", "laptop", "towel"};
        countingSort.sort(strArr, 22, 0, strArr.length - 1);
        Printer.print(strArr);
    }
}
