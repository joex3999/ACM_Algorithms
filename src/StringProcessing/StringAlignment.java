package StringProcessing;

import java.io.*;
import java.util.*;

public class StringAlignment {
	public static void main(String[] args) {
		String a = "ACAATCC";
		String b = "AGCATGC";
		A = a.toCharArray();
		B = b.toCharArray();
		V = new int[a.length()][b.length()];
		System.out.println(AlignIterative(A, B));
		print2D(result);
	}

	static int[][] V;
	static char[] A;
	static char[] B;
	static int[][] result;
	public static int score(char a, char b) {
		if (a == b)
			return 2;
		else
			return -1;
	}

	public static int AlignRecursive(int i, int j) {
		if (i == 0 && j == 0)
			return 0;
		if (j == 0)
			return i * score(A[i], ' ');
		if (i == 0)
			return j * score(' ', B[j]);
		// if(V[i][j]!=-1)
		int Option1 = AlignRecursive(i - 1, j - 1) + score(A[i], B[j]);
		int Option2 = AlignRecursive(i - 1, j) + score(A[i], ' ');
		int Option3 = AlignRecursive(i, j - 1) + score(' ', B[j]);

		return V[i][j] = Math.max(Math.max(Option1, Option2), Option3);

	}

	public static int AlignIterative(char[] A, char[] B) {

		 result = new int[A.length + 1][B.length + 1];
		int saved = 0;
		for (int i = 0; i < result[0].length; i++) {
			result[0][i] = saved; // A and B same length here
			result[i][0] = saved;

			saved--;
		}
		int res = Integer.MIN_VALUE;
		for (int i = 1; i < result.length; i++) {
		
		
				for(int j = 1 ; j<result[i].length;j++){
					
					result[i][j]=score(A[i-1],B[j-1])+Math.max(Math.max(result[i-1][j-1], result[i-1][j]),result[i][j-1]);
					res=Math.max(result[i][j],res);
				}
		}
		return res;
	}
	static void print2D(int [][]n){
		System.out.println();
		for(int i = 0 ; i<n.length;i++){
		
			for(int j= 0 ; j<n[i].length;j++){
				System.out.print(n[i][j]+" ");
			}
			if(!(i==n.length-1))
			System.out.print("\n");
		}
	}
}
