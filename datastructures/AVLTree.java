package datastructures;

public class AVLTree {
    class Node {
        int  val;
        int  bf;
        int  height;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node root;

    public void insert(int val) {
        root = insert(this.root, val);
    }

    private Node insert(Node node, int val) {
        if (node == null) {
            node = new Node(val);
            return node;
        }

        if (val < node.val) {
            node.left = insert(node.left, val);
        }

        if (val > node.val) {
            node.right = insert(node.right, val);
        }

        updateHeightAndBalanceFactor(node);
        return reBalanceAt(node);
    }

    private Node deleteNode(Node node) {

        if (node.left != null && node.right != null) {
            return replaceLeftRightCase(node);
        }
        else if (node.left != null & node.right == null) {
            return replaceWithLeftCase(node);
        }
        else if (node.left == null && node.right != null) {
            return replaceWithRightCase(node);
        }
        else {
            return null;
        }
    }

    private Node replaceLeftRightCase(Node node) {
        Node leftLargest = findLeftLargest(node.left);
        int leftLargestVal = leftLargest.val;
        deleteNode(node.left, leftLargestVal);
        node.val = leftLargestVal;
        return node;
    }

    private Node replaceWithLeftCase(Node node) {
        Node left = node.left;
        node.left = null;
        return left;
    }

    private Node replaceWithRightCase(Node node) {
        Node right = node.right;
        node.right = null;
        return right;
    }

    private Node findLeftLargest(Node node) {
        while (true) {
            if (node.right == null) {
                return node;
            }
            node = node.right;
        }
    }

    private Node findRightSmallest(Node node) {
        while (true) {
            if (node.left == null) {
                return node;
            }
            node = node.left;
        }
    }

    public boolean deleteNode(int val) {
        if (findNode(val) == null) {
            return false;
        }
        deleteNode(root, val);
        return true;
    }

    private Node findNode(int val) {
        return findNode(val, root);
    }

    private Node findNode(int val, Node node) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            return node;
        }
        if (val < node.val) {
            return findNode(val, node.left);
        }
        else {
            return findNode(val, node.right);
        }
    }

    private Node deleteNode(Node node, int val) {
        if (val < node.val) {
            node.left = deleteNode(node.left, val);
        }
        else if (val > node.val) {
            node.right = deleteNode(node.right, val);
        }
        else {
            return deleteNode(node);
        }
        updateHeightAndBalanceFactor(node);
        return reBalanceAt(node);
    }

    private void updateHeightAndBalanceFactor(Node node) {
        int lh = -1, rh = -1;
        if (node.left != null) {
            lh = node.left.height;
        }
        if (node.right != null) {
            rh = node.right.height;
        }

        node.height = 1 + Math.max(lh, rh);

        node.bf = rh - lh;
    }

    private Node reBalanceAt(Node node) {
        if (node.bf == -2) {
            return rightRotate(node);
        }
        else if (node.bf == 2) {
            return leftRotate(node);
        }
        return node;
    }

    private Node leftRotate(Node node) {
        if (node.right.bf == -1) {
            node.right = performRightRotate(node.right);
        }
        return performLeftRotate(node);
    }

    private Node rightRotate(Node node) {
        if (node.left.bf == 1) {
            node.left = performLeftRotate(node.left);
        }
        return performRightRotate(node);
    }

    private Node performLeftRotate(Node node) {
        Node right = node.right;
        Node left = right.left;
        right.left = node;
        node.right = left;
        return right;
    }

    private Node performRightRotate(Node node) {
        Node left = node.left;
        Node right = left.right;
        left.right = node;
        node.left = right;
        return left;
    }

    public void printInOrder() {
        if (root != null) {
            dfs(root);
        }
    }

    private void dfs(Node node) {
        if (node != null) {

            dfs(node.left);
            System.out.println(node.val);
            dfs(node.right);

        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(4);
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);
        tree.insert(10);
        tree.insert(5);
        tree.printInOrder();
        tree.deleteNode(3);
        System.out.println("----------------");
        tree.printInOrder();
    }


}
