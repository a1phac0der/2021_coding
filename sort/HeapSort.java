package sort;

public class HeapSort {
    private int[] array;

    public HeapSort(int[] array) {
        this.array = array;
        constructHeap();
    }

    private void constructHeap() {
        if (array.length <= 1) { return; }

        int parent = (array.length - 2) / 2;

        while (parent >= 0) {
            print();
            sink(parent, array.length);
            print();
            parent--;
        }
    }

    private void sink(int node, int length) {
        int left = 2 * node + 1;
        int right = 2 * node + 2;
        while (left < length) {
            int largest = left;
            if (right < length && array[right] > array[left]) {
                largest = right;
            }
            if (array[node] >= array[largest]) {return;}
            swap(node, largest);
            node = largest;
            left = 2 * node + 1;
            right = 2 * node + 2;
        }

    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void sort() {
        int length = array.length;
        while (length > 1) {
            swap(0, length - 1);
            length--;
            print();
            sink(0, length);
            print();
        }
    }

    public void print(){
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        HeapSort heapSort = new HeapSort(array);
        heapSort.sort();
        heapSort.print();
    }
}
