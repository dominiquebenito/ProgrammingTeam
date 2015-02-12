import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class collide {
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("collide.in"));
        int planets = in.nextInt();
        int counter = 1;
        
        while (planets != 0) {
            BigInteger[] times = new BigInteger[planets];
            
            for (int  i= 0; i < planets; i++)
                times[i] = new BigInteger(in.next());
               
            BigInteger answer = new BigInteger("0");
            
            if (planets >= 2) {
                answer = LCM(times[0], times[1]); 
                
                for (int i = 2; i < planets; i++) {
                    answer = LCM(answer, times[i]);
                }
            }
            
            else {
                answer = LCM(times[0], new BigInteger("1")); 
            }

            System.out.println(counter + ((answer.compareTo(new BigInteger("2147483648")) == -1) ? (": THE WORLD ENDS IN " + answer + " DAYS") : ": NOT TO WORRY"));
            
            planets = in.nextInt();   
            counter++;
        }
        
        in.close();
    }
    
    public static BigInteger GCD (BigInteger one, BigInteger two) {
        return (two.equals(new BigInteger("0"))) ? one : GCD(two, one.mod(two));
    }
    
    public static BigInteger LCM (BigInteger one, BigInteger two) {
        return one.multiply(two).divide(GCD(one, two));
    }
}
