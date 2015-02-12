import java.util.*;
import java.io.*;

public class vacation {
	public static final int RIDE_TIME = 120;
	public static double total_time = Double.MAX_VALUE;
	public static Points[] locations;
	public static Points[] blocked;
	
	public static void main (String[] args) throws IOException{
		Scanner in = new Scanner (System.in);
		int parks = in.nextInt();
		
		for (int i = 0; i < parks; i++ ) {
			int rides = in.nextInt();
			int blocks = in.nextInt();
			
			locations = new Points[rides];
			blocked = new Points[blocks];
			
			for (int j = 0; j < rides; j++)
				locations[j] = new Points(in.nextInt(), in.nextInt(), 1);
			
			for (int j = 0; j < blocks; j++)
				blocked[j] = new Points(in.nextInt(), in.nextInt(), 0);
			
			perm(0, new int[rides], new boolean[rides]);
			
			System.out.println("Vacation #" + (i+1));
			
			if (total_time != Double.MAX_VALUE)
				System.out.println("Jimmy can finish all of the rides in " + total_time + " seconds.");
			
			else 
				System.out.println("Jimmy should plan this vacation a different day.");
			
			total_time = Double.MAX_VALUE;
		}
		
		in.close();
	}
	
	public static void perm(int current, int[]spots, boolean[] used) {
		if (current == used.length) {
			if (valid(spots)) {
				calculateTime(spots);
			}
		}
		
		else {
			for (int i = 0; i < used.length; i++) {
				if (spots[0] != 0)
					return;
				
				if (!used[i]) {
					used[i] = true;
					spots[current] = i;
					perm(current+1, spots, used);
					used[i] = false;
				}
			}
		}
	}
	
	public static boolean valid(int[] spots) {
		if (spots[0] != 0)
			return false;
		
		else  {
			for (int i = 1; i < spots.length; i++) {
				for (int j = 0; j < blocked.length; j++) {
					if ((spots[i] == blocked[j].ride1 && spots[i-1] == blocked[j].ride2) || 
							(spots[i] == blocked[j].ride2 && spots[i-1] == blocked[j].ride1)) {
						return false;
					}
				}
			}
			
			return true;
		}
	}
	
	public static void calculateTime(int[] spots) {
		double temp = 0;
		
		for (int i = 1; i < spots.length; i++) {
			temp += Math.sqrt(Math.pow(locations[spots[i]].x - locations[spots[i-1]].x, 2) + 
					Math.pow(locations[spots[i]].y - locations[spots[i-1]].y, 2)) + RIDE_TIME;
		}
		
		if (temp < total_time) 
			total_time = temp;
	}
	
	static class Points {
		int x = 0; int y = 0;
		int ride1 = 0, ride2 = 0;
		
		public Points (int one, int two, int opt) {
			if (opt == 1) {
				x = one;
				y = two;
			}
			else {
				ride1 = one - 1;
				ride2 = two - 1;
			}
		}
	}
}
