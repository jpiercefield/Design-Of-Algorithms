/*
 * James 'Logan' Piercefield
 * Project 2 - CSC 2400 Design Of Algorithms
 */
public class Vertex<T> implements Comparable<Vertex>
{
	public T itemV;
	public String Skey;
	
	public int minDistance = Integer.MAX_VALUE;	
	public Vertex<T> previous;	

	public Vertex(T itemV) 
	{
		this.itemV = itemV;
		this.Skey = itemV.toString();
	}

	@Override
	public int compareTo(Vertex other) 
	{
		return (minDistance - other.minDistance);
	}
}
