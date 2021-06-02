package misc;

import java.util.Arrays;

public class SortAndMerge {
	public static int[] sortAndMerge(int[] data) {
		if (data.length == 1)
			return data;
		int[] sortedData = new int[data.length];
		int[] left = Arrays.copyOfRange(data, 0, data.length / 2);
		int[] right = Arrays.copyOfRange(data, data.length / 2, data.length);
		int[] sortedLeft = sortAndMerge(left);
		int[] sortedRight = sortAndMerge(right);
		int index = 0, leftindex = 0, rightindex = 0;
		while (index < sortedData.length && leftindex < sortedLeft.length && rightindex < sortedRight.length) {
			if (sortedLeft[leftindex] < sortedRight[rightindex]) {
				sortedData[index++] = sortedLeft[leftindex];
				leftindex++;
			} else {
				sortedData[index++] = sortedRight[rightindex];
				rightindex++;
			}

		}
		if (index < sortedData.length) {
			if (leftindex < sortedLeft.length) {
				while (leftindex < sortedLeft.length) {
					sortedData[index++] = sortedLeft[leftindex++];
				}
			} else {
				while (rightindex < sortedRight.length) {
					sortedData[index++] = sortedRight[rightindex++];
				}
			}
		}
		return sortedData;
	}
	
	public static void main(String[] args) {
		int[] sortedArray = sortAndMerge(new int[]{4, 23, 22, 1, 99, 33, 22, 45, 66, 12});
		for(int i=0; i<sortedArray.length; i++){
			System.out.println();
			System.out.println(sortedArray[i] + " ");
		}
	}
}
