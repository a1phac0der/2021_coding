package leetcode;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> counts = new HashMap();
        Map<Character, Integer> lastIndex = new HashMap();
        for(char c: s1.toCharArray()){
            counts.put(c, counts.getOrDefault(c,0)+1);
        }

        for(int i=0; i < s2.length(); i++){
            if(counts.get(s2.charAt(i)) != null){
                int index = 0;
                Map<Character, Integer> tempCounts = new HashMap(counts);
                while(index < s1.length() && i < s2.length()){
                    if(counts.get(s2.charAt(i)) == null){
                        break;
                    }

                    if(tempCounts.get(s2.charAt(i)) <= 0){
                        if(counts.get(s2.charAt(i)) == index){
                            i++;
                            continue;
                        }else{
                            tempCounts = new HashMap(counts);
                            index = 0;
                            i = lastIndex.getOrDefault(s2.charAt(i),0) + 1;
                            lastIndex.clear();
                            continue;
                        }
                    }
                    if(lastIndex.get(s2.charAt(i)) == null){
                        lastIndex.put(s2.charAt(i), i);
                    }

                    tempCounts.put(s2.charAt(i), tempCounts.get(s2.charAt(i))-1);
                    i++;
                    index++;

                }
                if(index == s1.length()){
                    return true;
                }
            }
        }
        return false;
    }
}
