/**
 * 
 */
package dev.atanu.ds.java.dynamic.programming;

/**
 * @author ATANU
 *
 */
public class DpSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DpSolution solution = new DpSolution();
		System.out.println(solution.longestPalindrome("batanu"));
	}

	/**
	 * {@link https://leetcode.com/problems/longest-palindromic-substring/}
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return "";
		int n = s.length();
		// substring(i,j) is panlidrome
		int start = 0, end = 0;
		boolean[][] dp = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i; j >= 0; j--) {
				if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) {
					dp[j][i] = true;
					if (i - j + 1 > end - start) {
						start = j;
						end = i;
					}
				}
			}
		}
		return n == 0 ? "" : s.substring(start, end + 1);
	}

}
