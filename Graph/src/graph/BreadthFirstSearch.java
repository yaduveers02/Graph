package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

import graph.CreatingGraph.Edge;

public class BreadthFirstSearch {

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
	
	public static class Pair{
		int v;
		String psf;
		
		Pair(int v, String psf){
			this.v=v;
			this.psf = psf;
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
	
		int src = Integer.parseInt(br.readLine());
		ArrayDeque<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(src, src+""));
		boolean[] visited = new boolean[vtes];
		while(queue.size()>0) {
//			removeFirst() method return a Pair and if remove() is used only then it will return a boolean value
//			remove
			Pair rem = queue.removeFirst();
//			mark if not marked else continue
			if(visited[rem.v]==true) {
				continue;
			}
			visited[rem.v]=true;
//			work - to print
			System.out.println(rem.v+"@"+rem.psf);
//			add if not marked
			for(Edge e: graph[rem.v]) {
				if(visited[e.nbr]==false) {
					queue.add(new Pair(e.nbr, rem.psf+e.nbr));
				}
			}
		}
	}
}
