import java.util.*;
import java.io.*;

public class primes {
	public static final int MAX = (int)Math.pow(10, 7) + 1;
	public static boolean[] primes = new boolean[MAX];
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new File("primes.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("myprimes.txt")));
		
		int cases = in.nextInt();
		
		solve();
		
		for (int i = 0; i < cases; i++) {
			out.write(primes[in.nextInt()] ? "1\n" : "0\n");
		}
		
		in.close();
		out.close();
	}
	
	public static void solve() {
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		
		for (int i = 2; i < Math.sqrt(MAX) + 1; i++) {
			if (primes[i]) {
				for (int  j = 2*i; j < MAX; j += i) {
					primes[j] = false;
				}
			}
		}
	}
}