package graphs.datastructure;

public class Edge implements Comparable<Edge>{
    public Integer from;
    public Integer to;
    public Double cost;

    public Edge(int from, int to, Double cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }


    @Override
    public int compareTo(Edge o) {
        return this.cost.compareTo(o.cost);
    }
}
