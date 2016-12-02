
public class ParentTree<T> {
	
	private int size;
	private Node<T> root;
	
	public ParentTree(Node<T> root) {
		this.root = root;
		this.size = 1;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Node<T> getRoot() {
		return this.root;
	}
	
	public boolean addTree(ParentTree<T> tree) {
		this.size += tree.getSize();
		tree.size = this.size;
		if (tree.getRoot().setParent(root)) {
			tree.root = this.root;
			return true;
		}
		return false;
	}

}
