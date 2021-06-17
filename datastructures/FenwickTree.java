package datastructures;

public class FenwickTree {
    int[] array;
    int[] tree;

    public FenwickTree(int[] array){
        this.array = array;
        initialise();
    }

    private void initialise(){
        tree = new int[array.length+1];
        for(int i=1; i < tree.length; i++){
            tree[i] = array[i-1];
        }
        int index = 1;
        while(index < tree.length){
            if(index + lsb(index) < tree.length){
                tree[index + lsb(index)] += tree[index];
            }
            index++;
        }
    }

    private int lsb(int num){
        return num & -num;
    }

    public void update(int arrayIndex, int val){
        int oldVal = array[arrayIndex];
        array[arrayIndex] = val;
        int diff = val - oldVal;
        int index = arrayIndex+1;
        while(index < tree.length){
            tree[index] += diff;
            index += lsb(index);
        }
    }

    private int getPrefixSum(int index){
        int sum = 0;
        while(index > 0){
            sum += tree[index];
            index -= lsb(index);
        }
        return sum;
    }

    private int getRangeSum(int from, int to){
        return  getPrefixSum(to) - getPrefixSum(from-1);
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        FenwickTree fenwickTree = new FenwickTree(array);
        System.out.println(fenwickTree.getPrefixSum(3));
        System.out.println(fenwickTree.getPrefixSum(9));
        System.out.println(fenwickTree.getRangeSum(2, 5));
        fenwickTree.update(4, -1);
        System.out.println(fenwickTree.getRangeSum(2, 5));
    }
}
