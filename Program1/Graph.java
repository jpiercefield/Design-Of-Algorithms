/**
 * @James Logan Piercefield
 * 
 * Graph <template>
 * 
 * Manages all information and performs searches within.
 */
import java.util.Iterator;
import java.util.Stack;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Graph<T> 
{
    
    private HashMap<Vertex<T>, LinkedList<Edge<T>>> adjList;
    private HashMap<String, Vertex<T>> vertices;
    private final int MAX_NUM_VERTICES;
    private Edge<T>[][] adjMatrix;
    private Vertex<T> topVertex = null;
    private boolean finished = false;
    private int size = 0;
    private Stack backEdge = new Stack();
    private Stack topo = new Stack();
    

    public Graph(int max_num_vertices) 
    {
        this.MAX_NUM_VERTICES = max_num_vertices;
        this.adjMatrix = new Edge[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
        this.adjList   = new HashMap<Vertex<T>, LinkedList<Edge<T>>>();
        this.vertices  = new HashMap<String, Vertex<T>>();
    }
    
    public void addBackEdge()
    {
        int stackSize = backEdge.size();
        int count = 0;
        while(count < stackSize)
        {
            System.out.println(backEdge.get(count));
            count++;
        }
    }
   
    public void addVertex(T item) 
    {
        if (size > MAX_NUM_VERTICES) 
        {
            return;
        }
        Vertex<T> vertex = new Vertex<T>(item, size);
        if (size == 0) 
        {
            this.topVertex = vertex;
        }
        String key = vertex.getKey();
        this.vertices.put(key, vertex); //string <t>  
        this.adjList.put(vertex, new LinkedList<Edge<T>>()); //linkedList Vertex <T>item>e)
        size++;
    }
    
    public void addEdge(String start_vertex_key, String end_vertex_key, double edge_weight) 
    {
        Vertex<T> startVertex = vertices.get(start_vertex_key);
        Vertex<T> endVertex = vertices.get(end_vertex_key);
        if (startVertex != null && endVertex != null) 
        {
            Edge<T> edge = new Edge<T>(startVertex, endVertex, edge_weight);
            adjList.get(startVertex).add(edge);
            adjMatrix[startVertex.getIndex()][endVertex.getIndex()] = edge;
        }
    }
    
    //Starting BFS
    public LinkedList<T> bfs() 
    {
        LinkedList<T> items = new LinkedList<T>();
        topVertex.setMarked(true);
        bfs(topVertex, items);
        for (Vertex<T> v : vertices.values()) 
        {
            v.setMarked(false);
        }
        
        return items;
    }
    //Performance of BFS
    private void bfs(Vertex<T> vertex, LinkedList<T> items) 
    {
        Queue<Vertex<T>> bfsQueue = new LinkedList<Vertex<T>>(); //BFS QUEUE
        bfsQueue.add(vertex);
        items.add(vertex.getItem());
        while (!bfsQueue.isEmpty()) 
        {
            LinkedList<Edge<T>> edges = adjList.get(bfsQueue.peek());
            Iterator<Edge<T>> iter = edges.iterator();
            while (iter.hasNext()) 
            {
                Vertex<T> v = iter.next().getEndingVertex();
                if (v.isMarked() == false) 
                {
                    v.setMarked(true);
                    items.add(v.getItem());
                    bfsQueue.add(v);
                }
            }
            bfsQueue.poll(); //.offer()---------
        } 
         while(!backEdge.isEmpty())
        {
            backEdge.pop();
        }
        for(Vertex<T> v : vertices.values())
        {
            if(v.isMarked() == false)
            {
                backEdge.push(v.getItem());
            }
        }
    }
    
    //Starting DFS -- Setting all vertices as unmarked
    public LinkedList<T> dfs() 
    {
        LinkedList<T> items = new LinkedList<T>();
        dfs(topVertex, items);
        for (Vertex<T> v : vertices.values()) 
        {
            v.setMarked(false);
        }
        
        return items;
    }
    //Performance of DFS
    private void dfs(Vertex<T> vertex, LinkedList<T> items) 
    {
        items.add(vertex.getItem());
        vertex.setMarked(true);
        int index = vertex.getIndex(); 
        for (int i = 0; i < size; i++) 
        {
            Edge<T> edge = adjMatrix[index][i];
            if (edge != null) 
            {
                if (edge.getEndingVertex().isMarked() == false) 
                {
                    dfs(edge.getEndingVertex(), items);
                }
            }
        }
        topo.push(vertex.getItem()); //Implementation for Topological sort, using DFS
        while(!backEdge.isEmpty())
        {
            backEdge.pop();
        }
        for(Vertex<T> v : vertices.values())
        {
            if(v.isMarked() == false)
            {
                backEdge.push(v.getItem());
            }
        }  
    }

    //Used for Topological Sort
    public boolean backEdgeEmpty()
    {
        if(backEdge.isEmpty())
        {
            return true; //Topological Search will NOT happen
        } else {
            return false; //Perform Topological Sort
        }
    }
    
    
    public void topoSort()
    {
        while(!topo.empty())
        {
            System.out.println(topo.pop()); //back edge -> pop
        }
    }
    
}
