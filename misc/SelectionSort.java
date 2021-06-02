package misc;

public class SelectionSort {
	int[] array;
	
	public SelectionSort(int[] array){
		this.array = array;
	}
	
	public void sort(){
		int length = array.length;
		for(int i=0; i<length-1; i++){
			int min_index = i;
			for(int j=i+1; j<length; j++){
				if(array[j]<array[min_index])
					min_index = j;
			}
			if(min_index!=i){
				int temp = array[i];
				array[i] = array[min_index];
				array[min_index] = temp;
			}
		}
	}
	
	public void print(){
		System.out.println();
		for(int i=0; i<array.length; i++){
			System.out.print(array[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		SelectionSort selectionSort = new SelectionSort(new int[]{3, 2, 23, 1, 12, 17, 10, 5, 1, 19, 4});
		selectionSort.print();
		selectionSort.sort();
		selectionSort.print();
	}
}
