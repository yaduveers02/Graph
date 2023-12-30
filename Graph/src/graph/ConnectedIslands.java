package graph;

import java.io.IOException;

public class ConnectedIslands {
	
	
	public static void main(String[] args) throws Exception, IOException {
		int[][] arr = {{0,0,1,1,1,1,1},{0,0,1,1,1,1,1},{1,1,1,1,1,1,0},{1,1,0,0,0,1,0},{1,1,1,1,0,1,0},{1,1,1,1,0,1,0},{1,1,0,1,1,1,0},{0,0,1,1,1,1,0}};
		boolean[][] visited = new boolean[arr.length][arr[0].length]; 
		int count=0;
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(arr[i][j]==0 && visited[i][j]==false) {
					drawTreeForComponent(arr, i, j, visited);
					count++;
				}
			}
		}
		System.out.println(count);
	}
	
	public static void drawTreeForComponent(int[][] arr, int i, int j, boolean[][] visited) {
		//reactive conditioning(first call is made and if call is wrong then we return)
		if(i<0 || j<0 || i>=arr.length || j>=arr[0].length || visited[i][j]==true || arr[i][j] == 1) {
			return;
		}
		
		visited[i][j] = true;
		drawTreeForComponent(arr, i-1, j, visited);
		drawTreeForComponent(arr, i, j-1, visited);
		drawTreeForComponent(arr, i+1, j, visited);
		drawTreeForComponent(arr, i, j+1, visited);		
	}
}
