package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import graphs.datastructure.Edge;

public class BridgesAndArticulationPoints {
    static int sequence = 0;
    int[] ids, low;
    boolean[]    visited;
    List<Edge>[] edges;
    List<Edge>   bridges = new ArrayList<>();
    boolean[]    articulationPoints;
    int          nodeCount;
    int          outgoingCount;

    public static BridgesAndArticulationPoints bootAlgorithm(List<Edge>[] edges) {
        BridgesAndArticulationPoints algo = new BridgesAndArticulationPoints();
        algo.nodeCount = edges.length;
        algo.visited = new boolean[algo.nodeCount];
        algo.articulationPoints = new boolean[algo.nodeCount];
        algo.ids = new int[algo.nodeCount];
        algo.low = new int[algo.nodeCount];
        algo.edges = edges;
        return algo;
    }

    public List<Edge> findBridges() {

        for (int i = 0; i < nodeCount; i++) {
            if (!visited[i]) {
                dfsBridges(i, -1);
            }
        }

        return bridges;
    }

    private void dfsBridges(int node, int parent) {
        ids[node] = low[node] = sequence++;
        visited[node] = true;

        for (Edge e : edges[node]) {
            if (e.to == parent) { continue; }
            if (visited[e.to]) {
                low[node] = ids[e.to];
                continue;
            }
            dfsBridges(e.to, node);
            if (low[e.to] < low[node]) {
                low[node] = low[e.to];
            }
            if (ids[node] < low[e.to]) {
                bridges.add(e);
            }
        }
    }

    private void dfsArticulationPoints(int root, int node, int parent) {
        ids[node] = low[node] = sequence++;
        if (parent == root) {
            outgoingCount++;
        }
        visited[node] = true;

        for (Edge e : edges[node]) {
            if (e.to == parent) { continue; }
            if (visited[e.to]) {
                low[node] = ids[e.to];
                continue;
            }

            dfsArticulationPoints(root, e.to, node);

            if (low[e.to] < low[node]) {
                low[node] = low[e.to];
            }

            if (ids[node] <= low[e.to]) {
                articulationPoints[node] = true;
            }
        }

    }

    public boolean[] findArticulationPoints() {
        for (int i = 0; i < nodeCount; i++) {
            if (visited[i]) { continue; }
            outgoingCount = sequence = 0;
            dfsArticulationPoints(i, i, -1);
            articulationPoints[i] = outgoingCount > 1;
        }
        return articulationPoints;
    }

    public static void main(String[] args) {
        List<Edge>[] edges = new List[9];
        edges[0] = Arrays.asList(new Edge(0, 1), new Edge(0, 2));
        edges[1] = Arrays.asList(new Edge(1, 0), new Edge(1, 2));
        edges[2] = Arrays.asList(new Edge(2, 0), new Edge(2, 1), new Edge(2, 3), new Edge(2, 5));
        edges[3] = Arrays.asList(new Edge(3, 2), new Edge(3, 4));
        edges[4] = Arrays.asList(new Edge(4, 3));
        edges[5] = Arrays.asList(new Edge(5, 2), new Edge(5, 6), new Edge(5, 8));
        edges[6] = Arrays.asList(new Edge(6, 5), new Edge(6, 7));
        edges[7] = Arrays.asList(new Edge(7, 6), new Edge(7, 8));
        edges[8] = Arrays.asList(new Edge(8, 5), new Edge(8, 7));
        bootAlgorithm(edges).findBridges().stream().forEach(bridge -> System.out.println(bridge.from + " " + bridge.to));
        boolean[] aps = bootAlgorithm(edges).findArticulationPoints();
        IntStream.range(0, aps.length).forEach(i -> System.out.println(i + ":" + aps[i]));
    }
}
