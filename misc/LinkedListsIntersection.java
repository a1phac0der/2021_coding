package misc;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedListsIntersection {
	public static String findIntersectionUsingLength(SingleLinkedList<String> llist1, SingleLinkedList<String> llist2) {
		Node<String> head1 = llist1.getHead();
		Node<String> head2 = llist2.getHead();

		int size_list1 = 0, size_list2 = 0;

		Node<String> currentNode = head1;
		while (currentNode != null) {
			size_list1++;
			currentNode = currentNode.getNext();
		}
		currentNode = head2;
		while (currentNode != null) {
			size_list2++;
			currentNode = currentNode.getNext();
		}

		Node<String> list1_itr = head1;
		Node<String> list2_itr = head2;

		if (size_list1 > size_list2) {
			int diff = size_list1 - size_list2;
			int pass = 0;
			while (pass < diff && list1_itr != null) {
				list1_itr = list1_itr.getNext();
				pass++;
			}
		} else {
			int diff = size_list2 - size_list1;
			int pass = 0;
			while (pass < diff && list2_itr != null) {
				list2_itr = list2_itr.getNext();
				pass++;
			}
		}

		while (list1_itr != null && list2_itr != null && !list1_itr.equals(list2_itr)) {
			System.out.println(list1_itr.getElement() + " " + list2_itr.getElement());
			list1_itr = list1_itr.getNext();
			list2_itr = list2_itr.getNext();
		}
		if (list1_itr != null && list2_itr != null && list1_itr.equals(list2_itr))
			return list1_itr.getElement();
		else
			return null;
	}

	public static String findIntersectionUsingStacks(SingleLinkedList<String> sllist1,
			SingleLinkedList<String> sllist2) {
		LinkedStack<Node<String>> stack1 = new LinkedStack<Node<String>>();
		LinkedStack<Node<String>> stack2 = new LinkedStack<Node<String>>();
		Node<String> head1 = sllist1.getHead();
		Node<String> head2 = sllist2.getHead();
		Node<String> current1 = head1;
		Node<String> current2 = head2;
		while (current1 != null) {
			stack1.push(current1);
			current1 = current1.getNext();
		}
		while (current2 != null) {
			stack2.push(current2);
			current2 = current2.getNext();
		}
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			Node<String> stackNode1 = stack1.pop();
			Node<String> stackNode2 = stack2.pop();
			System.out.println(stackNode1.getElement() + " " + stackNode2.getElement());
			if (!stackNode1.equals(stackNode2)) {
				return stackNode1.getNext().getElement();
			}
		}
		return null;
	}

	public static String findItersectionUsingSet(SingleLinkedList<String> sllist1, SingleLinkedList<String> sllist2) {
		Node<String> current1 = sllist1.getHead();
		Set<Node<String>> set = new LinkedHashSet<Node<String>>();
		
		while(current1!=null){
			set.add(current1);
			current1 = current1.getNext();
		}
		
		Node<String> current2 = sllist2.getHead();
		while(current2!=null){
			if(set.contains(current2))
				return current2.getElement();
			current2 = current2.getNext();
		}
		return null;
	}

	public static void main(String[] args) {
		SingleLinkedList<String> sllist1 = new SingleLinkedList<String>();
		SingleLinkedList<String> sllist2 = new SingleLinkedList<String>();

		sllist1.addElementAtEnd("node 1");
		sllist1.addElementAtEnd("node 2");
		sllist1.addElementAtEnd("node 3");
		sllist1.addElementAtEnd("node 4");
		sllist1.addElementAtEnd("node 5");
		sllist1.addElementAtEnd("node 6");
		sllist1.addElementAtEnd("node 7");
		sllist1.print();

		System.out.println();
		sllist2.addElementAtEnd("node 10");
		sllist2.addElementAtEnd("node 11");
		sllist2.addElementAtEnd("node 12");
		sllist2.print();

		Node<String> node7 = sllist1.getNode("node 7");
		Node<String> node12 = sllist2.getNode("node 12");

		System.out.println();
		System.out.println(node7.getElement());
		System.out.println(node12.getElement());

		Node<String> intersectionNode = new Node<String>("node 13", null, null);
		node7.setNext(intersectionNode);
		node12.setNext(intersectionNode);

		System.out.println();
		sllist1.print();
		System.out.println();
		sllist2.print();

		intersectionNode.setNext(new Node<String>("node 14", null, null));
		intersectionNode.getNext().setNext(new Node<String>("node 15", null, null));
		intersectionNode.getNext().getNext().setNext(new Node<String>("node 16", null, null));
		intersectionNode.getNext().getNext().getNext().setNext(new Node<String>("node 17", null, null));
		intersectionNode.getNext().getNext().getNext().getNext().setNext(new Node<String>("node 18", null, null));

		System.out.println();
		sllist1.print();
		System.out.println();
		sllist2.print();

		System.out.println();

//		System.out.println(findIntersectionUsingLength(sllist1, sllist2));
//		System.out.println(findIntersectionUsingStacks(sllist1, sllist2));
		System.out.println(findItersectionUsingSet(sllist1, sllist2));
	}
}
