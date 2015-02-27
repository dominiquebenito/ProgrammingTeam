import java.util.*;
import java.io.*;

public class sqfree {
	public static final int MAX = 1000001;
	public static int[] freq = new int[MAX];
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sqfree.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("mysquare.txt")));
		
		solve();
		int cases = in.nextInt();
		
		for (int i = 0; i < cases; i++) {
			int start = in.nextInt();
			out.write(Math.abs(freq[(start == 0) ? 1 : start-1] - freq[in.nextInt()]) + "\n");
		}
		
		in.close();
		out.close();
	}
	
	public static void solve() {
		for (int i = 1; i < MAX; i++) {
			if (Math.pow((int)Math.sqrt(i), 2) != i)
				freq[i] = 1;
			
			freq[i] += freq[i-1];
		}
	}
}
