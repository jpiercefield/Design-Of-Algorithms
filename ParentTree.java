/*
 * James 'Logan' Piercefield
 * Project 2 - CSC 2400 Design Of Algorithms
 */
public class ParentTree<T> 
{
	private int size;
	private Node<T> rootN;
	
	public ParentTree(Node<T> rootN) 
	{
		this.rootN = rootN;
		this.size = 1;
	}
	
	public int getSize() 
	{
		return this.size;
	}
	
	public Node<T> getRoot() 
	{
		return this.rootN;
	}
	
	public boolean addTree(ParentTree<T> tree) 
	{
		this.size += tree.getSize();
		tree.size = this.size;
		if (tree.getRoot().setParent(rootN)) 
		{
			tree.rootN = this.rootN;
			return true;
		}
		return false;
	}
}