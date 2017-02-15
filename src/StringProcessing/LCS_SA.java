package StringProcessing;
// not functioning properly . Problem in the track of the result 
public class LCS_SA {
	static int [][]table;
public static void main(String[] args) {


System.out.println(LCS("abcd","dacb"));
  }	
public static String LCS(String a , String b){
	char[] A = a.toCharArray(), B = b.toCharArray();
	table = new int[105][105]; // Needleman Wunsnch's algorithm // this number isChanging
    int i, j, n = A.length, m = B.length;
    boolean [] Abool = new boolean[A.length];
    boolean []Bbool = new boolean[B.length];
    StringBuilder sbb = new StringBuilder();
    // insert/delete = -1 point
    for (i = 1; i <= n; i++)
      table[i][0] = 0;
    for (j = 1; j <= m; j++)
      table[0][j] =  0;

   StringBuilder result = new StringBuilder();
   int saved = 1 ;
    for (i = 1; i <= n; i++)
      for (j = 1; j <= m; j++) {
        // match = 1 points, mismatch = -Infinity point
        int first = table[i][j];
    	  table[i][j] = table[i - 1][j - 1] + (A[i - 1] == B[j - 1] ? 1 : Integer.MIN_VALUE); // cost for match or mismatches
        int second = table[i][j];
        if(A[i-1]==B[j-1]){
        	System.out.println(A[i-1]);
        }
        if(second>first&&!Abool[i-1]&&!Bbool[j-1]){
        	result.append(A[i-1]);
Abool[i-1] = true ;
Bbool[j-1] = true ;
        }
   
    	  // insert/delete = -1 point
        table[i][j] = Math.max(table[i][j], table[i - 1][j] ); // delete
        table[i][j] = Math.max(table[i][j], table[i][j - 1] ); // insert
        saved = second;
      }

    System.out.printf("DP table:\n");
    for (i = 0; i <= n; i++) {
      for (j = 0; j <= m; j++)
        System.out.printf("%3d", table[i][j]);
      System.out.printf("\n");
    }
    System.out.printf("Maximum Alignment Score: %d\n", table[n][m]);
    return result.toString();
}
}