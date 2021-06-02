package misc;

public class UseTrees {
	public static void main(String[] args) {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		Position<String> root = tree.addRoot("Dave");
		Position<String> left = tree.addLeft(root, "Mohan");
		Position<String> right = tree.addRight(root, "Karun");
		tree.addLeft(left, "signin");
		tree.addRight(left, "RPP");
		tree.addLeft(right, "Sasi");
		tree.addRight(right, "Pramoth");
		for (Position<String> p : tree.preorder()) {
			System.out.println(p.getElement());
		}
		System.out.println(tree.heightOf(left));
		System.out.println(tree.allChildren(root));

		printTree(tree);
	}

	public static void printTree(LinkedBinaryTree<String> tree) {
		int treeHeight = tree.heightOf(tree.root()) + 1;
		int treeWidth = tree.allChildren(tree.root()) + 1;
		String[][] treeMatrix = new String[treeHeight][treeWidth];
		LinkedBinaryTree.Node<String> root = tree.validate(tree.root());
		System.out.println(treeHeight + " " + treeWidth);
		storeTree(root, treeMatrix, new UseTrees.Index(0, 0));
		System.out.println("\n\n");
		for (int i = 0; i < treeHeight; i++) {
			for (int j = 0; j < treeWidth; j++) {
				if (treeMatrix[i][j] != null)
					System.out.print(treeMatrix[i][j]);
				else
					System.out.print("    ");
			}
			System.out.println("\n");
		}

	}

	static class Index {
		int x, y;

		public Index(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void storeTree(LinkedBinaryTree.Node<String> node, String[][] treeMatrix, Index index) {
		if (node.getLeft() != null) {
			index.x++;
			storeTree(node.getLeft(), treeMatrix, index);
			index.x--;
			index.y++;
		}

		System.out.println(node.getElement() + " " + index.x + " " + index.y);
		treeMatrix[index.x][index.y] = node.getElement();

		if (node.getRight() != null) {
			index.y++;
			index.x++;
			storeTree(node.getRight(), treeMatrix, index);
			index.x--;
		}

	}

}
