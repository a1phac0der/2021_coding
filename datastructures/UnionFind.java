package datastructures;

public class UnionFind {
    private int[] parent;
    private int[] component_size;
    private int   num_components;

    public UnionFind(int component_size){
        this.parent = new int[component_size];
        this.component_size = new int[component_size];
        num_components = component_size;

        for(int i = 0; i < component_size; i++){
            parent[i] = i;
            this.component_size[i] = 1;
        }
    }

    private int findRoot(int elem){
        int root = elem;
        while(root != parent[root]){
            root = parent[root];
        }

        while(elem != root){
            int parent = this.parent[elem];
            this.parent[elem] = root;
            elem = parent;
        }

        return root;
    }

    private boolean connected(int x, int y){
        return findRoot(x) == findRoot(y);
    }

    public void union(int x, int y){
        if(connected(x, y)) {return;}

        int c1_root = findRoot(x);
        int c2_root = findRoot(y);

        if(component_size[c1_root] > component_size[c2_root]){
            parent[c2_root] = c1_root;
            component_size[c1_root] += component_size[c2_root];
            component_size[c2_root] = 0;
        }else{
            parent[c1_root] = c2_root;
            component_size[c2_root] += component_size[c1_root];
            component_size[c1_root] = 0;
        }
        num_components--;
    }

    public int getNumComponents(){
        return num_components;
    }

    public void printElements(){
        for(int i=0; i < parent.length; i++){
            System.out.print(i + ":" + parent[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.printElements();
        uf.union(4, 8);
        uf.union(1, 3);
        uf.union(2, 3);
        uf.union(5, 6);
        uf.union(7, 9);
        uf.union(6, 4);
        uf.union(3, 5);
        uf.union(0, 5);
        uf.union(9, 3);
        uf.printElements();
        System.out.println(uf.getNumComponents());
    }
}
