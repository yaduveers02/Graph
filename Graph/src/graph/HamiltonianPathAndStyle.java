package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/*1. You are given a graph and a src vertex.
2. You are required to find and print all hamiltonian paths and cycles starting from src. The cycles must end with "*" and paths with a "."

Note -> A hamiltonian path is such which visits all vertices without visiting any twice. A hamiltonian path becomes a cycle if there is an edge between first and last vertex.
Note -> Print in lexicographically increasing order.*/

/*input
7
9
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
4 5 10
5 6 10
4 6 10
2 5 10
0 - start
*/

/* Ans
0123456.
0123465.
0125643*
0346521*
*/

public class HamiltonianPathAndStyle {

	public static class Edge{
		int v;
		int n;
		int wt;
		
		Edge(int v, int n, int wt){
			this.v = v;
			this.n = n;
			this.wt = wt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int vtes = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] graph = new ArrayList[vtes];

		for(int v=0; v<vtes; v++) {
			graph[v] = new ArrayList();
		}
		
		int edges = Integer.parseInt(br.readLine());
		
		for(int e=0; e<edges; e++) {
			String line = br.readLine();
			String[] parts = line.split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			int wt = Integer.parseInt(parts[2]);
			
			graph[v1].add(new Edge(v1, v2, wt));
			graph[v2].add(new Edge(v2, v1, wt));
		}
		
		int src = Integer.parseInt(br.readLine());
		
		HashSet<Integer> set = new HashSet<>();
		createGraphForHamiltonian(graph, src, set, src +"", src);
	}
	
	public static void createGraphForHamiltonian(ArrayList<Edge>[] graph, int src, HashSet<Integer> set, String psf, int osrc) {
		if(graph.length-1 == set.size()) {
			boolean cycleFound = false;
			for(Edge e: graph[src]) {
				if(e.n==osrc) {
					cycleFound = true;
				}
			}
			if(cycleFound) {
				System.out.println(psf+"*");
			}else {
				System.out.println(psf+".");
			}
			return;
		}
			
		set.add(src);
		for(Edge e: graph[src]) {
			if(set.contains(e.n)==false) {
				createGraphForHamiltonian(graph, e.n, set, psf+e.n, osrc);
			}
		}
		set.remove(src);
	}
	
}
