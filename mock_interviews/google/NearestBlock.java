package mock_interviews.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NearestBlock {

    public static void main(String[] args) {
        Map<String, Boolean>[] blocks = new Map[5];
        Map<String, Double>[] minRight = new Map[5];
        Map<String, Double>[] minLeft = new Map[5];
        Map<String, Double>[] minDistance = new Map[5];

        Set<String> requirements = new HashSet<>();


        //fill the input data
        Map<String, Boolean> block0 = new HashMap<>();
        block0.put("gym", false);
        block0.put("school", true);
        block0.put("store", false);
        blocks[0] = block0;

        Map<String, Boolean> block1 = new HashMap<>();
        block1.put("gym", true);
        block1.put("school", false);
        block1.put("store", false);
        blocks[1] = block1;

        Map<String, Boolean> block2 = new HashMap<>();
        block2.put("gym", true);
        block2.put("school", true);
        block2.put("store", false);
        blocks[2] = block2;

        Map<String, Boolean> block3 = new HashMap<>();
        block3.put("gym", false);
        block3.put("school", true);
        block3.put("store", false);
        blocks[3] = block3;

        Map<String, Boolean> block4 = new HashMap<>();
        block4.put("gym", false);
        block4.put("school", true);
        block4.put("store", true);
        blocks[4] = block4;

        requirements.add("school");
        requirements.add("store");

        //initialise..
        for (int i = 0; i < blocks.length; i++) {
            minLeft[i] = new HashMap<>();
            minRight[i] = new HashMap<>();
            minDistance[i] = new HashMap<>();

            for (String place : blocks[i].keySet()) {
                minLeft[i].put(place, blocks[i].get(place) ? 0 : Double.POSITIVE_INFINITY);
                minRight[i].put(place, blocks[i].get(place) ? 0 : Double.POSITIVE_INFINITY);
            }
        }

        //calculate left min distance
        for (int i = 1; i < blocks.length; i++) {
            for (String req : requirements) {
                minLeft[i].put(req, Math.min(minLeft[i-1].get(req)+1, minLeft[i].get(req)));
                minRight[blocks.length-i-1].put(req, Math.min(minRight[blocks.length-i].get(req)+1, minRight[blocks.length-i-1].get(req)));
            }
        }

        for (int i=0; i < blocks.length; i++){
            for (String place: requirements){
                minDistance[i].put(place, Math.min(minLeft[i].get(place), minRight[i].get(place)));
            }
        }

        //get index of block with minimum distance for the requirements
        double overallMinDist = Double.POSITIVE_INFINITY;
        int minDistIndex = 0;
        for (int i=0; i < blocks.length; i++){
            double maxOfSubDistances = -1;
            for (String place: requirements){
                if (minDistance[i].get(place) > maxOfSubDistances){
                    maxOfSubDistances = minDistance[i].get(place);
                }
            }
            if (maxOfSubDistances < overallMinDist){
                overallMinDist = maxOfSubDistances;
                minDistIndex = i;
            }
        }

        System.out.println(minDistIndex);

    }


}
