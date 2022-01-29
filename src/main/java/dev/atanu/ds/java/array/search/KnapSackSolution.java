package dev.atanu.ds.java.array.search;

/**
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * 
 * @author Atanu Bhowmick
 *
 */
public class KnapSackSolution {

	public static void main(String[] args) {
		KnapSackSolution solution = new KnapSackSolution();
		int[] wt = new int[] { 3, 4, 6, 5 };
		int[] val = new int[] { 2, 3, 1, 4 };
		int maxWt = 8, n = 4;

		int maxValItr = solution.knapSackRecursion(maxWt, wt, val, n);
		System.out.println(maxValItr);

		int maxValMem = solution.knapSackMem(maxWt, wt, val, n);
		System.out.println(maxValMem);

		int maxVal = solution.knapSackBottomUp(maxWt, wt, val, n);
		System.out.println(maxVal);

		int maxFinal = solution.knapSackFinal(maxWt, wt, val, n);
		System.out.println(maxFinal);
	}

	/**
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackRecursion(int maxWt, int[] wt, int[] val, int n) {
		if (n == 0 || maxWt == 0)
			return 0;
		if (wt[n - 1] > maxWt)
			return knapSackRecursion(maxWt, wt, val, n - 1);
		else
			return Math.max((val[n - 1] + knapSackRecursion(maxWt - wt[n - 1], wt, val, n - 1)),
					knapSackRecursion(maxWt, wt, val, n - 1));
	}

	/**
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackMem(int maxWt, int[] wt, int[] val, int n) {
		int dp[][] = new int[n + 1][maxWt + 1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < maxWt + 1; j++) {
				dp[i][j] = -1;
			}
		}
		return knapSackRec(maxWt, wt, val, n, dp);
	}

	private int knapSackRec(int maxWt, int[] wt, int[] val, int n, int[][] dp) {
		if (n == 0 || maxWt == 0)
			return 0;
		if (dp[n][maxWt] != -1)
			return dp[n][maxWt];
		if (wt[n - 1] > maxWt)
			dp[n][maxWt] = knapSackRec(maxWt, wt, val, n - 1, dp);
		else
			dp[n][maxWt] = Math.max((val[n - 1] + knapSackRec(maxWt - wt[n - 1], wt, val, n - 1, dp)),
					knapSackRec(maxWt, wt, val, n - 1, dp));
		return dp[n][maxWt];
	}

	/**
	 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackBottomUp(int maxWt, int[] wt, int[] val, int n) {
		int[][] dp = new int[n + 1][maxWt + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= maxWt; j++) {
				if (i == 0 || j == 0)
					dp[i][j] = 0;
				else if (wt[i - 1] <= j)
					dp[i][j] = Math.max((val[i - 1] + dp[i - 1][j - wt[i - 1]]), dp[i - 1][j]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		return dp[n][maxWt];
	}

	/**
	 * 
	 * @param maxWt
	 * @param wt
	 * @param val
	 * @param n
	 * @return
	 */
	public int knapSackFinal(int maxWt, int[] wt, int[] val, int n) {
		int[] dp = new int[maxWt + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = maxWt; j >= 0; j--) {
				if (wt[i - 1] <= j) {
					// Single row and handle the previous row data if we start from end
					dp[j] = Math.max((val[i - 1] + dp[j - wt[i - 1]]), dp[j]);
				}
			}
		}
		return dp[maxWt];
	}

}
