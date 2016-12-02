
public class Node<T> {
	
	private Node<T> parent = null;
	private T item;
	private ParentTree<T> tree;
	
	public Node(T item) {
		this.item = item;
	}
	
	public boolean setParent(Node<T> p) {
		if (p != this) {
			this.parent = p;
			return true;
		}
		return false;
	}
	
	public T getItem() {
		return this.item;
	}
	
	public Node<T> getParent() {
		return this.parent;
	}
	
	public Node<T> getRoot() {
		if (parent == null) {
			return this;
		}
		return parent.getRoot();
	}
	
	public ParentTree<T> getTree() {
		return this.tree;
	}
	
	public void setTree(ParentTree<T> tree) {
		this.tree = tree;
	}

}
