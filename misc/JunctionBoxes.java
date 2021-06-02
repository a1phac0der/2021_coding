import java.util.*;

public class JunctionBoxes {

    List<String> newBoxes = new ArrayList<>();
    List<String> orderedBoxes = new ArrayList<>();

    public static void main(String[] args) {
        int numberOfBoxes = 6;
        List<String> boxList = Arrays.asList("f3 52 54 31", "br8 eat nim did", "t2 13 121 98", "w1 has uni gry", "r1 box ape bit", "b4 xi me nu");
        System.out.println(new JunctionBoxes().orderedJunctionBoxes(numberOfBoxes, boxList));
    }

    public List<String> orderedJunctionBoxes(int numberOfBoxes, List<String> boxList){
        List<JunctionBox> junctionBoxList = processAllJunctionBoxes(boxList);
        Collections.sort(junctionBoxList);
        for (JunctionBox junctionBox: junctionBoxList){
            orderedBoxes.add(junctionBox.completeStr);
        }
        for (String newBox: newBoxes){
            orderedBoxes.add(newBox);
        }
        return orderedBoxes;
    }

    public List<JunctionBox> processAllJunctionBoxes(List<String> boxList){
        List<JunctionBox> junctionBoxList = new ArrayList<>();
        for(String str : boxList) {
            if(isOld(str)) {
                String[] tokens = str.split(" ", 2);
                JunctionBox junctionBox = new JunctionBox(tokens[0], tokens[1], str);
                junctionBoxList.add(junctionBox);
            } else {
                newBoxes.add(str);
            }
        }
        return junctionBoxList;
    }

    public static boolean isOld(String s) {
        String[] tokens = s.split(" ");
        if (tokens[1].matches("[0-9]+")) {
            return false;
        }
        return true;
    }


    class JunctionBox implements Comparable<JunctionBox>{
        String identifier;
        String version;
        String completeStr;

        public JunctionBox(String i, String v, String cS){
            this.identifier = i;
            this.version = v;
            this.completeStr = cS;
        }

        @Override
        public int compareTo(JunctionBox junctionBox) {
            if (this.version.compareTo(junctionBox.version) == 0){
                    if (this.identifier.compareTo(junctionBox.identifier) == 0){
                        return 0;
                    }else if (this.identifier.compareTo(junctionBox.identifier) > 0){
                        return 1;
                    }else {
                        return -1;
                    }
            }else if (this.version.compareTo(junctionBox.version) > 0){
                return 1;
            }else return -1;
        }
    }
}
