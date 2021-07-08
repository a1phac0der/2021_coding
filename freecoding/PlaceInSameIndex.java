package freecoding;

import java.util.Arrays;

public class PlaceInSameIndex {
    public static String[] placeInMatchingIndex(String[] strArray) {
        boolean visited[] = new boolean[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            if (!visited[i] && strArray[i] != null) {
                int index = i;
                String prev = null;
                while (true) {
                    String current = strArray[index];
                    strArray[index] = prev;
                    prev = current;
                    if (visited[index]) {
                        break;
                    }
                    visited[index] = true;
                    if (prev == null) {
                        break;
                    }
                    index = current.charAt(0) - '0';
                }
            }
        }
        return strArray;
    }

    public static void main(String[] args) {
        String[] strArray = new String[]{"3three", "2two", "5five", "1one", "4four", null};
        System.out.println(Arrays.asList(strArray));
        System.out.println(Arrays.asList(placeInMatchingIndex(strArray)));
    }

}
