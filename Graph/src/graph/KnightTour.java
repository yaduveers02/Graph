package graph;

public class KnightTour {

	/*
	 * 1. You are given a number n, the size of a chess board. 2. You are given a
	 * row and a column, as a starting point for a knight piece. 3. You are required
	 * to generate the all moves of a knight starting in (row, col) such that knight
	 * visits all cells of the board exactly once.
	 */
	
	public static void main(String[] args) {
		int n=5;
		int i=2, j=0;
		int[][] arr = new int[n][n];
		knightMove(arr, i, j, 1);
		
	}
	
	public static void knightMove(int[][] chess, int i, int j, int move) {
		if(i<0 || j<0 || j>=chess[0].length || i>=chess.length ||chess[i][j]>0) {
			return;
		}
		if(move==chess.length*chess.length) {
			chess[i][j]=move;
			printChess(chess);
			System.out.println();
			chess[i][j]=0;
			return;
		}
		
		chess[i][j] = move;
		/*
		 * knightMove(chess, i-2, j+1, move++); 
		 * knightMove(chess, i-1, j+2, move++);
		 * knightMove(chess, i+1, j+2, move++); 
		 * knightMove(chess, i+2, j+1, move++);
		 * knightMove(chess, i+2, j-1, move++); 
		 * knightMove(chess, i+1, j-2, move++);
		 * knightMove(chess, i-1, j-2, move++); 
		 * knightMove(chess, i-2, j-1, move++);
		 */		

		knightMove(chess, i-2, j+1, move+1);
		knightMove(chess,  i-1, j+2, move+1);
		knightMove(chess,  i+1, j+2, move+1);
		knightMove(chess,  i+2, j+1, move+1);
		knightMove(chess,  i+2, j-1, move+1);
		knightMove(chess, i+1, j-2, move+1);
		knightMove(chess,  i-1, j-2, move+1);
		knightMove(chess,  i-2, j-1, move+1);

		chess[i][j]=0;		
	}
	
	public static void printChess(int[][] chess) {
		for(int i=0; i<chess.length; i++) {
			for(int j=0; j<chess[0].length; j++) {
				System.out.print(chess[i][j]+" ");
			}
			System.out.println();
		}
	}

}
