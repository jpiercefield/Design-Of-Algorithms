/**
 * @James Logan Piercefield
 * 
 * Template for Edge
 */
public class Edge<T> 
{
	private Vertex<T> startingVertex;
	private Vertex<T> endingVertex;
	private double weight;
	
	//Edge Constructor
	public Edge(Vertex<T> startingVertex, Vertex<T> endingVertex, double weight) 
	{
		this.startingVertex = startingVertex; //instance - 
		this.endingVertex = endingVertex;
		this.weight = weight;
	}
	//X 
	public Vertex<T> getStartingVertex() 
	{
		return this.startingVertex;
	}
	//Y
	public Vertex<T> getEndingVertex() 
	{
		return this.endingVertex;
	}
	
	//# 
	//Could use for any weighted graph attributes
	public double getWeight() 
	{  
		return this.weight;
	}

}
