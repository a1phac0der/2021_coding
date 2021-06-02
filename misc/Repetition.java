import java.util.HashMap;
import java.util.Map;

public class Repetition {

    public static void main(String[] args) {
        System.out.println(getSequence("aaabbbcc", 2).equals("aabbcc"));
        System.out.println(getSequence("abcd", 0).equals(""));
        System.out.println(getSequence("aaabbbccaaaaaaa", 1).equals("abca"));

    }

    public static String getSequence(String input, int repetition) {
        if (repetition == 0 || input.isEmpty())
            return "";

        String output = input.charAt(0) + "";
        Map<Character, Integer> charCount = new HashMap<>();
        char previousChar = input.charAt(0);

        int i = 1;

        charCount.put(input.charAt(0), 1);
        while (i < input.length()) {
            if (input.charAt(i) == previousChar) {
                charCount.put(input.charAt(i), charCount.get(input.charAt(i)) + 1);
                if (charCount.get(input.charAt(i)) <= repetition) {
                    output = output + input.charAt(i);
                } else {
                    char currentChar = input.charAt(i);
                    while (i < input.length() && currentChar == input.charAt(i)) {
                        i++;
                    }
                    i--;
                }
            } else {
                charCount.put(input.charAt(i), 1);
                output = output + input.charAt(i);
            }

            previousChar = input.charAt(i);
            i++;
        }
        return output;
    }
}
