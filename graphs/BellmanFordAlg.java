package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import graphs.datastructure.Edge;

public class BellmanFordAlg {


    public static Double[] detectNegativeCycles(List<Edge> edges, int vertices, int start) {
        Double[] cost = new Double[vertices];
        for(int i=0; i < vertices; i++){
            cost[i] = Double.POSITIVE_INFINITY;
        }

        cost[start] = 0.0;

        for (int i = 0; i < vertices - 1; i++) {
            for(Edge edge: edges){
                double from_cost = cost[edge.from] + edge.cost;
                if(from_cost < cost[edge.to]){
                    cost[edge.to] = from_cost;
                }
            }
        }

        for (int i = 0; i < vertices - 1; i++) {
            for(Edge edge: edges){
                double from_cost = cost[edge.from] + edge.cost;
                if(from_cost < cost[edge.to]){
                    cost[edge.to] = Double.NEGATIVE_INFINITY;
                }
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5.0));
        edges.add(new Edge(1, 6, 60.0));
        edges.add(new Edge(1, 5, 30.0));
        edges.add(new Edge(1, 2, 20.0));
        edges.add(new Edge(2, 3, 10.0));
        edges.add(new Edge(2, 4, 75.0));
        edges.add(new Edge(3, 2, -15.0));
        edges.add(new Edge(4, 9, 100.0));
        edges.add(new Edge(5, 6, 5.0));
        edges.add(new Edge(5, 8, 50.0));
        edges.add(new Edge(6, 7, -50.0));
        edges.add(new Edge(7, 8, -10.0));
        Double[] dist = detectNegativeCycles(edges, 10, 0);
        IntStream.range(0, dist.length).forEach(i -> System.out.print(i + ":" + dist[i] + " "));
    }


}
