import java.util.Comparator;

public class Edge<T> {
    private Vertex<T> startingVertex;
    private Vertex<T> endingVertex;
    private int weight;

    public Edge(Vertex<T> startingVertex, Vertex<T> endingVertex, int weight) {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
        this.weight = weight;
    }

    public Vertex<T> getStartingVertex() {
        return this.startingVertex;
    }

    public Vertex<T> getEndingVertex() {
        return this.endingVertex;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "(" + startingVertex.getItem().toString() + ", " + endingVertex.getItem().toString() + ", " + weight + ")";
    }

    @SuppressWarnings("rawtypes")
	public static final Comparator<Edge> comparator = new Comparator<Edge>() {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.getWeight() - o2.getWeight();
		}
		
	};
}
