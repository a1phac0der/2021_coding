package misc;

import java.util.Stack;

public class LinkedStack<E> extends Stack<E> {
	private static final long serialVersionUID = 476291122695664837L;
	private SingleLinkedList<E> list = new SingleLinkedList<E>();

	@Override
	public E push(E item) {
		return list.addElementAtStart(item);
	}

	@Override
	public synchronized E pop() {
		return list.removeFirst();
	}
	
	@Override
	public synchronized E peek() {
		return list.first();
	}

	@Override
	public boolean empty() {
		return list.isEmpty();
		
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
		
	}
	
}
