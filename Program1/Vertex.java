/**
 * @James Logan Piercefield
 * 
 * Template for Vertices 
 */
public class Vertex<T> 
{
	
	private T item;
	private String key;
	private final int index;	
	private boolean marked;  //set---
	
	//Constructor
	public Vertex(T item, int index) 
	{
		this.item = item;
		this.key = this.item.toString();
		this.index = index;
		this.marked = false;
	}
	
	public T getItem() 
	{
		return item;
	}
	
	public void setMarked(boolean val) 
	{
		this.marked = val;
	}
	
		public boolean isMarked() 
	{
		return marked;
	}
	
	public String getKey() 
	{
		return key; //search
	}
	
	public int getIndex() 
	{
		return index;
	}
	
}
