package RandomLibrary;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


// this is a Collection of Random Code ! 
public class Others {

	static public long flipBits2(long n) {
		int k = (int)Math.ceil(Math.log(n)/Math.log(2));
	    int mask = (1 << k) - 1;
	    return ~n & mask;
	}
	static public int BinToDec(String s ){
		int g = 0 ;
		for(int i =s.length() ; i>0;i--){
			char c = s.charAt(i-1);

			int pp = c-48;

			g+=pp*Math.pow(2, s.length()-i);
		}
		return g ;
		
	}
	public static String NextLexo(String g) {
		int length = g.length();
		String result = "";
		int index = -1 ;
		for(int i = length-1 ; i>=0;i--){
			if((int)g.charAt(i)==122){
				result ="a"+result;
			}else{
				int val = (int)g.charAt(i);
				result=(char)(val+1)+""+result;
				index=i;
				break;
			}
		}
		for(int i = index-1 ;i>=0;i--){
			result=g.charAt(i)+""+result;
		}
		return result;
	}
//changes a number from decimal to binary
static String DecToBin(int n){
	String b = "";
	if(n==0){
		return ""+0 ;
	}
	int msk = 1 ;
	double fec =  Math.log(n)/Math.log(2);
	int test = (int) fec;
	
	int factor = (int)Math.ceil(fec);
	if(test==factor){
		factor++;
	}
for(int i =0 ; i<factor;i++){
	int leftmost = n&msk;
	b= leftmost + b ;
	n=n>>1;}

return b.toString();
	
}
//prints a 2D array
static void print2D(int [][]n){
	System.out.println();
	for(int i = 0 ; i<n.length;i++){
	
		for(int j= 0 ; j<n[i].length;j++){
			System.out.print(n[i][j]);
		}
		if(!(i==n.length-1))
		System.out.print("\n");
	}
}
// Modifing the priority Queue comparison ... making its priority tends to the bigger numbers 
static public class compare implements Comparator<Integer> {

	@Override
	public int compare(Integer first, Integer second) {
		int x = (int) first;
		int y = (int) second;
		if (x > y) {
			return -1;
		}
		if (y > x) {
			return 1;
		}
		return 0;
	}
// +  	Comparator<Integer>comm = new compare(); and = new PriorityQueue(comm);
}

private static long gcd(long a, long b)
{
    while (b > 0)
    {
        long temp = b;
        b = a % b; // % is remainder
        a = temp;
    }
    return a;
}
private static long lcm(long a, long b)
{
    return a * (b / gcd(a, b));
}

public static void main(String[]args){
  List<String>dp = new ArrayList<String>();
System.out.println(dp.size());
}
}
