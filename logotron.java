import java.util.*;

public class logotron {
	public static int answer = 0;
	public static Statement[] info;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int i = 1; i <= cases; i++) {
			int robots = in.nextInt();
			int num = in.nextInt();
			
			info = new Statement[num];
			
			for (int j = 0; j < num; j++)
				info[j] = new Statement(in.nextInt(), in.nextInt(), in.next());
			
			combination(robots);
			
			System.out.println("Case #" + i + ": " + answer);
			answer = 0;
		}
		
		in.close();
	}
	
	// One represents T and zero represents C
	private static void combination(int size) {
	     for (int i = 0; i < (1 << size); i++) {	          
	          if (isValid(i, size))
	        	  answer++;
	     }
	}
	
	public static boolean isValid(int num, int robots) {
		String binary = Integer.toBinaryString(num);
		int size = robots - binary.length();
		
		if (binary.length() != robots)
			for (int i = 0; i < size; i++)
				binary = "0" + binary;
		
		for (int i = 0; i < info.length; i++) {
			if (binary.charAt(info[i].current) == '1' &&  
				info[i].type != Integer.parseInt(binary.charAt(info[i].accused) + ""))
					return false;
			
			else if (binary.charAt(info[i].current) == '0' &&  
				info[i].type == Integer.parseInt(binary.charAt(info[i].accused) + ""))
					return false;
		}
		
		return true;
	}
	
	static class Statement {
		int current = 0, accused = 0, type = 0;
		
		public Statement(int one, int two, String n) {
			current = one - 1;
			accused = two - 1;
			type = (n.equals("C")) ? 0 : 1;
		}
		
	}
}
