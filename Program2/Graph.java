import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class Graph<T> {
	
	private final int MAX_NUM_VERTICES;
	
	private Edge<T>[][] adjMatrix;
	private HashMap<Vertex<T>, LinkedList<Edge<T>>> adjList;
	private HashMap<String, Vertex<T>> vertices;
	private ArrayList<Vertex<T>> vertexList;
	private int size = 0;
	private Vertex<T> topVertex = null;
	
	public Graph(int max_num_vertices) {
		this.MAX_NUM_VERTICES = max_num_vertices;
		this.adjMatrix = new Edge[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
		this.adjList   = new HashMap<Vertex<T>, LinkedList<Edge<T>>>();
		this.vertices  = new HashMap<String, Vertex<T>>();
		this.vertexList = new ArrayList<Vertex<T>>();
	}
	
	public void addVertex(T item) {
		if (size > MAX_NUM_VERTICES) {
			return;
		}
		
		Vertex<T> vertex = new Vertex<T>(item, size);
		if (size == 0) {
			this.topVertex = vertex;
		}
		String key = vertex.getKey();
		this.vertices.put(key, vertex);
		this.adjList.put(vertex, new LinkedList<Edge<T>>()); // TODO might need to change for proper efficiency
		this.vertexList.add(vertex);
		size++;
	}
	
	public void addEdge(String start_vertex_key, String end_vertex_key, int edge_weight) {
		Vertex<T> startVertex = vertices.get(start_vertex_key);
		Vertex<T> endVertex = vertices.get(end_vertex_key);
		
		if (startVertex != null && endVertex != null) {
			Edge<T> edge = new Edge<T>(startVertex, endVertex, edge_weight);
			//Edge<T> endingEdge = new Edge<T>(endVertex, startVertex, edge_weight);
			adjList.get(startVertex).add(edge);
			//adjList.get(endVertex).add(endingEdge);
			adjMatrix[startVertex.getIndex()][endVertex.getIndex()] = edge;
			//adjMatrix[endVertex.getIndex()][startVertex.getIndex()] = endingEdge;
		}
	}
	
	public List<Edge<T>> prim() {
		
		List<Edge<T>> minEdges = new ArrayList<Edge<T>>();
		PriorityQueue<Edge<T>> pq = new PriorityQueue<Edge<T>>(vertexList.size(), Edge.comparator);
		
		for (int i = 0; i < vertexList.size(); i++) {
			Vertex<T> vertex = vertexList.get(i);
			if (!vertex.isMarked()) {
				prim(vertex, minEdges, pq);
			}
		}
		
		for (int i = 0; i < vertexList.size(); i++) {
			vertexList.get(i).setMarked(false);
		}
		
		return minEdges;
	}
	
	private void prim(Vertex<T> vertex, List<Edge<T>> minEdges, PriorityQueue<Edge<T>> pq) {
		vertex.setMarked(true);
		LinkedList<Edge<T>> adjEdges = adjList.get(vertex);
		Iterator<Edge<T>> iterEdges = adjEdges.iterator();
		while (iterEdges.hasNext()) {
			pq.add(iterEdges.next());
		}
		
		Edge<T> edge = pq.poll();
		while (edge != null && edge.getEndingVertex().isMarked()) {
			edge = pq.poll();
		}
		
		if (edge != null) {
			minEdges.add(edge);
			prim(edge.getEndingVertex(), minEdges, pq);
		}
	}
	
	public List<Edge<T>> kruskal() {
		
		//HashMap<Node<Vertex<T>>, ParentTree<Vertex<T>>> trees = new HashMap<Node<Vertex<T>>, ParentTree<Vertex<T>>>();
		HashMap<Vertex<T>, Node<Vertex<T>>> nodes = new HashMap<Vertex<T>, Node<Vertex<T>>>();
		PriorityQueue<Edge<T>> pq = new PriorityQueue<Edge<T>>(vertexList.size(), Edge.comparator);
		List<Edge<T>> edges = new ArrayList<Edge<T>>();
		
		for (int i = 0; i < vertexList.size(); i++) {
			Vertex<T> v = vertexList.get(i);
			Node<Vertex<T>> node = new Node<Vertex<T>>(v);
			node.setTree(new ParentTree<Vertex<T>>(node));
			nodes.put(v, node);
			//trees.put(node, new ParentTree<Vertex<T>>(node));
			
			LinkedList<Edge<T>> adjEdges = adjList.get(v);
			Iterator<Edge<T>> iter = adjEdges.iterator();
			while (iter.hasNext()) {
				pq.add(iter.next());
			}
			
		}
		
		Edge<T> edge = pq.poll();
		while (edge != null) {
			Node<Vertex<T>> n1 = nodes.get(edge.getStartingVertex());
			Node<Vertex<T>> n2 = nodes.get(edge.getEndingVertex());
			
			if (!n1.getRoot().equals(n2.getRoot())) {
				edges.add(edge);
				ParentTree<Vertex<T>> t1 = n1.getTree();		//trees.get(n1);
				ParentTree<Vertex<T>> t2 = n2.getTree();		//trees.get(n2);
				if (t1.getSize() < t2.getSize()) {
					if (!t2.addTree(t1)) {
						edges.remove(edges.size() - 1);
					} else {
						//trees.put(n1, t2);
						n1.setTree(t2);
					}
				} else {
					if (!t1.addTree(t2)) {
						edges.remove(edges.size() - 1);
					} else {
						//trees.put(n2, t1);
						n2.setTree(t1);
					}
				}
			}
			
			edge = pq.poll();
		}
		
		return edges;
	}
	
}
