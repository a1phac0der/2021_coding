package google.kick_start;

import java.util.Scanner;

public class Countdown {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int c = 0; c < testCases; c++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] array = new int[n];
            for (int j = 0; j < n; j++) {
                array[j] = scanner.nextInt();
            }

            int count = 0;
            for (int i = k - 1; i < array.length; i++) {
                boolean seq = true;
                for (int j = 0; j < k && seq; j++) {
                    if (array[i - j] != j + 1) {
                        seq = false;
                    }
                }
                if (seq) {count++;}

            }

            System.out.println("Case #" + (c + 1) + ": " + count);

        }
    }
}
