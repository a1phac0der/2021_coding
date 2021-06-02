package leetcode;

import java.util.NoSuchElementException;

public class LRUCache {

    class Node {
        Integer val;
        Node    prev;
        Node    next;

        public Node(int val) {
            this.val = val;
        }
    }

    class LRUQ {
        Node head;
        Node tail;

        public Node add(Integer val) {
            Node node = new Node(val);
            node.val = val;
            if (tail == null) {
                tail = node;
            }
            else {
                node.next = head;
                head.prev = node;
            }
            head = node;
            return node;
        }

        public void delete(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            if (prev == null) {
                head = next;
            }
            else {
                prev.next = next;
                node.prev = null;
            }

            if (next == null) {
                tail = prev;
            }
            else {
                next.prev = prev;
                node.next = null;
            }
        }

        public int remove() {
            if (tail == null) { throw new NoSuchElementException(); }
            int val = tail.val;
            delete(tail);
            return val;
        }
    }

    public void test() {
        LRUQ lruq = new LRUQ();
        Node node1 = lruq.add(1);
        Node node3 = lruq.add(3);
        Node node2 = lruq.add(2);
        //        lruq.delete(node3);
        System.out.println(lruq.remove());
        System.out.println(lruq.remove());
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache();
        lruCache.test();
    }

}
