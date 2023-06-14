package dev.atanu.ds.java.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MathSolution {

	public static void main(String[] args) {
		MathSolution solution = new MathSolution();
		int[] nums = new int[] { 5, 1, 6 };
		System.out.println(solution.subsetXORSum(nums));
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
				set.add(i * i);
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

	/**
	 * https://leetcode.com/problems/add-binary/
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int carry = 0;

		int i = a.length() - 1;
		int j = b.length() - 1;

		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (i >= 0) {
				sum += (a.charAt(i--) - '0');
			}
			if (j >= 0) {
				sum += (b.charAt(j--) - '0');
			}
			sb.append(sum % 2);
			carry = sum / 2;
		}

		if (carry != 0) {
			sb.append(carry);
		}

		return sb.reverse().toString();
	}

	/**
	 * https://leetcode.com/problems/number-of-good-pairs/
	 * 
	 * @param nums
	 * @return
	 */
	public int numIdenticalPairs(int[] nums) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int prevCount = map.getOrDefault(nums[i], 0);
			count += prevCount;
			map.put(nums[i], prevCount + 1);
		}

		return count;
	}

	/**
	 * https://leetcode.com/problems/smallest-even-multiple/
	 * 
	 * @param n
	 * @return
	 */
	public int smallestEvenMultiple(int n) {
		return n << (n & 1);
	}

	/**
	 * https://leetcode.com/problems/sum-of-all-subset-xor-totals/ Subset xor sum
	 * 
	 * @param nums
	 * @return
	 */
	public int subsetXORSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		int sum = 0;
		subsetXORSum(list, nums, new ArrayList<>(), 0);
		for (List<Integer> combinations : list) {
			int init = 0;
			for (int comb : combinations) {
				init ^= comb;
			}
			sum += init;
		}
		return sum;
	}

	private void subsetXORSum(List<List<Integer>> list, int[] nums, List<Integer> tempList, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			subsetXORSum(list, nums, tempList, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
