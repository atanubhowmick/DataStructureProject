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
		System.out.println(solution.longestPalindrome("battan"));
		System.out.println(solution.maxSumAfterPartitioning(new int[] {1,15,7,9,2,5,10}, 3));
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
					if (i - j + 1 > end - start + 1) {
						start = j;
						end = i;
					}
				}
			}
		}
		return s.substring(start, end + 1);
	}

	/**
	 * {@link https://leetcode.com/problems/partition-array-for-maximum-sum/}
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int maxSumAfterPartitioning(int[] arr, int k) {
		int n = arr.length;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			int curMax = 0;
			for (int j = 1; j <= k && i - j + 1 >= 0; j++) {
				curMax = Math.max(curMax, arr[i - j + 1]);
				dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + curMax * j);
			}
		}
		return dp[n - 1];
	}

}
