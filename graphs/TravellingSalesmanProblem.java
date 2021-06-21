package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TravellingSalesmanProblem {
    private int        size;
    private double[][] dist;
    private boolean solved;
    private double  tourCost = Double.POSITIVE_INFINITY;
    List<Integer> tour = new ArrayList<>();
    private       int        start;
    private       double[][] costToGoTo;
    private final int        WITH_ALL_NODES_IN_PATH;

    public TravellingSalesmanProblem(double[][] dist, int start) {
        this.dist = dist;
        this.start = start;
        this.size = dist.length;
        this.costToGoTo = new double[size][(int) Math.pow(2, size)];
        this.WITH_ALL_NODES_IN_PATH = (1 << size) - 1;
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
            costToGoTo[i][1 << start | 1 << i] = dist[start][i];
        }
    }

    private Set<Integer> combinations(int sub_size) {
        Set<Integer> allSets = new HashSet();
        combinations(allSets, sub_size, 0, 0);
        return allSets;
    }

    private void combinations(Set<Integer> allSets, int setSize, int at, int currentSet) {
        if (setSize == 0) {
            allSets.add(currentSet);
            return;
        }
        for (int i = at; i < size; i++) {
            currentSet |= (1 << i);
            combinations(allSets, setSize - 1, i + 1, currentSet);
            currentSet &= ~(1 << i);
        }
    }

    private boolean notIn(int allBits, int bit) {
        return (allBits & (1 << bit)) == 0;
    }

    private void solve() {
        setup();
        //capture min distance for all the path lengths & combinations.
        for (int sub_length = 3; sub_length <= size; sub_length++) {
            for (int withNodesInPath : combinations(sub_length)) {
                if (notIn(withNodesInPath, start)) { continue; }
                for (int newNode = 0; newNode < size; newNode++) {
                    if (newNode == start || notIn(withNodesInPath, newNode)) {continue;}
                    int nodesSetWithoutNewNode = withNodesInPath ^ (1 << newNode);
                    double minDistance = Double.POSITIVE_INFINITY;
                    for (int toLastNode = 0; toLastNode < size; toLastNode++) {
                        if (toLastNode == start || toLastNode == newNode || notIn(nodesSetWithoutNewNode, toLastNode)) {
                            continue;
                        }
                        double distanceFromTheLastNode = costToGoTo[toLastNode][nodesSetWithoutNewNode] + dist[toLastNode][newNode];
                        if (distanceFromTheLastNode < minDistance) {
                            minDistance = distanceFromTheLastNode;
                        }
                    }
                    costToGoTo[newNode][withNodesInPath] = minDistance;
                }
            }
        }

        //get min cost across all the nodes with all nodes in path to go back to the start node
        double costOfTheShortestPath = Double.POSITIVE_INFINITY;
        for (int i = 0; i < size; i++) {
            if (i == start) {
                continue;
            }
            double totalCostToGoBackToStart = costToGoTo[i][WITH_ALL_NODES_IN_PATH] + dist[i][start];
            if (totalCostToGoBackToStart < costOfTheShortestPath) {
                costOfTheShortestPath = totalCostToGoBackToStart;
            }
        }
        tourCost = costOfTheShortestPath;

        //get complete route for the shortest path
        if (tourCost == Double.POSITIVE_INFINITY) {return;}
        solved = true;
        tour.add(start);
        int next = start;
        int withNodesInPath = WITH_ALL_NODES_IN_PATH;
        for (int i = 1; i < size; i++) {
            int min_at = -1;
            for (int node = 0; node < size; node++) {
                if (node == start || notIn(withNodesInPath, node)) {continue;}
                if (min_at == -1) {min_at = node;}
                double currentMinCostToGoToNode = costToGoTo[min_at][withNodesInPath] + dist[min_at][next];
                double newCostToGoToNode = costToGoTo[node][withNodesInPath] + dist[node][next];
                if (newCostToGoToNode < currentMinCostToGoToNode) {
                    min_at = node;
                }
            }
            next = min_at;
            withNodesInPath ^= (1 << min_at);
            tour.add(min_at);
        }
        tour.add(start);
    }

    public static void main(String[] args) {
        double[][] graph = new double[][]{
                {0.0, 10.0, 15.0, 20.0},
                {10.0, 0.0, 35.0, 25.0},
                {15.0, 35.0, 0.0, 30.0},
                {20.0, 25.0, 30.0, 0.0}};
        TravellingSalesmanProblem tsmp = new TravellingSalesmanProblem(graph, 0);
        System.out.println(tsmp.getTour());
        System.out.println(tsmp.tourCost);
        //10 + 25 + 30 + 15 = 80
    }

}
