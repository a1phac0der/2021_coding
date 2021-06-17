package sort.radix;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import utils.Printer;

public class MSDRadixSort<T> {
    private T[]          array;
    private int          bitsInWord;
    private int          bitsInRadix;
    private int          wordCount = 1;
    private CountingSort countingSort;

    public MSDRadixSort(T[] array, int bitsInWord, int maxTxtLength, int bitsInRadix) {
        this.array = array;
        this.bitsInWord = bitsInWord;
        this.wordCount = maxTxtLength;
        this.bitsInRadix = bitsInRadix;
        this.countingSort = new CountingSort(bitsInWord, bitsInRadix);
    }

    public MSDRadixSort(T[] array, int bitsInWord, int bitsInRadix) {
        this.array = array;
        this.bitsInWord = bitsInWord;
        this.bitsInRadix = bitsInRadix;
        countingSort = new CountingSort(bitsInWord, bitsInRadix);
    }

    public void sort() {
        sort(1, 0, array.length - 1);
    }

    private void sort(int position, int start, int end) {
        if (position > (bitsInWord * wordCount) || start >= end) {
            return;
        }
        int[] buckets = countingSort.sort(array, position, start, end);
        for (int i = 0; i < buckets.length - 1; i++) {
            sort(position + bitsInRadix, start+buckets[i], start+buckets[i + 1] - 1);
        }
    }

    public static void main(String[] args) {
        /*String[] strArr = new String[]{"phone", "botule", "bottle", "towel", "laptrp", "laptop", "table", "laptoponthetable", "laptoponthedesk"};
        MSDRadixSort msdRadixSort = new MSDRadixSort(strArr, 7, 12, 7);
        msdRadixSort.sort();
        Printer.print(strArr);*/
        Integer[] array = new Integer[]{139, 129, 142, 172, 130};
        MSDRadixSort intRadix = new MSDRadixSort(array, 8, 2);
        intRadix.sort();
        Printer.print(array);
    }
}
