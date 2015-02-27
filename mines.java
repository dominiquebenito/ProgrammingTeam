import java.util.*;
import java.io.*;

public class mines {
	public static int[][][] mine;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("mines.in"));
		int dimension = in.nextInt();
		mine = new int[dimension][dimension][dimension];
		
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j <dimension; j++)
				for (int k = 0; k < dimension; k++)
					mine[i][j][k] = in.nextInt();
		
		int cases = in.nextInt();
		
		for (int i = 1; i <= cases; i++) {
			System.out.println("Simulation #" + i + ", volume cleared is " + 
					Math.round(solve(in.nextInt(), in.nextInt(), in.nextInt(), new boolean[dimension][dimension][dimension])/2.0) 
					+ " cubic feet.\n");
		}
		
		in.close();
	}
	
	public static int solve(int x, int y, int z, boolean[][][] visited) {
		if (mine[x][y][z] != 1)
			return 0;		
		
		visited[x][y][z] = true;
		int answer = 1;
		
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				for (int k = -1; k< 2; k++)
					if (valid(x,y,z,x+i,y+j,z+k) && !visited[x+i][y+j][z+k])
						answer += solve(x+i,y+j,z+k, visited) + 1;
		
		return answer;
	}
	
	public static boolean valid(int oldx, int oldy, int oldz, int x, int y, int z) {
		// Check if in bounds
		if (x < 0 || y < 0 || z < 0 ||
			x >= mine.length || y >= mine.length || z >= mine.length)
			return false;
		
		// No moving in diagonal directions
		else if ((oldx == x && oldy != y && oldz != z) ||
				 (oldx != x && oldy == y && oldz != z) ||
				 (oldx != x && oldy != y && oldz == z) ||
				 (oldx != x && oldy != y && oldz != z))
			return false;
		
		// Check if there's a rock to blow up
		else if (mine[x][y][z] == 0)
			return false;
		
		return true;
	}
}
