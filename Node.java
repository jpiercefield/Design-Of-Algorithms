/*
 * James 'Logan' Piercefield
 * Project 2 - CSC 2400 Design Of Algorithms
 */
public class Node<T> 
{
	
	private Node<T> parent = null;
	private T itemN;
	private ParentTree<T> tree;
	
	public Node(T itemN) 
	{
		this.itemN = itemN;
	}
	
	public boolean setParent(Node<T> parNod) 
	{
		if(parNod != this) 
		{
			this.parent = parNod;
			return true;
		}
		return false;
	}
	
	public T getItem() 
	{
		return this.itemN;
	}
	
	public Node<T> getParent() 
	{
		return this.parent;
	}
	
	public Node<T> getRoot() 
	{
		if (parent == null) 
		{
			return this;
		}
		return parent.getRoot();
	}
	
	public ParentTree<T> getTree() 
	{
		return this.tree;
	}
	
	public void setTree(ParentTree<T> tree) 
	{
		this.tree = tree;
	}
}