package DP;

import java.util.Scanner;

public class Max2DRangeSum {
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int [][]a=new int[n][n];

		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				a[i][j]=sc.nextInt();
				if(i>0)
					a[i][j]+=a[i-1][j];
				if(j>0)
					a[i][j]+=a[i][j-1];
				if(i>0 && j>0)
					a[i][j]-=a[i-1][j-1];
							
			}
		int max=-127*100*100;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				for(int r=i;r<n;r++)
					for(int c=j;c<n;c++)  // (i,j) is the start point & (r,c) is the end point
					{
						int curr=a[r][c];
						if(i>0)
							curr-=a[i-1][c];
						if(j>0)
							curr-=a[r][j-1];
						if(i>0 && j>0)
							curr+=a[i-1][j-1];
						max=Math.max(max, curr);
					}
		System.out.println(max);
		
	}


}
