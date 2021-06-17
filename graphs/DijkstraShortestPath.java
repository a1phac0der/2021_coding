package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import graphs.datastructure.Edge;

public class DijkstraShortestPath {
    private List<List<Edge>> graph;
    private boolean[] visited;
    private int[]               prev;
    private Double[]            cost;
    private PriorityQueue<Edge> pq = new PriorityQueue<>();

    public DijkstraShortestPath() {

        //----------build graph-----------
        this.graph = new ArrayList<>();
        List<Edge> edges = new ArrayList();
        edges.add(new Edge(0, 1, 8.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(1, 6, 2.0));
        edges.add(new Edge(1, 0, 8.0));
        edges.add(new Edge(1, 2, 1.0));
        edges.add(new Edge(1, 4, 6.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(2, 1, 1.0));
        edges.add(new Edge(2, 3, 1.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(3, 2, 1.0));
        edges.add(new Edge(3, 5, 5.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(4, 1, 6.0));
        edges.add(new Edge(4, 7, 5.0));
        edges.add(new Edge(4, 5, 4.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(5, 4, 4.0));
        edges.add(new Edge(5, 3, 5.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(6, 1, 2.0));
        graph.add(edges);

        edges = new ArrayList();
        edges.add(new Edge(7, 4, 5.0));
        graph.add(edges);
        //------------------------------


        visited = new boolean[graph.size()];
        prev = new int[graph.size()];
        Arrays.fill(prev, -1);

        //initialise min distance array for each vertex.
        cost = new Double[10];
        for (int i = 0; i < cost.length; i++){
            cost[i] = Double.POSITIVE_INFINITY;
        }
    }

    public List<Integer>[] calculateShortestPaths(int source){
        List<Integer>[] shortestPaths = new List[graph.size()];

        //You are already here. You can only go forward. So, it's marked.
        visited[source] = true;

        //starting node cost is zero.
        cost[source] = 0.0;

        //Add all the Edges of source vertex to PQ.
        for(Edge e: graph.get(source)){
            prev[e.to] = source;
            cost[e.to] = e.cost;
            pq.add(e);
        }

        //Loop until there are no more Edges to traverse.
        while (!pq.isEmpty()){
            Edge e = pq.poll();

            //Check and skip the vertex that this Edge is pointing towards is already visited (that means it's already part of the current path)
            if(visited[e.to]){
                continue;
            }

            for (Edge to: graph.get(e.to)){
                //Update the minimum cost of reaching the vertex if the cost of current path is smaller than the previous.
                //Also update the prev in the current shortest node.
                double new_cost = cost[to.from] + to.cost;
                if(new_cost < cost[to.to]){
                    cost[to.to] = new_cost;
                    pq.add(new Edge(to.from, to.to, cost[to.to]));
                    prev[to.to] = to.from;
                }
            }

        }

        //When pq is empty, we already traversed all the edges and finished calculating the least cost path to each node from the source node.
        //We have cost of reaching each vertex in cost array.
        //We have previous vertices in shortest path in prev array.

        //Construct shortest paths to each vertex from the source.

        for(int i=0; i < graph.size(); i++){
            if(i == source){
                List<Integer> path = new ArrayList<>();
                path.add(i);
                shortestPaths[i] = path;
                continue;
            }

            List<Integer> path = new ArrayList<>();
            int v = i;
            path.add(0, v);
            while (prev[v] != -1){
                v = prev[v];
                path.add(0, v);
            }

            shortestPaths[i] = path;

        }

        return shortestPaths;
    }

    public static void main(String[] args) {
        DijkstraShortestPath dsp = new DijkstraShortestPath();
        List[] paths = dsp.calculateShortestPaths(0);
        for (List path: paths){
            System.out.println("\n----------------------");
            for (Object v: path){
                System.out.print("->" + v);
            }

        }
    }

}
