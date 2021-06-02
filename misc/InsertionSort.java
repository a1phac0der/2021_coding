package misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertionSort {
	public static void sortArray(int[] array){
		for(int i=0; i<array.length; i++){
			if(i>0){
				int j=i;
					while(j>0 && array[j]<array[j-1])
					{
						int temp = array[j];
						array[j] = array[j-1];
						array[j-1] = temp;
						j--;
					}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		List<Integer> intlist = new ArrayList<Integer>();
		while(true){
			int n = scanner.nextInt();
			if(n == 9999)
				break;
			intlist.add(n);
		}
		scanner.close();
		int[] array = new int[intlist.size()];
		for(int i=0; i<array.length;i++){
			array[i]=intlist.get(i);
		}
		for(int j=0;j<array.length;j++){
			System.out.print(array[j]+" ");
		}
		System.out.println();
		sortArray(array);
		for(int j=0;j<array.length;j++){
			System.out.print(array[j]+" ");
		}
	}
}
