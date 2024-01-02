package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import graph.SpreadOfInfection.Edge;

/*1. You are given a graph and a source vertex. The vertices represent computers and the edges 
represent length of LAN wire required to connect them.
2. You are required to find the minimum length of wire required to connect all PCs over a network. 
Print the output in terms of which all PCs need to be connected, and the length of wire between 
them.

Input
7
8
0 1 10
1 2 10
2 3 10
0 3 40
3 4 2
4 5 3
5 6 3
4 6 8

Output
[1-0@10]
[2-1@10]
[3-2@10]
[4-3@2]
[5-4@3]
[6-5@3]*/
public class PrimAlgorithm {

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
	
	public static class Pair implements Comparable<Pair>{
		int v;
		int av;
		int wt;
		
		Pair(int v, int av, int wt){
			this.v=v;
			this.av =av;
			this.wt = wt;
		}
		
		public int compareTo(Pair o) {
			return this.wt-o.wt;
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
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(src, -1, 0));
		boolean[] visited = new boolean[vtes];
		
		while(pq.size()>0) {
			Pair rem = pq.remove();
			
			if(visited[rem.v]==true) continue;
			visited[rem.v]=true;
			
			if(rem.av != -1) {
				System.out.println("[ "+rem.v+ " - "+ rem.av + "@" + rem.wt +"]");
			}
			
			for(Edge e: graph[rem.v]) {
				if(visited[e.nbr]==false) {
					pq.add(new Pair(e.nbr, rem.v, e.wt));
				}
			}
		}
		
	}
}
