package misc;

public class QuickSort {
	public static void quickSort(int[] array, int lowerindex, int higherindex){
			int i = lowerindex;
			int j = higherindex;
			int pivot = array[lowerindex+((higherindex-lowerindex)/2)];
			while(i<=j){
			while(array[i] < pivot){
				i++;
			}
			while(array[j] > pivot){
				j--;
			}
			if(i <= j){
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
			}
			if(lowerindex<j){
				quickSort(array, lowerindex, j);
			}
			if(i<higherindex){
				quickSort(array, i, higherindex);
			}
	}
	
	public static void main(String[] args){
		int[] data = new int[]{34, 12, 2, 67, 2, 34, 89, 23};
		quickSort(data, 0, data.length-1);
		System.out.println("Sorted array:");
		for(int i=0; i<data.length; i++){
			System.out.println(data[i] + " ");
		}
	}
}
