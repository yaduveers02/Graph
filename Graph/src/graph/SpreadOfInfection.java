package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class SpreadOfInfection {

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
		int l;
		
		Pair(int v, int l){
			this.v=v;
			this.l=l;
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
		int t = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[vtes];
		System.out.println(countSpread(graph, src, visited, t));
	}
	
	public static int countSpread(ArrayList<Edge>[] graph, int src, boolean[] visited, int t){
		int count = 0;
		
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		q.add(new Pair(src, 0));
		while(q.size()>0 && t>0) {
			Pair rem = q.removeFirst();
			if(rem.l == t+1) {
				break;
			}
			if(visited[rem.v]==true) continue;
			visited[rem.v] = true;
			count++;
			for(Edge e: graph[rem.v]) {
				if(visited[e.nbr]==false) {
					q.add(new Pair(e.nbr, rem.l+1));
				}
			}
		}
		
		return count;
	}
}
