package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

class HelloWorld{
  public static void main(String[] args){
    /*int[] arr = new int[5];
    Arrays.fill(arr, 1234);
    arr[2] = 5;
    IntStream.range(0, 4).forEach(i -> System.out.println(arr[i]));*/
    System.out.println(Long.MAX_VALUE);
    long num = Long.MAX_VALUE;
    System.out.println((num+"").length());
    int count = 0;
    while(num != 0){
      count++;
      num = num/10;
    }
    System.out.println(count);
  }
}
