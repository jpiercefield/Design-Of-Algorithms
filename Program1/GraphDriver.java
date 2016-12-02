/**
 * @James Logan Piercefield
 * 
 * CSC 2400 - Design of Algorithms
 * Dr. Boshart, Spring 2016 - Program 1
 * 
 * Driver: 
 * -- Performs Breadth First Search, Depth First Search, and Topological sort (when DAG).
 * 
 * @Parameters: "vertices file", "edges file"
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;


public class GraphDriver 
{
    public static void main(String[] args) 
    {
        //Create Graph from vertices
        Graph<String> graph = createGraph(args[0]);
        //Add Edges to graph
        addEdges(graph, args[1]);
        
        //Start Breadth First Search
        LinkedList<String> bfsVertices = graph.bfs();
        System.out.println("BFS:");
        Iterator<String> iter = bfsVertices.iterator();
        while(iter.hasNext()) 
        {
            System.out.println(iter.next());
        }
        graph.addBackEdge();
        
        //Start Depth First Search
        LinkedList<String> dfsVertices = graph.dfs();
        System.out.println("\nDFS:");
        iter = dfsVertices.iterator();
        while(iter.hasNext()) 
        {
            System.out.println(iter.next());
        }
        graph.addBackEdge();
        
        //Start Topological Sort, Outputs Nothing Under "Topological Sort:" if can't be done.
        System.out.println("\nTopological Sort:");
        if(!graph.backEdgeEmpty())
        {   
            graph.addBackEdge();
            graph.topoSort();
        }
        
    }
    
    //Reads in string "x y #"  Where 'x' is an edge directed to 'y', x->y (directed vertices)
    //'#' is weight (for weighted graphs)
    private static Graph<String> createGraph(String fileName) 
    {
        LinkedList<String> verts = new LinkedList<String>();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String vert;
            vert = br.readLine();
            while (vert != null) 
            {
                verts.add(vert);
                vert = br.readLine();
            }
            br.close();
        } catch (IOException excep) {
            excep.printStackTrace();
        }
                             //new G ... size
        Graph<String> graph = new Graph<String>(verts.size());
        Iterator<String> iter = verts.iterator();
        while (iter.hasNext()) 
        {
            graph.addVertex(iter.next());
        }
        
        return graph;
    }
    
    //Reads in edges from text file, adds the edge to graph.
    private static void addEdges(Graph<String> graph, String fileName) 
    {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String edge = br.readLine();
            while (edge != null) 
            {
                String[] splitLine = edge.split(" ");
                graph.addEdge(splitLine[0], splitLine[1], Double.parseDouble(splitLine[2]));
                edge = br.readLine();
            }
            br.close();
        } catch (IOException excep) {
            excep.printStackTrace();
        }
    }

}
