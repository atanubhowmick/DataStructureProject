package dev.atanu.ds.java.math;

import java.util.HashSet;
import java.util.Set;

public class MathSolution {

	public static void main(String[] args) {
		MathSolution solution = new MathSolution();
		System.out.println(solution.judgeSquareSum(2147483647));
	}

	/**
	 * https://leetcode.com/problems/valid-number/
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber(String s) {

		return true;
	}

	/**
	 * https://leetcode.com/problems/sum-of-square-numbers/
	 * 
	 * @param c
	 * @return
	 */
	public boolean judgeSquareSum(int c) {
		Set<Integer> set = new HashSet<>();
		int sqrt = findSqrt(c);
		for (int i = 0; i <= sqrt; i++) {
			if (set.contains(c - (i * i))) {
				return true;
			} else {
				set.add(i*i);
			}
		}
		return false;
	}

	private int findSqrt(int num) {
		int sqrt = 0, i = 1;
		long sum = 0;
		while (sum < num) {
			sum += i;
			i += 2;
			sqrt += 1;
		}
		return sqrt;
	}
}
