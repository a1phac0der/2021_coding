package sort;

import java.util.stream.IntStream;

public class MergeSort {
    public static void sort(int[] array) {
        solve(array, 0, array.length - 1);
    }

    private static void solve(int[] array, int start, int end) {
        if (end - start == 0) {return;}
        if (end - start == 1) {
            if (array[start] > array[end]) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
            return;
        }

        int midIndex = start + ((end - start) / 2);
        solve(array, start, midIndex);
        solve(array, midIndex + 1, end);
        int[] merged = merge(array, start, midIndex, midIndex + 1, end);
        copyRange(array, merged, start, end);
    }

    private static void copyRange(int[] original_arr, int[] sub_arr, int from, int to){
        int sub_i = 0;
        for(int i=from; i <= to; i++){
            original_arr[i] = sub_arr[sub_i++];
        }
    }

    private static int[] merge(int[] array, int l_start, int l_end, int r_start, int r_end) {
        int[] merged = new int[(l_end - l_start) + (r_end - r_start) + 2];
        int l = l_start, r = r_start;
        int mergedIndex = 0;
        while (l <= l_end  && r <= r_end) {
            if (array[l] < array[r]) {
                merged[mergedIndex++] = array[l++];
            }
            else {
                merged[mergedIndex++] = array[r++];
            }
        }

        while (l <= l_end) {
            merged[mergedIndex++] = array[l++];
        }

        while (r <= r_end) {
            merged[mergedIndex++] = array[r++];
        }

        return merged;

    }

    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 5, 7, 4, 19, 111, 4, 9, 30, 62, 62, 20};
        sort(array);
        IntStream.range(0, array.length).forEach(i -> System.out.print(array[i] + " "));
    }
}
