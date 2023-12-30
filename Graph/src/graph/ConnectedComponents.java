package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import graph.CreatingGraph.Edge;

public class ConnectedComponents {

	static class Edge {

		int src;
		int nbr;
		int wt;

		Edge(int src, int nbr, int wt) {
			this.nbr = nbr;
			this.src = src;
			this.wt = wt;
		}
	}
	
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int vtes =  Integer.parseInt(br.readLine());
		ArrayList<Edge>[] graph = new ArrayList[vtes];
		for(int i=0; i<vtes; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int edges = Integer.parseInt(br.readLine());
		for(int i=0; i<edges; i++) {
			String[] parts = br.readLine().split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			int wt = Integer.parseInt(parts[2]);
			graph[v1].add(new Edge(v1, v2, wt));
			graph[v2].add(new Edge(v2, v1, wt));
		}	
		
		ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
		
		boolean[] visited = new boolean[vtes];
		for(int v=0; v<vtes; v++) {
			if(visited[v]==false) {
				ArrayList<Integer> comp = new ArrayList<Integer>();
				drawTreeAndGraphComps(graph, v, comp, visited);
				comps.add(comp);
			}
		}
		
		System.out.println();
	}
	
	public static void drawTreeAndGraphComps(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
		visited[src] = true;
		comp.add(src);
		for(Edge e: graph[src]) {
			if(visited[e.nbr]==false) {
				drawTreeAndGraphComps(graph, e.nbr, comp, visited);
			}
		}
	}
	 
}
