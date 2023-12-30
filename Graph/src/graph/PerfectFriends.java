package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PerfectFriends {

	public static class Edge{
		int v;
		int n;
		
		Edge(int v, int n){
			this.v = v;
			this.n = n;
		}
		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] graph = new ArrayList[n];
		
		for(int v=0; v<n; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for(int e=0; e<k ;e++) {
			String line = br.readLine();
			String[] parts = line.split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			
			graph[v1].add(new Edge(v1, v2));
			graph[v2].add(new Edge(v2, v1));
		}
		
		ArrayList<ArrayList<Integer>> components = new ArrayList<>();
		boolean[] visited = new boolean[n];
		for(int v=0; v<n; v++) {
			if(visited[v]==false) {
				ArrayList<Integer> comp = new ArrayList<Integer>();
				getComponents(graph, v, comp, visited);
				components.add(comp);
			}
			
		}
		
		System.out.println(components.toString());
		
		int pair=0;
		for(int i=0; i<components.size(); i++) {
			for(int j= i+1; j<components.size(); j++) {
				int count = components.get(i).size()*components.get(j).size();
				pair+=count;
			}
		}
		System.out.println(pair);
	}
	
	public static void getComponents(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp,  boolean[] visited) {
		visited[src] = true;
		comp.add(src);
		for(Edge e: graph[src]) {
			if(visited[e.n]==false) {
				getComponents(graph, e.n, comp, visited);
			}
		}
	}
}
