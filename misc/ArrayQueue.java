package misc;

public class ArrayQueue<E> {
	private E[] elements;
	private int first = -1;
	private int last = -1;
	private int size = 0;
	private final int CAPACITY;

	ArrayQueue(int capacity) {
		CAPACITY = capacity;
		elements = (E[]) new Object[CAPACITY];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public E first() {
		if (size > 0) {
			return elements[first];
		}
		return null;
	}

	public E dequeue() {
		if (size > 0) {
			E e = elements[first];
			first = (first + 1) % CAPACITY;
			size--;
			System.out.println("\nTaking out: " + e);
			return e;
		}
		return null;
	}

	public void enqueue(E e) {
		if (size < CAPACITY) {
			System.out.println("\nAdding to queue: " + e);
			last = (last + 1) % CAPACITY;
			elements[last] = e;
			size++;
			if (first < 0)
				first = last;
		} else {
			throw new IllegalStateException("Queue is full");
		}
	}

	public void print() {
		if (size > 0) {
			System.out.println();
			System.out.print("Queue: ");
			if (size == 1)
				System.out.print(elements[first]);
			else {
				int current = first;
				while (current != last) {
					System.out.print(elements[current] + " ");
					current = (current + 1) % CAPACITY;
				}
				System.out.print(elements[current]);
			}

		}
	}

	public static void main(String[] args) {
		ArrayQueue<String> queue = new ArrayQueue<String>(5);
		queue.enqueue("process 1");
		queue.enqueue("process 2");
		queue.enqueue("process 3");
		queue.enqueue("process 4");
		queue.enqueue("process 5");
		queue.print();
		queue.dequeue();
		queue.print();
		queue.enqueue("process 6");
		queue.print();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();

	}
}
