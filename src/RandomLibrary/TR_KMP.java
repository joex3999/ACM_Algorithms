package RandomLibrary;

/**
 * Date 09/22/2014
 * 
 * @author tusroy
 * 
 *         Do pattern matching using KMP algorithm
 * 
 *         Runtime complexity - O(m + n) where m is length of text and n is
 *         length of pattern Space complexity - O(n)
 */
public class TR_KMP {

	/**
	 * Slow method of pattern matching
	 */
	// public boolean hasSubstring(char[] text, char[] pattern){
	// int i=0;
	// int j=0;
	// int k = 0;
	// while(i < text.length && j < pattern.length){
	// if(text[i] == pattern[j]){
	// i++;
	// j++;
	// }else{
	// j=0;
	// k++;
	// i = k;
	// }
	// }
	// if(j == pattern.length){
	// return true;
	// }
	// return false;
	// }

	/**
	 * Compute temporary array to maintain size of suffix which is same as
	 * prefix Time/space complexity is O(size of pattern)
	 */
	private int[] computeTemporaryArray(char pattern[]) {
		int[] lps = new int[pattern.length];
		int index = 0;
		for (int i = 1; i < pattern.length;) {
			if (pattern[i] == pattern[index]) {
				lps[i] = index + 1;
				index++;
				i++;
			} else {
				if (index != 0) {
					index = lps[index - 1]; // not sure 100% about this line
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	/**
	 * KMP algorithm of pattern matching.
	 */
	public int KMPCalc(char[] text, char[] pattern) {

		int lps[] = computeTemporaryArray(pattern);
		int i = 0;
		int j = 0;
		int result = 0;
		while (i < text.length && j < pattern.length) {
			if (text[i] == pattern[j]) {
				i++;
				j++;
				if (j == pattern.length) { // -j;
					result++;
					j = lps[j - 1];
				} // j-
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
		// for checking of existance
		// if(j == pattern.length){
		// return true;
		// }
		// return false;
		return result;
	}

	public boolean KMPCheck(char[] text, char[] pattern) {

		int lps[] = computeTemporaryArray(pattern);
		int i = 0;
		int j = 0;
		int result = 0;
		while (i < text.length && j < pattern.length) {
			if (text[i] == pattern[j]) {
				i++;
				j++;

			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

		if (j == pattern.length) {
			return true;
		}
		return false;

	}

	static int[] countPrefixOcc(int[] pi) {
		int[] ans = new int[pi.length];
		for (int i = 0; i < pi.length; ++i)
			ans[pi[i]]++;

		for (int len = pi.length - 1; len > 0; --len)
			ans[pi[len - 1]] += ans[len];
		for (int i = 0; i < pi.length; ++i)
			ans[i]++;
		return ans;
	}

	static int[] prefixFunction(char[] s) {
			int n = s.length;
			int[] pi = new int[n];
		for (int i = 1, j = 0; i < n; ++i) {
			while (j > 0 && s[i] != s[j])
				j = pi[j - 1];
			if (s[i] == s[j])
				++j;
			pi[i] = j;

		}
		return pi;
	}

	public static void main(String args[]) {

		String str = "aax";
		String subString = "ax";
		TR_KMP ss = new TR_KMP();
		int result = ss.KMPCalc(str.toCharArray(), subString.toCharArray());
		System.out.print(result);

	}
}