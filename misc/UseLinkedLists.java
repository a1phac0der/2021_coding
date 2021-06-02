package misc;

public class UseLinkedLists {
	public static void main(String[] args) {
		/*SingleLinkedList sllist = new SingleLinkedList();
		sllist.insertNode(new Node("node 1"));
		sllist.insertNode(new Node("Node 2"));
		sllist.insertNode(new Node("Node 3"));
		sllist.deleteNode(new Node("Node 2"));
		sllist.print();*/
		CircularLinkedList<String> cllist = new CircularLinkedList<String>();
		cllist.insert(new Node<String>("Node 1", null, null));
		cllist.insert(new Node<String>("Node 2", null, null));
		cllist.insert(new Node<String>("Node 3", null, null));
		cllist.insert(new Node<String>("Node 4", null, null));
		cllist.insert(new Node<String>("Node 5", null, null));
		/*cllist.print();
		System.out.println("\n" + cllist.takeOneOut().getElement());
		cllist.print();
		cllist.rotate();
		cllist.print();
		System.out.println("\n" + cllist.takeOneOut().getElement());
		cllist.print();
		cllist.rotate();
		cllist.print();*/
		cllist.print();
		cllist.rotate();
		cllist.print();
		cllist.rotate();
		cllist.print();
	}
}
