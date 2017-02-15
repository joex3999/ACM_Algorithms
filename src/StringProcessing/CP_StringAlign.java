package StringProcessing;

/**
 * aka . needleman Wunsnch algorithm To know the best score to conver the first
 * string to the second string or vice vers ( hya hya )
 */
public class CP_StringAlign {
	public static void main(String[] args) {
		char[] A = "aaa".toCharArray(), B = "aabaaaa".toCharArray();
		int[][] table = new int[20][20]; // Needleman Wunsnch's algorithm
		int i, j, n = A.length, m = B.length;
		int[][] parenti = new int[20][20]; // assumption that it is of constant
											// length of 20
		int[][] parentj = new int[20][20];
		// insert/delete = -1 point
		for (i = 1; i <= n; i++)
			table[i][0] = i * -1;
		for (j = 1; j <= m; j++)
			table[0][j] = j * -1;

		for (i = 1; i <= n; i++)
			for (j = 1; j <= m; j++) {
				// match = 2 points, mismatch = -1 point
				table[i][j] = table[i - 1][j - 1]
						+ (A[i - 1] == B[j - 1] ? 2 : -1); // cost for match or
															// mismatches
				// insert/delete = -1 point
				table[i][j] = Math.max(table[i][j], table[i - 1][j] - 1); // delete
				table[i][j] = Math.max(table[i][j], table[i][j - 1] - 1); // insert
				if ((table[i][j] == table[i - 1][j - 1]
						+ (A[i - 1] == B[j - 1] ? 2 : -1))) {

					parenti[i][j] = i - 1;
					parentj[i][j] = j - 1;
				} else if (table[i][j] == table[i - 1][j] - 1) {
					parenti[i][j] = i - 1;
					parentj[i][j] = j;
				} else if (table[i][j] == Math.max(table[i][j],
						table[i][j - 1] - 1)) {
					parenti[i][j] = i;
					parentj[i][j] = j - 1;
				}
			}

		System.out.printf("DP table:\n");
		for (i = 0; i <= n; i++) {
			for (j = 0; j <= m; j++)
				System.out.printf("%3d", table[i][j]);
			System.out.printf("\n");
		}
		System.out.printf("Maximum Alignment Score: %d\n", table[n][m]);
		int iI = n;
		int jI = m;
		while (true) {
			int value = table[parenti[iI][jI]][parentj[iI][jI]];
			System.out.println(value);
			if (iI==0&&jI==0)
				break;
			int temp1 = parenti[iI][jI];
			int temp2 = parentj[iI][jI];
			iI = temp1;
			jI = temp2;

		}
	}
}
