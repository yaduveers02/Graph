package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/*1. You are given a graph.
2. You are required to find and print if the graph is bipartite

Note -> A graph is called bipartite if it is possible to split it's vertices in two sets of mutually 
               exclusive and exhaustive vertices such that all edges are across sets.*/

public class IsBipartite {
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
		int l;
		
		Pair(int v, String psf, int l){
			this.v=v;
			this.psf = psf;
			this.l = l;
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
		
		int[] visited = new int[vtes];
		Arrays.fill(visited, -1);
		
		for(int v=0; v<vtes; v++) {
			if(visited[v] != -1) {
				boolean isCompBipartite = checkComponentForBipartiteness(graph, v, visited);
				if(!isCompBipartite) {
					System.out.println(false);
					return;
				}
			}
		}
		System.out.println(true);
	
	}
	
	public static boolean checkComponentForBipartiteness(ArrayList<Edge>[] graph, int src, int[] visited) {
		ArrayDeque<Pair> q = new ArrayDeque<>();
		q.add(new Pair(src, src+"", 0));
		
		while(q.size()>0) {
			Pair rem = q.removeFirst();
			
			if(visited[rem.v]!=-1) {
				if(rem.l != visited[rem.v]) {
					return false;
				}
			}else {
				visited[rem.v] = rem.l;
			}
			
			for(Edge e: graph[rem.v]) {
				if(visited[e.nbr] == -1) {
					q.add(new Pair(e.nbr, rem.psf + e.nbr, rem.l+1));
				}
			}
		}
		return true;
	}
}
