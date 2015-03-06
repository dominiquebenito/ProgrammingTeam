import java.util.*;

public class EightPuzzle {
	public static int[][] board = new int[3][3];
	public static HashMap<String, Integer> states = new HashMap<String, Integer>();
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
			
		BFS();
		
		int cases = in.nextInt();
		for (int i = 0; i < cases; i++) {
			String current_state = "";
			
			for (int x = 0; x < 9; x++)
				current_state += in.nextInt();
			
			System.out.println(states.get(current_state));
		}
		
		in.close();
	}
	
	private static void BFS() {
	     Queue<String> list = new LinkedList<String>();
	     list.add("123456780");
	     	     
    	 states.put("123456780", 0);
    	 int distance = 1, last_added = 0;
	     
	     while(!list.isEmpty()) {
	    	     	 
    		 String current_state = list.remove();
	    	 ArrayList<String> new_states = solve(current_state);
	    	 last_added += new_states.size();
	    	 
	    	 for (String s : new_states) {
	    		 states.put(s, distance);
	    		 list.add(s);
	    	 }
	    	 
	    	 if (list.size() == last_added) {
	    		 distance++;
	    		 last_added = 0;
	    	 }
	     }
	}
	
	public static ArrayList<String> solve(String State) {
		ArrayList<String> new_states = new ArrayList<String> ();
		
		// Convert string to board
		Board state = new Board(State);
		
		// Find all adjacent states
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isValid(state.x, state.y, state.x + j, state.y + i)) {
					String new_state = swap(state, j, i);
					
					
					if (states.get(new_state) == null)
						new_states.add(new_state);
					
					state = new Board(State);
				}
			}
		}
		
		return new_states;
	}
	
	public static String swap(Board state, int x, int y) {		
		int temp = state.board[state.y][state.x];
		state.board[state.y][state.x] = state.board[state.y+y][state.x+x];
		state.board[state.y+y][state.x+x] = temp;
		
		return state.toString();
		
	}
	
	public static boolean isValid(int oldx, int oldy, int x, int y) {
		//Out of bounds
		if (x < 0 || x >= 3 || y < 0 || y >= 3)
			return false;
		
		//Same spot
		if (x == oldx && y == oldy)
			return false;
		
		// Diagonals
		if (x != oldx && y != oldy)
			return false;
		
		return true;
	}
	
	static class Board {
		int[][] board = new int[3][3];
		int x = 0, y = 0;
		
		public Board(String state) {
			int spot = 0;
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					board[i][j] = Integer.parseInt(state.charAt(spot++) + "");
					
					if (board[i][j] == 0) {
						y = i;
						x = j;
					}
				}
		}
		
		@Override
		public String toString() {
			String result = "";
			
			for (int  i = 0; i < 3; i++)
				for (int  j = 0; j < 3; j++)
					result += board[i][j] + "";
			
			return result;
		}
	}
}
