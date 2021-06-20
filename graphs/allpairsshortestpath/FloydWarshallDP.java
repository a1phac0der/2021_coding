package graphs.allpairsshortestpath;

public class FloydWarshallDP {


    public static int[][] calculateShortestPath(double[][] graph) {
        double[][][] costMatrix = new double[graph.length + 1][graph.length][graph.length];
        int[][] next = new int[graph.length][graph.length];
        initialise(graph, costMatrix, next);
        recurseFindShortestPath(costMatrix, graph.length + 1, graph.length, 0, 0, next);
        detectNodesInNegativeCycles(costMatrix, next);
        return next;
    }

    private static void detectNodesInNegativeCycles(double[][][] costMatrix, int[][] next) {
        int nodeCount = next.length;
        for (int viaNode = 0; viaNode < nodeCount; viaNode++) {
            for (int node1 = 0; node1 < nodeCount; node1++) {
                for (int node2 = 0; node2 < nodeCount; node2++) {
                    double inclusiveCost = costMatrix[2][node1][viaNode] + costMatrix[nodeCount-1][viaNode][node2];
                    if (inclusiveCost < costMatrix[nodeCount][node1][node2]){
                        costMatrix[nodeCount][node1][node2] = Double.NEGATIVE_INFINITY;
                        next[node1][node2] = -1;
                    }
                }
            }
        }
    }

    private static void recurseFindShortestPath(double[][][] costMatrix, int nodesInPath, int totalNodes, int from, int to, int[][] next) {

        if (nodesInPath < 3) {return;}

        for (int node1 = 0; node1 < totalNodes; node1++) {
            for (int node2 = 0; node2 < totalNodes; node2++) {
                if (node1 == node2) {continue;}
                recurseFindShortestPath(costMatrix, nodesInPath - 1, totalNodes, node1, node2, next);
            }
        }

        if (nodesInPath > totalNodes) {return;}
        for (int node = 0; node < totalNodes; node++) {
            double inclusiveCost = costMatrix[nodesInPath - 1][from][node] + costMatrix[nodesInPath - 1][node][to];
            if (inclusiveCost < costMatrix[nodesInPath - 1][from][to]) {
                costMatrix[nodesInPath][from][to] = inclusiveCost;
                next[from][to] = node;
            }
        }
    }

    private static void initialise(double[][] graph, double[][][] costMatrix, int[][] next) {
        for (int node1 = 0; node1 < graph.length; node1++) {
            for (int node2 = 0; node2 < graph.length; node2++) {
                costMatrix[2][node1][node2] = graph[node1][node2];
                next[node1][node2] = node2;
            }
        }
    }

    private static void printAllPairsShortestPaths(int[][] nextMatrix) {
        for (int node1 = 0; node1 < nextMatrix.length; node1++) {
            for (int node2 = 0; node2 < nextMatrix.length; node2++) {
                if (node1 == node2) {continue;}
                int next = node1;
                System.out.println();
                while (next != node2) {
                    if (next == -1){
                        System.out.print(">>>>>>NEGATIVE CYCLE DETECTED");
                        break;
                    }
                    System.out.print("->" + next);
                    next = nextMatrix[next][node2];
                }
                System.out.print("->" + node2);
            }
        }
    }

    public static void main(String[] args) {
        double[][] graph = new double[][]{
                {0.0, 4.0, 1.0, 9.0},
                {3.0, 0.0, 6.0, 11.0},
                {4.0, 1.0, 0.0, 2.0},
                {6.0, 5.0, -4.0, 0.0}};
        int[][] nextMatrix = calculateShortestPath(graph);
        printAllPairsShortestPaths(nextMatrix);
    }


}
