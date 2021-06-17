package sort.radix;

import java.util.stream.IntStream;

public class BinaryQuickSort {
    private int[] array;
    private BitwiseUtils bitwiseUtils;

    public BinaryQuickSort(int[] array, int bitsInWord, int bitsInRadix) {
        this.array = array;
        this.bitwiseUtils = new BitwiseUtils(bitsInWord, bitsInRadix);
    }

    public void sort() {
        binaryQuickSort(0, array.length - 1, 0);
    }

    private void binaryQuickSort(int l, int r, int position) {
        if (position > array.length || l >= r) {return;}
        int i = l, j = r;

        while (i < j) {

            while (bitwiseUtils.getDigit(array[i], position) == 0 && i < j) {
                i++;
            }
            while (bitwiseUtils.getDigit(array[j], position) == 1 && i < j) {
                j--;
            }

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        if (bitwiseUtils.getDigit(array[i], position) == 0) {
            i++;
        }

        binaryQuickSort(l, i-1, position+1);
        binaryQuickSort(i, r, position+1);

    }

    public void print(){
        IntStream.range(0, array.length).forEach(i -> System.out.print(array[i] + " "));
    }

    public static void main(String[] args) {
        int[] array = new int[]{47, 39, 61, 54, 33};
        BinaryQuickSort binaryQuickSort = new BinaryQuickSort(array, 7, 1);
        binaryQuickSort.sort();
        binaryQuickSort.print();
    }
}
