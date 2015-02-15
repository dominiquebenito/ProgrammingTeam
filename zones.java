import java.util.*;
import java.io.*;

public class zones {
	public static int planned, actual, max = 0;
	public static int[] peeps;
	public static Info[] shared;
	public static Info best;
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new File("zones.in"));
		int counter = 1;
		planned = in.nextInt();
		actual = in.nextInt();
		
		while (planned != 0 && actual != 0) {
			peeps = new int[planned];
			
			for (int i = 0; i < planned; i++)
				peeps[i] = in.nextInt();
			
			shared = new Info[in.nextInt()];
			
			for (int i = 0; i < shared.length; i++) {
				int size = in.nextInt();
				shared[i] = new Info();
				
				for (int j = 0; j < size; j++) {
					shared[i].towers.add(in.nextInt() - 1);
				}
				
				Collections.sort(shared[i].towers);
				shared[i].common = in.nextInt();
			}
			
			System.out.println("Case Number  " + counter++);
			solve(0, 0, new boolean[planned]);
			System.out.println("Number of Customers: " + max);
			System.out.println("Locations recommended: " + best + "\n");
			
			planned = in.nextInt();
			actual = in.nextInt();
			
			max = 0;
		}
		
		in.close();
	}
	
	public static void solve(int cur, int size, boolean[] visited) {
		if (size == actual) {
			Info tmp = calculate(visited);
			
			if (max < tmp.common) {
				max = tmp.common;
				best = new Info();
				best.towers = tmp.towers;
			}
		}
		
		else if (cur == visited.length) 
			return;
		
		
		else {
			visited[cur] = true;
			solve(cur+1, size+1, visited);
			visited[cur] = false;
			solve(cur+1, size, visited);
		}
	}
	
	public static Info calculate(boolean[] used) {
		Info answer = new Info();
		int sum = 0;
		ArrayList<Integer> tower = new ArrayList<Integer>();
		
		for (int i = 0; i < used.length; i++) {
			if (used[i]) {
				sum += peeps[i];
				tower.add(i);
			}
		}
		
		for (int i = 0; i < shared.length; i++) {
			int counter = 0;
				
			for (int x = 0; x < tower.size(); x++) {
				if (shared[i].towers.contains(tower.get(x))) {
					counter++;
				}
			}
				
			if (counter >= 2) {
				sum -= (counter-1) * shared[i].common;
			}
		}
		
		answer.towers = tower;
		answer.common = sum;
		
		return answer;
	}
	
	static class Info {
		int common = 0;
		ArrayList<Integer> towers;
		
		public Info()  {
			towers = new ArrayList<Integer>();
		}
		
		@Override
		public String toString() {
			String answer = "";
			Collections.sort(towers);
			
			for (int i : towers)
				answer += (i+1) + " ";
			
			return answer;
		}
	}
}
