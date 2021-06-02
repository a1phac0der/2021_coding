package google;

import java.util.Stack;
import java.util.stream.Stream;

public class ErrorThreshold {
    static Integer[] leftCounts;
    static Integer[] rightCounts;

    private static void getLeftCounts(int[] errorRates) {
        leftCounts = new Integer[errorRates.length];
        rightCounts = new Integer[errorRates.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < errorRates.length; i++) {
            int count = 0;
            while (!stack.isEmpty() && stack.peek() >= errorRates[i]) {
                count++;
                count += leftCounts[i - count];
                stack.pop();
            }
            leftCounts[i] = count;
            stack.push(errorRates[i]);
        }
        stack.clear();
    }

    private static boolean shouldFireAlert(int[] errorRates, int threshold) {
        getLeftCounts(errorRates);
        Stack<Integer> stack = new Stack<>();
        for (int i = errorRates.length - 1; i >= 0; i--) {
            int count = 0;
            while (!stack.isEmpty() && stack.peek() >= errorRates[i]) {
                count++;
                count += rightCounts[i + count];
                stack.pop();
            }
            rightCounts[i] = count;
            if ((leftCounts[i] + rightCounts[i] + 1) * errorRates[i] >= threshold) {
                return true;
            }
            stack.push(errorRates[i]);
        }
        return false;
    }

    private static void printCounts(){
        Stream.of(leftCounts).forEach(a -> System.out.print(a + " "));
        System.out.println();
        Stream.of(rightCounts).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(shouldFireAlert(new int[]{4, 9, 6, 40, 40, 40, 45, 50, 20, 50, 0, 4, 4}, 200));
    }
}
