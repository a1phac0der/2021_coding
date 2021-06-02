package misc;

public class SingleLinkedList<E> {
	private Node<E> head;
	private int size = 0;

	public Node<E> addElementAtEnd(E e) {
		Node<E> newNode = new Node<E>(e, null, null);
		Node<E> currentNode = head;
		if (currentNode == null) {
			head = newNode;
			size++;
			return head;
		}
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		currentNode.setNext(newNode);
		size++;
		return newNode;
	}

	public Node<E> getHead() {
		return head;
	}

	public E first() {
		if (head == null)
			return null;
		return head.getElement();
	}

	public E removeFirst() {
		if (head == null)
			return null;
		E e = head.getElement();
		head = head.getNext();
		size--;
		return e;
	}

	public E removeLast() {
		if (head == null)
			return null;
		if (head.getNext() == null) {
			E e = head.getElement();
			head = null;
			return e;
		}
		Node<E> current = head;
		Node<E> previous = null;
		E e = null;
		while (current != null) {
			e = current.getElement();
			previous = current;
			current = current.getNext();
		}
		previous.setNext(null);
		return e;

	}

	public Node<E> getNode(E e) {
		if (head == null)
			return head;
		Node<E> current = head;
		while (current != null) {
			if (current.getElement().equals(e)) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public E addElementAtStart(E element) {
		Node<E> node = new Node<E>(element, null, null);
		if (head == null) {
			head = node;
			size++;
			return element;
		} else {
			node.setNext(head);
			head = node;
			size++;
			return element;
		}

	}

	public int getSize() {
		return size;
	}

	public boolean deleteNode(Node<E> node) {
		Node<E> currentNode = head;
		Node<E> previousNode = null;
		while (currentNode != null) {
			if (currentNode.getElement().equals(node.getElement())) {
				if (previousNode != null) {
					previousNode.setNext(currentNode.getNext());
					size--;
					return true;
				} else {
					head = head.getNext();
					size--;
					return true;
				}
			}
			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}
		return false;
	}

	public void print() {
		if (head == null)
			return;
		System.out.print(head.getElement());
		Node<E> currentNode = head.getNext();
		while (currentNode != null) {
			System.out.print(" --> " + currentNode.getElement());
			currentNode = currentNode.getNext();
		}
	}

	/*
	 * public static void main(String[] args){ SingleLinkedList }
	 */

}
