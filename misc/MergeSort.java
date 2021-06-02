import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSort {

    int[] original;

    public MergeSort(int[] original) {
        this.original = original;
    }

    public static void main(String[] args) {
        while (true) {
            String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date());
            str = str.split("\\.")[1];
            Long lngVal = Long.valueOf(str);
            if (lngVal > 1000) {
                System.out.println("GOT MICRO: " + str);
            }else
                System.out.println(str);
        }
    }

    public int[] sort() {
        int start = 0, end = original.length - 1, mid = (end - start) / 2;
        int[] working = new int[original.length];
        subSort(working, start, mid);
        subSort(working, mid + 1, end);
        merge(working, start, mid + 1, end);
        return original;
    }

    public void subSort(int[] working, int startIndex, int endIndex) {
        if (endIndex - startIndex > 0) {
            int midIndex = startIndex + ((endIndex - startIndex) / 2);
            subSort(working, startIndex, midIndex);
            subSort(working, midIndex + 1, endIndex);
            merge(working, startIndex, midIndex + 1, endIndex);
        }
    }

    public void merge(int[] working, int startIndex, int midIndex, int endIndex) {
        int i = startIndex, j = midIndex, k = startIndex;

        while (true) {
            if (i >= midIndex && j > endIndex)
                break;
            if (i >= midIndex || (j <= endIndex && original[j] < original[i])) {
                working[k] = original[j];
                j++;
            } else {
                working[k] = original[i];
                i++;
            }
            k++;
        }

        for (int l = startIndex; l <= endIndex; l++) {
            original[l] = working[l];
        }
    }
}
