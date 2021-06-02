package misc;

public class CircularLinkedList<E> {
	Node<E> END;

	public void insert(Node<E> newNode) {
		if (END == null) {
			END = newNode;
			END.setNext(END);
		} else {
			newNode.setNext(END.getNext());
			END.setNext(newNode);
			END = newNode;
		}
	}

	public void rotate() {
		END = END.getNext();
	}

	public Node<E> delete(Node<E> node) {
		if (END == null)
			return null;
		Node<E> currentNode = END;
		Node<E> previousNode = null;
		do {
			if (currentNode.getElement().equals(node.getElement())) {
				if (previousNode == null) {
					END = null;
					return END;
				} else {
					previousNode.setNext(currentNode.getNext());
					return currentNode;
				}
			}
			currentNode = currentNode.getNext();
		} while (currentNode != null && currentNode.getElement().equals(END.getElement()));
		return null;
	}

	public Node<E> takeOneOut() {
		if (END == null)
			return null;
		if (END.getNext() == null) {
			Node<E> node = END;
			END = null;
			return node;
		}
		Node<E> first = END.getNext();
		END.setNext(first.getNext());
		return first;
	}

	public boolean find(Node<E> node) {
		if (END == null)
			return false;
		/*
		 * if(END.getNext()==null){ return END.getName().equals(node.getName());
		 * }
		 */
		Node<E> currentNode = END;
		while (currentNode != null) {
			if (currentNode.getElement().equals(node.getElement())) {
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
	}

	public void print() {
		System.out.println();
		if (END == null)
			return;
		if (END.getNext() == null) {
			System.out.print(END.getElement());
			return;
		}
		Node<E> currentNode = END.getNext();
		while (currentNode != null) {
			if (currentNode.getElement().equals(END.getElement())) {
				System.out.print(currentNode.getElement());
				return;
			}
			System.out.print(currentNode.getElement() + " --> ");
			currentNode = currentNode.getNext();
		}

	}
}
