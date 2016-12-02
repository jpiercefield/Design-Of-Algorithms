import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class MinSpan {
	
	public static void main(String[] args) {
		
		Graph<String> graph = createGraph(args[0]);
		addEdges(graph, args[1]);
		
		File file = new File(args[2]);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
        PrintWriter pw = new PrintWriter(bw);
		
		List<Edge<String>> minEdgesPrim = graph.prim();
		out(pw, "PRIM");
		Iterator<Edge<String>> iter = minEdgesPrim.iterator();
		while (iter.hasNext()) {
			out(pw, iter.next().toString());
		}
		
		out(pw, "");
		out(pw, "KRUSKAL");
		List<Edge<String>> minEdgesKruskal = graph.kruskal();
		iter = minEdgesKruskal.iterator();
		while (iter.hasNext()) {
			out(pw, iter.next().toString());
		}
		
		pw.close();
		
	}
	
	private static void out(PrintWriter pw, String s) {
		System.out.println(s);
		pw.println(s);
	}
	
	private static Graph<String> createGraph(String fileName) {
		LinkedList<String> cities = new LinkedList<String>();

		try {
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String city;
			city = br.readLine();
			while (city != null) {
				//System.out.println(city);
				cities.add(city);
				city = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Graph<String> graph = new Graph<String>(cities.size());
		Iterator<String> iter = cities.iterator();
		while (iter.hasNext()) {
			graph.addVertex(iter.next());
		}
		
		return graph;
	}
	
	private static void addEdges(Graph<String> graph, String fileName) {
		try {
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				//System.out.println(line);
				String[] splitLine = line.split(" ");
				graph.addEdge(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]));
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
