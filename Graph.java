/*
 * James 'Logan' Piercefield
 * Project 2 - CSC 2400 Design Of Algorithms
 */
import java.util.*;

public class Graph<T> 
{
    private HashMap<Vertex<T>, LinkedList<Edge<T>>> adjList;
    private HashMap<String, Vertex<T>> vertices;
    private ArrayList<Vertex<T>> vertexList;

    public Graph() 
    {
        this.adjList   = new HashMap<Vertex<T>, LinkedList<Edge<T>>>();
        this.vertices  = new HashMap<String, Vertex<T>>();
        this.vertexList = new ArrayList<Vertex<T>>();
    }

    public void addVertex(T item) 
    {
        Vertex<T> vertex = new Vertex<T>(item);
        this.vertices.put(vertex.Skey, vertex);
        this.adjList.put(vertex, new LinkedList<Edge<T>>());
        this.vertexList.add(vertex);
    }

    public void addEdge(String start_vertex_key, String end_vertex_key, int edge_weight) 
    {
        Vertex<T> startVertex = vertices.get(start_vertex_key);
        Vertex<T> endVertex = vertices.get(end_vertex_key);

        if (startVertex != null && endVertex != null) 
        {
            Edge<T> edge = new Edge<T>(startVertex, endVertex, edge_weight);
            adjList.get(startVertex).add(edge);
        }
    }

    public List<Edge<T>> prim() 
    {

        List<Edge<T>> minEdges = new ArrayList<Edge<T>>();
        PriorityQueue<Edge<T>> fringe = new PriorityQueue<Edge<T>>(vertexList.size(), Edge.comparator);
        HashSet<String> visited = new HashSet<String>();
        Vertex<T> current = vertexList.get(0);
        visited.add(current.Skey);
        fringe.addAll(adjList.get(current));
        int doneCount = 1;
        while(true)
        {
            Edge<T> e = fringe.poll();
            if(visited.contains(e.getStartingVertex().Skey) && visited.contains(e.getEndingVertex().Skey))
            {
                continue;
            }
            visited.add(e.getEndingVertex().Skey);
            minEdges.add(e);
            current = e.getEndingVertex();
            fringe.addAll(adjList.get(current));
            doneCount++;
            if(doneCount == vertices.size() || fringe.size() == 0)
            {
                break;
            }
        }
        return minEdges;
    }

    List<Edge<T>> shortestPath(String start_vertex_key, String end_vertex_key)
    {
        for(Vertex<T> ver : vertexList)
        {
            ver.minDistance = Integer.MAX_VALUE;
        }

        Vertex<T> source = vertices.get(start_vertex_key);
        if(source == null)
        {
            return null;
        }
        source.minDistance = 0; 
        PriorityQueue<Vertex<T>> vertexQueue = new PriorityQueue<Vertex<T>>();  
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) 
        {
            Vertex<T> u = vertexQueue.poll();
            for (Edge<T> e : adjList.get(u)) 
            {
                Vertex<T> v = e.getEndingVertex();
                int weight = e.getWeight();
                int distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) 
                {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
        List<Edge<T>> edges = new ArrayList<Edge<T>>();

        Vertex<T> cur = vertices.get(end_vertex_key);
        if(cur == null)
        {
            return null;
        }
        while(cur != null)
        {
            List<Edge<T>> adj = adjList.get(cur);
            for(Edge<T> edge : adj)
            {
                if(edge.getEndingVertex() == cur.previous)
                {
                    edges.add(edge);
                    break;
                }
            }
            cur = cur.previous;
        }
        return edges;
    }

    public List<Edge<T>> kruskal() 
    {
        HashMap<Vertex<T>, Node<Vertex<T>>> nodes = new HashMap<Vertex<T>, Node<Vertex<T>>>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<Edge<T>>(vertexList.size(), Edge.comparator);
        List<Edge<T>> edges = new ArrayList<Edge<T>>();

        for (int i = 0; i < vertexList.size(); i++) 
        {
            Vertex<T> v = vertexList.get(i);
            Node<Vertex<T>> node = new Node<Vertex<T>>(v);
            node.setTree(new ParentTree<Vertex<T>>(node));
            nodes.put(v, node);

            LinkedList<Edge<T>> adjEdges = adjList.get(v);
            Iterator<Edge<T>> iter = adjEdges.iterator();
            while (iter.hasNext()) 
            {
                pq.add(iter.next());
            }
        }

        Edge<T> edge = pq.poll();
        while (edge != null) {
            Node<Vertex<T>> n1 = nodes.get(edge.getStartingVertex());
            Node<Vertex<T>> n2 = nodes.get(edge.getEndingVertex());

            if (!n1.getRoot().equals(n2.getRoot())) 
            {
                edges.add(edge);
                ParentTree<Vertex<T>> t1 = n1.getTree();        
                ParentTree<Vertex<T>> t2 = n2.getTree();        
                if (t1.getSize() < t2.getSize()) 
                {
                    if (!t2.addTree(t1)) 
                    {
                        edges.remove(edges.size() - 1);
                    } else {
                        n1.setTree(t2);
                    }
                } else {
                    if (!t1.addTree(t2))
                    {
                        edges.remove(edges.size() - 1);
                    } else{

                        n2.setTree(t1);
                    }    
                }
            }
            edge = pq.poll();
        }
        return edges;
    }
}
