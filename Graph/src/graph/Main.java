package graph;

import java.util.ArrayList;

public class Main {

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

	public static void main(String[] args) {
		int vtes = 7;
		ArrayList<Edge>[] graph = new ArrayList[vtes];
		for (int i = 0; i < vtes; i++) {
			graph[i] = new ArrayList<Edge>();
		}

		graph[0].add(new Edge(0, 1, 10));
		graph[0].add(new Edge(0, 3, 40));

		graph[1].add(new Edge(1, 0, 10));
		graph[1].add(new Edge(1, 2, 10));

		graph[2].add(new Edge(2, 3, 10));
		graph[2].add(new Edge(2, 1, 10));

		graph[3].add(new Edge(3, 0, 40));
		graph[3].add(new Edge(3, 2, 10));
		graph[3].add(new Edge(3, 4, 2));

		graph[4].add(new Edge(4, 3, 2));
		graph[4].add(new Edge(4, 5, 5));
		graph[4].add(new Edge(4, 6, 8));

		graph[5].add(new Edge(5, 4, 5));
		graph[5].add(new Edge(5, 6, 3));

		graph[6].add(new Edge(6, 4, 8));
		graph[6].add(new Edge(6, 5, 3));

		boolean[] visited = new boolean[7];
//		System.out.println(hasPath(graph, 2, 4, visited));
		printAllPath(graph, 0, 6, visited, 0+"");
	}

	public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
		if (src == dest)
			return true;

		visited[src] = true;
		for (Edge edge : graph[src]) {
			if (visited[edge.nbr] == false) {
				boolean nbrHasPath = hasPath(graph, edge.nbr, dest, visited);
				if (nbrHasPath == true) {
					return true;
				}
			}
		}
		return false;
	}

	public static void printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {

		if(src==dest) {
			System.out.println(psf);
			return;
		}
		
		visited[src] = true;
		
		for(Edge edge: graph[src]) {
			if(visited[edge.nbr]==false) {
				printAllPath(graph, edge.nbr, dest, visited, psf+edge.nbr);
			}
		}
		
		visited[src] = false;
	}
}
