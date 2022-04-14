package dev.atanu.ds.java.math;

public class BitManupulationSolution {

	public static void main(String[] args) {
		BitManupulationSolution solution = new BitManupulationSolution();
		System.out.println(solution.singleNumber(new int[] { 1, 2, 1, 2, 4, 4, 7, 8 }));
	}

	/**
	 * https://leetcode.com/problems/single-number-iii/
	 * https://algorithms.tutorialhorizon.com/find-the-right-most-set-bit-of-a-number/
	 * 
	 * @param nums
	 * @return
	 */
	public int[] singleNumber(int[] nums) {
		// Pass 1 :
		// Get the XOR of the two numbers we need to find
		int diff = 0;
		for (int num : nums) {
			diff ^= num;
		}
		// Get its last set bit
		diff &= -diff;

		// Pass 2 :
		int[] rets = { 0, 0 }; // this array stores the two numbers we will return
		for (int num : nums) {
			if ((num & diff) == 0) // the bit is not set
			{
				rets[0] ^= num;
			} else // the bit is set
			{
				rets[1] ^= num;
			}
		}
		return rets;
	}

}
