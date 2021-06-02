import java.util.*;

class HIndex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        List<List<Integer>> all_citations = new ArrayList();
        for (int i = 0; i < testCases; i++) {
            all_citations.add(new ArrayList<>());
            int n = scanner.nextInt();
            for (int j = 0; j < n; j++) {
                all_citations.get(i).add(scanner.nextInt());
            }
        }
        int test_number = 1;
        for (List<Integer> citations : all_citations) {
            System.out.print("Case #" + test_number++ + ":");
            printHIndex(citations);
            System.out.println();
        }
    }

    private static void printHIndex(List<Integer> citations) {
        int hIndex = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int citation : citations) {
            if (citation > hIndex) {
                map.put(citation, map.getOrDefault(citation, 0) + 1);
                count++;
                if (count > hIndex) {
                    hIndex++;
                    if (map.containsKey(hIndex)) {
                        count -= map.get(hIndex);
                        map.remove(hIndex);
                    }
                }
            }
            System.out.print(" " + hIndex);
        }
    }
}
