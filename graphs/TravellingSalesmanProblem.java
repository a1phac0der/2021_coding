package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TravellingSalesmanProblem {
    private int        size;
    private double[][] dist;
    private boolean    solved;
    private double     minDistance = Double.POSITIVE_INFINITY;
    List<Integer> tour;
    private int        start;
    private double[][] memo;
    private final int FINAL_STATE;

    public TravellingSalesmanProblem(double[][] dist, int start) {
        this.dist = dist;
        this.start = start;
        this.size = dist.length;
        this.memo = new double[size][(int) Math.pow(2, size)];
        this.FINAL_STATE = (1 << size) -1;
    }

    public List<Integer> getTour() {
        if (!solved) {
            solve();
        }
        return tour;
    }

    private void setup() {
        for (int i = 0; i < size; i++) {
            if (i == start) {continue;}
            memo[i][1 << start | 1 << i] = dist[start][i];
        }
    }

    private Set<Integer> combinations(int sub_size){
        Set<Integer> allSets = new HashSet();
        combinations(allSets, sub_size, 0, 0);
        return allSets;
    }

    private void combinations(Set<Integer> allSets, int length, int at, int bitInt){
        if(length == 0){
            allSets.add(bitInt);
            return;
        }
        for(int i=at; i<size; i++){
            bitInt |= (1 << i);
            combinations(allSets, length-1, i+1, bitInt);
            bitInt &= ~(1 << i);
        }
    }

    private boolean notIn(int allBits, int bit){
        return (allBits & bit) == 0;
    }

    private void solve() {
        setup();
        //capture min distance for all the path lengths & combinations.
        for(int sub_length = 3; sub_length <= size; sub_length++){
            for(int bitInt: combinations(sub_length)){
                if(notIn(bitInt, start)) continue;
                for(int current=0; current < size; current++){
                    int previous_bits = bitInt & (~ (1 << current));
                    double minDistance = Double.POSITIVE_INFINITY;
                    for(int prev=0; prev < size; prev++){
                        if(prev == start || prev == current || notIn(previous_bits, prev)){
                            continue;
                        }
                        if(memo[prev][previous_bits] + memo[prev][current] < minDistance){
                            minDistance = memo[prev][previous_bits] + memo[prev][current];
                        }
                    }
                    memo[current][bitInt] = minDistance;
                }
            }
        }

        //get min cost for the shortest path
        for(int i=0; i < size; i++){
            if(i == start){
                continue;
            }
            if(memo[i][FINAL_STATE] + memo[i][start] < minDistance){
                minDistance = memo[i][FINAL_STATE] + memo[i][start];
            }
        }

        //get complete route for the shortest path
        if(minDistance == Double.POSITIVE_INFINITY) {return;}
        solved = true;
        List<Integer> path = new ArrayList();
        path.add(start);
        int next = start;
        int STATE = FINAL_STATE;
        for(int i=1; i<size; i++){
            int min_at = -1;
            for(int at=0; at < size; at++){
                if(at == next || notIn(STATE, at)){continue;}
                if(min_at == -1){min_at=at;}
                if((memo[at][STATE] + memo[at][start]) < (memo[min_at][STATE])){
                    min_at = at;
                }
            }
            next = min_at;
            STATE &= ~(1 << min_at);
            path.add(min_at);
        }

        path.add(start);
    }

}
