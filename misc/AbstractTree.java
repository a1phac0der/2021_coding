package misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractTree<E> implements Tree<E> {

	public boolean isInternal(Position<E> p){
		return numChildren(p)>0;
	}
	
	public boolean isExternal(Position<E> p){
		return numChildren(p) == 0;
	}
	
	public boolean isRoot(Position<E> p){
		return p == root();
	}
	
	public boolean isEmpty(){
		return size() ==0;
	}
	
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
		snapshot.add(p);
		for(Position<E> c: children(p)){
			preorderSubtree(c, snapshot);
		}
	}
	
	public Iterable<Position<E>> preorder(){
		List<Position<E>> snapshot = new ArrayList<Position<E>>();
		if(!isEmpty())
			preorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
		for(Position<E> c: children(p))
			postorderSubtree(c, snapshot);
		snapshot.add(p);
	}
	
	public Iterable<Position<E>> postorder(){
		List<Position<E>> snapshot = new ArrayList<Position<E>>();
		if(!isEmpty())
			postorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	public Iterable<Position<E>> breadthFirst() {
		List<Position<E>> snapshot = new ArrayList<Position<E>>();
		if (!isEmpty()) {
			Queue<Position<E>> fringe = new LinkedList<Position<E>>();
			fringe.offer(root());
			while (!fringe.isEmpty()) {
				Position<E> p = fringe.poll();
				snapshot.add(p);
				for (Position<E> c : children(p)) {
					fringe.offer(c);
				}
			}
		}
		return snapshot;
	}
	
}
