import java.util.*;
import java.io.*;

public class profits {
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new File("profits.txt"));
		//BufferedWriter out = new BufferedWriter(new FileWriter(new File("myprofits.txt")));
		
		int days = in.nextInt();
		
		while (days != 0) {
			int[] profits = new int[days];
			
			for (int i = 0; i < days; i++) 
				profits[i] = in.nextInt();
			
			System.out.println(solve(profits));
			
			days = in.nextInt();
		}
		
		in.close();
		//out.close();
	}
	
	private static int solve(int[] array){
		int max = 0, min = Integer.MIN_VALUE, sum = 0;
		
		for (int j = 0; j < array.length; j++){
			sum += array[j];
			
			if (sum > max) {
				max = sum;
			}
			
			else if (sum < 0){
				if (sum > min)
					min = sum;
				sum = 0;
			}
		}
		
		if (max == 0 && min != Integer.MAX_VALUE)
			max = min;
		
		return max;
	}

}