import java.util.ArrayList;
import java.util.List;

public class MaxLogicalANDOfFourDigits {
    static int maxSum = 0;
    static int total_positions = 4;
    static int[] array = new int[]{1, 2, 3, 4, 5, 6};
    static List<Integer>[][] cache = new List[4][array.length];

    public static void main(String[] args) {
        for (int i = 0; i <= array.length-total_positions; i++) {
            calculateSum(array, 1, i);
            for (int j=0; j<cache[1][i].size(); j++){
                int sum = cache[1][i].get(j);
                maxSum = sum > maxSum ? sum : maxSum;
            }
        }
        System.out.println(maxSum);
    }

    public static void calculateSum(int[] array, int current_position, int current_index) {
        if (cache[current_position][current_index] != null) {
            return;
        } else {
            if (current_position + 1 == total_positions) {
                List<Integer> sums = new ArrayList<>();
                for (int index = current_index + 1; index < array.length; index++) {
                    sums.add(array[current_index] & array[index]);
                }
                cache[current_position][current_index] = sums;
            } else {
                List<Integer> sums = new ArrayList<>();
                for (int index = current_index + 1; index <= array.length - (total_positions - current_position); index++) {
                    calculateSum(array, current_position + 1, index);
                    for (int sum: cache[current_position+1][index]){
                        sums.add(array[current_index] & sum);
                    }
                    cache[current_position][current_index] = sums;
                }

            }
        }


    }

}
