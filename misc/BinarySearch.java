package misc;

public class BinarySearch {

	public static int search(int[] sortedArray, int from, int to, int element) {
		System.out.println("from: " + from + " To: " + to);
		if (from == to) {
			if (sortedArray[from] == element)
				return from;
			else
				return -1;
		}
		int mid = from + ((to - from) / 2);
		if (element == sortedArray[mid])
			return mid;
		if (element < sortedArray[mid]) {
			return search(sortedArray, from, mid, element);
		} else {
			return search(sortedArray, mid + 1, to, element);
		}
	}

	public static void main(String[] args) {
		SortedArray sortedArray = new SortedArray(new int[] { 12, 14, 19, 30, 31, 31, 32, 33 });
		int index = sortedArray.find(31);
		sortedArray.printAllOccurrences(index);
	}

	public static class SortedArray {
		int[] array;

		public SortedArray(int[] array) {
			this.array = array;
		}

		public int find(int element) {
			return search(array, 0, array.length, element);
		}

		public void printAllOccurrences(int index) {
			int element = array[index];
			while (array[index] == element) {
				index--;
			}
			while (array[++index] == element) {
				System.out.println(index);
			}
		}
	}
}
