package google;

public class CountWords {
    public static void main(String[] args) {
        String sentence = "\"test one two\" three one two three \"four five six\"";
        System.out.println(countWords(sentence));
    }

    private static int countWords(String sentence) {
        int count = 0;
        char delimiter = ' ';
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == '\"') {
                delimiter = '\"';
                i++;
            }
            while (i < sentence.length() && sentence.charAt(i) != delimiter) {
                i++;
            }
            count++;
            delimiter = ' ';
            if(i < sentence.length() && sentence.charAt(i) == '\"'){
                i++;
            }
        }
        return count;
    }
}
