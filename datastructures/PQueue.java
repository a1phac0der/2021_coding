package datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PQueue<T extends Comparable> {
    //The number of elements currently inside the heap.
    private int heapSize = 0;

    //The internal capacity of heap.
    private int heapCapacity = 0;

    //A dynamic list to store the elements inside the heap.
    private List<T> heap = null;

    //A map to keep track of indices of elements in heap.
    private Map<T, TreeSet<Integer>> map = new HashMap();

    public PQueue() {
        this(1);
    }

    public PQueue(int size) {
        heap = new ArrayList(size);
        heapCapacity = size;
    }

    public PQueue(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList(elems.length);

        for (int i = 0; i < elems.length; i++) {
            heap.add(elems[i]);
            mapAdd(elems[i], i);
        }

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    public PQueue(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) {
            add(elem);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapSize; i++) {
            heap.set(i, null);
        }
        map.clear();
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    public T peek() {
        if (isEmpty()) {return null;}
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem) {
        if (elem == null) {return false;}
        return map.containsKey(elem);
    }

    public void add(T elem) {
        if (elem == null) { throw new IllegalArgumentException(); }

        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        }
        else {
            heap.add(elem);
            heapCapacity++;
        }

        mapAdd(elem, heapSize);
        swim(heapSize);

        heapSize++;

    }

    private boolean less(int i, int j) {
        T one = heap.get(i);
        T two = heap.get(j);
        return one.compareTo(two) <= 0;
    }

    private void swim(int k) {
        int parent = k / 2 - 1;

        while (parent >= 0 && less(k, parent)) {
            swap(k, parent);
            k = parent;
            parent = k / 2 - 1;
        }
    }

    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int swapWith = left;

            if(right < heapSize && less(left, right)){
                swapWith = right;
            }

            if(swapWith >= heapSize || !less(k, swapWith)){
                break;
            }

            swap(k, swapWith);
            k = swapWith;
        }
    }

    private void swap(int i, int j){
        T one = heap.get(i);
        T two = heap.get(j);

        heap.set(i, two);
        heap.set(j, one);

        mapSwap(i, one, j, two);
    }

    private boolean remove(T elem){
        if(elem == null){
            return false;
        }

        Integer index = mapGet(elem);
        if(index != null) {
            removeAt(index);
        }
        return index != null;

    }

    private T removeAt(int index){
        if(isEmpty()) return null;
        T elemRemoved = heap.get(index);
        T lastElem = heap.get(heapSize-1);
        heap.set(index, lastElem);
        heap.set(heapSize-1, null);
        mapRemove(elemRemoved, index);
        if(index == heapSize-1) return elemRemoved;
        sink(index);
        if(lastElem.equals(heap.get(index))){
            swim(index);
        }
        return elemRemoved;
    }

    public boolean isMinHeap(int index){
        if(isEmpty() || index >= heapSize) {return true;}

        int left  = 2 * index + 1;
        int right = 2 * index + 2;

        if((left < heapSize && !less(index, left) || (right < heapSize && !less(index, right)))) {return false;}

        return (isMinHeap(left) && isMinHeap(right));
    }

    private void mapAdd(T elem, int index){
        if(map.get(elem) == null){
            map.put(elem, new TreeSet());
        }
        map.get(elem).add(index);
    }

    private void mapRemove(T elem, int index){
        if(map.get(elem) != null && map.get(elem).contains(index)){
            map.get(elem).remove(index);
        }
        if(map.get(elem).isEmpty()){
            map.remove(elem);
        }
    }

    private Integer mapGet(T elem){
        if(map.get(elem) != null){
            return map.get(elem).last();
        }
        return null;
    }

    private void mapSwap(int i, T elem1, int j, T elem2){
        map.get(elem1).remove(i);
        map.get(elem1).add(j);

        map.get(elem2).remove(j);
        map.get(elem2).add(i);
    }



}
