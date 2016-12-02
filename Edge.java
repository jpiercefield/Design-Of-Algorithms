/*
 * James 'Logan' Piercefield
 * Project 2 - CSC 2400 Design Of Algorithms
 */
import java.util.Comparator;

public class Edge<T> 
{
    private Vertex<T> startingVertex;
    private Vertex<T> endingVertex;
    private int weight;

    public Edge(Vertex<T> startingVertex, Vertex<T> endingVertex, int weight) 
    {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
        this.weight = weight;
    }

    public Vertex<T> getStartingVertex() 
    {
        return this.startingVertex;
    }
    
    public int getWeight() 
    {
        return this.weight;
    }

    public Vertex<T> getEndingVertex() 
    {
        return this.endingVertex;
    }

    @Override
    public String toString() 
    {
        return "(" + startingVertex.itemV.toString() + ", " + endingVertex.itemV.toString() + ", " + weight + ")";
    }

    @SuppressWarnings("rawtypes")
	public static final Comparator<Edge> comparator = new Comparator<Edge>() {

		@Override
		public int compare(Edge o1, Edge o2) 
		{
			return o1.getWeight() - o2.getWeight();
		}
		
	};
}
