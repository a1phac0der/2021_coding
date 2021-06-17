package sort;

import java.util.stream.IntStream;

public class QuickSort {

    private static void sort(int[] array, int start, int end){
        if((end-start) < 1){return;}
        int partition = partition(array, start, end);
        sort(array, start, partition);
        sort(array, partition+1, end);
    }

    private static int partition(int[] array, int start, int end){
        int pivot = array[start + ((end-start)/2)];
        int left = start;
        int right = end;

        while(true){

            while(array[left] < pivot){
                left++;
            }

            while(array[right] > pivot){
                right--;
            }

            if(left >= right){
                return right;
            }

            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{53, 10, 2, 45, 4, 7, 18, 4, 3,11, 53};
        sort(array, 0, array.length-1);
        IntStream.range(0, array.length).forEach(i -> System.out.print(array[i] + " "));
    }
}
