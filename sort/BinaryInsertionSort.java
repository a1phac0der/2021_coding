package sort;

import java.util.stream.IntStream;

public class BinaryInsertionSort {
    public static void sort(int[] array){
        for(int at=1; at < array.length; at++){
            int insertionPoint = findInsertionPoint(array, array[at], 0, at-1);
            if(insertionPoint != -1){
                insertAt(array, insertionPoint, at);

            }
            for(int i=0; i < array.length; i++){
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    private static void insertAt(int[] array, int at, int from){
        int num = array[from];
        for(int i=from-1; i >= at; i--){
            array[i+1] = array[i];
        }
        array[at] = num;
    }

    private static int findInsertionPoint(int[] array, int num, int start, int end){
        if(end - start <= 1){
            if(num > array[end]){
                return -1;
            }else if(num > array[start]){
                return end;
            }else return start;
        }
        int midIndex = start + ((end - start)/2);
        if(array[midIndex] == num) {return midIndex;}
        if(num < array[midIndex]){
            return findInsertionPoint(array, num, start, midIndex);
        }else {
            return findInsertionPoint(array, num, midIndex+1, end);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{53, 10, 2, 45, 4, 7, 18, 4, 3,11, 53};
        sort(array);
        IntStream.range(0, array.length).forEach(i -> System.out.print(array[i] + " "));
    }

}
