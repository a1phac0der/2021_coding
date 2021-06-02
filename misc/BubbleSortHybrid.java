package misc;

public class BubbleSortHybrid {
	public static void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	public static void printArray(int[] array){
		System.out.println();
		for(int i=0; i<array.length; i++){
			System.out.print(array[i]);
			if(i<array.length-1)
				System.out.print(" ");
		}
	}
	

	public static void main(String[] args) {
		int[] array = new int[]{3, 23, 55, 2, 13, 12, 19, 99, 1, 3, 2};
		printArray(array);
		sort(array);
		printArray(array);
		
	}
}
