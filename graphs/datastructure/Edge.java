package graphs.datastructure;

public class Edge implements Comparable<Edge>{
    public Integer from;
    public Integer to;
    public Double cost;
    public Double costFromSource=Double.POSITIVE_INFINITY;

    public Edge(int from, int to, Double cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Edge(int from, int to, Double cost, Double costFromSource){
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.costFromSource = costFromSource;
    }

    @Override
    public int compareTo(Edge o) {
        return this.costFromSource.compareTo(o.costFromSource);
    }
}
