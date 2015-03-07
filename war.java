import java.util.*;
import java.io.*;

public class war {
	public static int[][] board;
	public static String[] sides;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int classes = in.nextInt();
		
		for (int i = 0; i < classes; i++) {
			int students = in.nextInt();
			int enemies = in.nextInt();
			
			
			board = new int[students][students];
			for (int[] row : board)
				Arrays.fill(row, 1);
			
			for (int j = 0; j < students; j++)
				board[j][j] = 0;
			
			for (int j = 0; j < enemies; j++) {
				int one = in.nextInt(), two = in.nextInt();
				board[one][two] = 0;
				board[two][one] = 0;
			}
			
			sides = new String[students];
			bfs();
			
			System.out.println(isValid() ? "YES" : "NO");
		}
		
		in.close();
	}
	
	public static int sum (int row) {
		int sum = 0;
		
		for (int i = 0; i < board.length; i++)
			sum += board[row][i];
		
		return sum;
	}
	
	public static void bfs() {	
		for (int i = 0; i < sides.length; i++) {
			if (sum(i) != 0) {
				if (sides[i] == null)
						sides[i] = (i == 0) ? "B" : (sides[i-1] == "B") ? "W" : "B";
				assign(i);
			}
		}
	}
	
	public static void assign(int node) {
		Queue<Integer> list = new LinkedList<Integer>();
		list.add(node);
		
		while (!list.isEmpty()) {
			int parent = list.remove();
			
			for (int i = 0; i < board.length; i++) {
				if (board[parent][i] == 0 && sides[i] == null) {
						sides[i] = sides[parent].equals("B") ? "W" : "B";
						list.add(i);
					}
			}
		}
	}
	
	public static boolean isValid() {
		int w = 0, b = 0;
		
		for (String s : sides) {
			if (s == null)
				return false;
			
			if (s.equals("B")) 
				b++;
			
			else 
				w++;
		}
		
		if (b == 1 || w == 1)
			return false;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0 && sides[i].equals(sides[j]) && i != j)
					return false;
			}
			
		}
		
		return true;
	}
}
