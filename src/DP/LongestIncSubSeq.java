package DP;

import java.util.Arrays;

public class LongestIncSubSeq {

	static int[]a;
/* not sure of this code 100%
 * for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] <= arr[i]){
					lis[i] = lis[j] + 1;
				max = Math.max(lis[i], max);
				}}
		}*/
	static int[] lis1(int[]a,boolean inc) //lis ending at i 
	{
		int n=a.length;
		int []l=new int[n];
		for(int i =0; i<n; i++)
		{
			int curL=1;
			for(int j =0; j<n;j++)
				if(inc && a[j] < a[i] || !inc && a[j] > a[i])
					curL = Math.max(curL, l[j] + 1);
			l[i] = curL;
		}
		return l;
	}
	
	
	static int[] lis2(int[]a,boolean inc)//lis starting at i
	{
		int n=a.length;
		int []l=new int[n];
		for(int i =n-1; i>=0; i--)
		{
			int curL=1;
			for(int j =n-1; j>i;j--)
				if(inc && a[j] > a[i] || !inc && a[j] < a[i])
					curL = Math.max(curL, l[j] + 1);
			l[i] = curL;
		}
		return l;
	}

	public static void main(String[] args) {
		
		a=new int[]{-7,10,9,2,3,8,8,1};
		
		int[] l1=lis1(a,true);
		System.out.println(Arrays.toString(l1));
		
		int[] l2=lis2(a,true);
		System.out.println(Arrays.toString(l2));
		
		
	}
}
