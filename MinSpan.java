/*
 * James 'Logan' Piercefield
 * Project 2 - CSC 2400 Design Of Algorithms
 */

import java.io.*;
import java.util.*;

public class MinSpan 
{
    public static void main(String[] args) 
    {
        if(args.length != 5)
        {
            System.out.println("Usage: MinSpan <vertice file> <edge file> <source vertex key> <dest vertex key> [output file name]");
        }
        if(args.length < 4)
        {
            return;
        }

        Graph<String> graph = createGraph(args[0]);
        addEdges(graph, args[1]);

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(new File(args[4])));
        } catch (Exception e) {
            System.out.println("No output file");
        }

        List<Edge<String>> minEdgesPrim = graph.prim();
        out(pw, "PRIM");
        Iterator<Edge<String>> iter = minEdgesPrim.iterator();
        while (iter.hasNext()) 
        {
            out(pw, iter.next().toString());
        }

        out(pw, "");
        out(pw, "KRUSKAL");
        List<Edge<String>> minEdgesKruskal = graph.kruskal();
        iter = minEdgesKruskal.iterator();
        while (iter.hasNext()) 
        {
            out(pw, iter.next().toString());
        }

        out(pw, "");
        out(pw, "SHORTEST PATH");
        List<Edge<String>> shortestPath = graph.shortestPath(args[2],args[3]);
        if(shortestPath == null)
        {
            out(pw, "Invalid source or dest!");
            pw.close();
            return;
        }
        iter = shortestPath.iterator();
        while (iter.hasNext()) 
        {
            out(pw, iter.next().toString());
        }

        if(pw != null)
        {
            pw.close();
        }     
    }

    private static void out(PrintWriter pw, String s) 
    {
        System.out.println(s);
        if(pw != null)
        {
            pw.println(s);
        }
    }

    private static Graph<String> createGraph(String fileName) 
    {
        Graph<String> g = new Graph<String>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            String city;
            while (sc.hasNextLine()) 
            {
                city = sc.nextLine();
                if(city.length() == 0)
                {
                    continue;
                }
                g.addVertex(city);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return g;
    }

    private static void addEdges(Graph<String> graph, String fileName) 
    {
        try {
            Scanner sc = new Scanner(new File(fileName));
            String line;
            while (sc.hasNextLine()) 
            {
                line = sc.nextLine();
                if(line.length() == 0)
                {
                    continue;
                }
                String[] tokens = line.split(" ");
                graph.addEdge(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
