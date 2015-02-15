import java.util.*;
import java.io.*;

public class bunnies {
	public static char[][] cage;
	public static int row, col;
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new File("bunnies.in"));
		int cases = in.nextInt();
		
		for (int i = 0; i < cases; i++) {
			row = in.nextInt();
			col = in.nextInt();
			
			cage = new char[row][col];
			int x = 0, y = 0;
			
			for (int j = 0; j < row; j++) {
				String line =in.next();
				
				for (int k = 0; k < col; k++) {
					cage[j][k] = line.charAt(k);
					if (cage[j][k] == 'P') {
						x = j;
						y = k;
					}
				}
			}
			
			System.out.println(solve(x,y, new boolean[row][col]) ? "yes" : "no");
		}
		
		in.close();
	}
	
	public static boolean solve(int x, int y, boolean[][] visited) {
		if (cage[x][y] == 'C')
			return true;
		
		visited[x][y] = true;
		
		boolean answer = false;
		
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++) 
				if (valid(x, y, x+i, y+j) && !visited[x+i][y+j])
					answer |= solve(x+i, y+j, visited);
		
		return answer;
	}
	
	public static boolean valid(int oldx, int oldy, int x, int y) {
		// Check if in bounds
		if (x < 0 || y < 0 || x >= row || y >= col)
			return false;
		
		// No moving in diagonal directions
		else if (oldx != x && oldy != y)
			return false;
		
		// Check if not blocked
		else if (cage[x][y] == '#')
			return false;
		
		return true;
	}
}
