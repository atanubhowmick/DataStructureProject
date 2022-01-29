package dev.atanu.ds.java.array.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class ArraySolution {

	public static void main(String[] args) {
		ArraySolution solution = new ArraySolution();
		int[] arr = solution.rearrangeArray(
				new int[] { 28, -41, 22, -8, -37, 46, 35, -9, 18, -6, 19, -26, -37, -10, -9, 15, 14, 31 });
		for(int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * {@link https://leetcode.com/problems/next-greater-element-ii/}
	 * 
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length, next[] = new int[n];
		Arrays.fill(next, -1);
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n * 2; i++) {
			int num = nums[i % n];
			while (!stack.isEmpty() && nums[stack.peek()] < num) {
				next[stack.pop()] = num;
			}
			if (i < n) {
				stack.push(i);
			}
		}
		return next;
	}

	/**
	 * https://leetcode.com/problems/daily-temperatures/
	 * 
	 * @param temperatures
	 * @return
	 */
	public int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
				int prevIndex = stack.pop();
				result[prevIndex] = i - prevIndex;
			}
			stack.push(i);
		}
		return result;
	}

	/**
	 * https://leetcode.com/problems/queue-reconstruction-by-height/
	 * 
	 * @param people
	 * @return
	 */
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
		List<int[]> list = new LinkedList<>();
		for (int[] p : people) {
			list.add(p[1], p);
		}
		return list.toArray(new int[list.size()][]);
	}

	/**
	 * https://leetcode.com/problems/majority-element-ii/description/
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> majorityElement(int[] nums) {
		int n = nums.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}

		List<Integer> list = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > n / 3) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	/**
	 * https://leetcode.com/problems/stone-game/
	 * 
	 * @param piles
	 * @return
	 */
	public boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][][] memo = new int[n + 1][n + 1][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		int diff = stoneGameHelper(0, n - 1, piles, memo, 1);
		return diff >= 0;
	}

	private int stoneGameHelper(int left, int right, int[] piles, int[][][] memo, int id) {
		if (left < right) {
			return 0;
		}
		if (memo[left][right][id] != -1) {
			return memo[left][right][id];
		}
		int next = id == 1 ? 0 : 1;
		if (id == 1) {
			memo[left][right][id] = Math.max((stoneGameHelper(left + 1, right, piles, memo, next) + piles[left]),
					(stoneGameHelper(left, right - 1, piles, memo, next) + piles[right]));
		} else {
			memo[left][right][id] = Math.max((stoneGameHelper(left + 1, right, piles, memo, next) - piles[left]),
					(stoneGameHelper(left, right - 1, piles, memo, next) - piles[right]));
		}
		return memo[left][right][id];
	}

	/**
	 * https://leetcode.com/problems/find-the-middle-index-in-array/
	 * 
	 * @param nums
	 * @return
	 */
	public int findMiddleIndex(int[] nums) {
		int leftSum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; leftSum += nums[i++]) {
			map.putIfAbsent(leftSum * 2 + nums[i], i);
		}
		return map.getOrDefault(leftSum, -1);
	}

	/**
	 * https://leetcode.com/problems/kth-largest-element-in-an-array/
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length < k) {
			return -1;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int num : nums) {
			queue.add(num);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.poll();
	}

	/**
	 * https://leetcode.com/problems/kth-largest-element-in-an-array/
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthSmallest(int[] nums, int k) {
		if (nums == null || nums.length < k) {
			return -1;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>((a1, a2) -> a1 > a2 ? -1 : (a1 < a2 ? 1 : -1));
		for (int num : nums) {
			queue.add(num);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.poll();
	}

	/**
	 * https://leetcode.com/problems/kth-largest-element-in-an-array/
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargestWithQuickSelect(int[] nums, int k) {
		int start = 0;
		int end = nums.length - 1;
		int index = nums.length - k;
		while (start < end) {
			int pivot = partion(nums, start, end);
			if (pivot < index)
				start = pivot + 1;
			else if (pivot > index)
				end = pivot - 1;
			else
				return nums[pivot];
		}
		return nums[start];
	}

	private int partion(int[] nums, int start, int end) {
		int pivot = start;
		int temp;
		while (start <= end) {
			while (start <= end && nums[start] <= nums[pivot]) {
				start++;
			}
			while (start <= end && nums[end] > nums[pivot]) {
				end--;
			}
			if (start > end)
				break;
			temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
		}
		temp = nums[end];
		nums[end] = nums[pivot];
		nums[pivot] = temp;
		return end;
	}

	/**
	 * https://leetcode.com/problems/sort-the-matrix-diagonally
	 * 
	 * @param mat
	 * @return
	 */
	public int[][] diagonalSort(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				map.putIfAbsent(r - c, new PriorityQueue<>());
				map.get(r - c).add(mat[r][c]);
			}
		}
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				mat[r][c] = map.get(r - c).poll();
			}
		}
		return mat;
	}

	/**
	 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicatesWithMap(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				int count = map.get(nums[i]);
				map.put(nums[i], ++count);
			} else {
				map.put(nums[i], 1);
			}
		}
		return map.entrySet().stream().filter(e -> e.getValue() == 2).map(e -> e.getKey()).collect(Collectors.toList());
	}

	/**
	 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] < 0)
				res.add(Math.abs(index + 1));
			nums[index] = -nums[index];
		}
		return res;
	}

	/**
	 * https://leetcode.com/problems/subsets/
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrackSubsets(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrackSubsets(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrackSubsets(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	/**
	 * https://leetcode.com/problems/subsets-ii/
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> list = new ArrayList<>();
		backtrackingSubsetsWithDup(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrackingSubsetsWithDup(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1]) {
				continue;
			}
			tempList.add(nums[i]);
			backtrackingSubsetsWithDup(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	/**
	 * https://leetcode.com/problems/permutations/
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		permutationBackTracking(list, new ArrayList<>(), nums);
		return list;
	}

	private void permutationBackTracking(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
		if (nums.length == tempList.size()) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tempList.contains(nums[i])) {
					continue;
				}
				tempList.add(nums[i]);
				permutationBackTracking(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	/**
	 * https://leetcode.com/problems/permutations-ii/
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backTrackPermuteUnique(list, new ArrayList<>(), nums, new boolean[nums.length]);
		return list;
	}

	private void backTrackPermuteUnique(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
		if (nums.length == tempList.size()) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
					continue;
				}
				tempList.add(nums[i]);
				used[i] = true;

				backTrackPermuteUnique(list, tempList, nums, used);
				tempList.remove(tempList.size() - 1);
				used[i] = false;
			}
		}
	}

	/**
	 * https://leetcode.com/problems/combination-sum/
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		backtrackCombinationSum(list, new ArrayList<>(), candidates, target, 0);
		return list;
	}

	private void backtrackCombinationSum(List<List<Integer>> list, List<Integer> tempList, int[] candidates, int target,
			int start) {
		if (target < 0) {
			// return;
		} else if (target == 0) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < candidates.length; i++) {
				tempList.add(candidates[i]);
				backtrackCombinationSum(list, tempList, candidates, target - candidates[i], i);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	/**
	 * https://leetcode.com/problems/combination-sum-ii/
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(candidates);
		backtrackCombinationSum2(list, new ArrayList<>(), candidates, target, 0);
		return list;
	}

	private void backtrackCombinationSum2(List<List<Integer>> list, List<Integer> tempList, int[] candidates,
			int target, int start) {
		if (target < 0) {
			// return;
		} else if (target == 0) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < candidates.length; i++) {
				if (i > start && candidates[i] == candidates[i - 1]) {
					continue;
				}
				tempList.add(candidates[i]);
				backtrackCombinationSum2(list, tempList, candidates, target - candidates[i], i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	/**
	 * https://leetcode.com/problems/unique-email-addresses/
	 * 
	 * @param emails
	 * @return
	 */
	public int numUniqueEmails(String[] emails) {
		Set<String> set = new HashSet<>();
		for (String email : emails) {
			String[] arr = email.split("@");
			if (arr.length == 2) {
				String updatedEmail = arr[0].replaceAll("\\.", "");
				String[] finalEMailArr = updatedEmail.split("\\+");
				String finalEMail = finalEMailArr[0] + "@" + arr[1];
				set.add(finalEMail);
			}
		}
		return set.size();
	}

	/**
	 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
	 * 
	 * @param s
	 * @return string
	 */
	public String reverseWords(String s) {
		char[] arr = s.toCharArray();
		int i = 0;
		while (i < arr.length) {
			if (arr[i] != ' ') {
				int j = i;
				while (j < arr.length && arr[j] != ' ') {
					j++;
				}
				reverse(arr, i, j - 1);
				i = j;
			} else {
				i++;
			}
		}
		return new String(arr);
	}

	private void reverse(char[] arr, int start, int end) {
		char temp;
		while (start < end) {
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * https://leetcode.com/problems/reverse-string-ii/
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public String reverseStr(String s, int k) {
		char[] arr = s.toCharArray();
		int i = 0;
		while (i < arr.length) {
			if (i % (2 * k) == 0) {
				int j = i;
				int count = 0;
				while (count < k) {
					j++;
					count++;
				}
				reverse(arr, i, j - 1);
				i = j;
			} else {
				i++;
			}
		}
		return new String(arr);
	}

	/**
	 * https://leetcode.com/problems/di-string-match/
	 * 
	 * @param s
	 * @return
	 */
	public int[] diStringMatch(String s) {
		int n = s.length();
		int[] arr = new int[n + 1];
		int start = 0, end = n;

		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'I') {
				arr[i] = start;
				start++;
			} else {
				arr[i] = end;
				end--;
			}
		}
		arr[n] = start;
		return arr;
	}

	/**
	 * https://leetcode.com/problems/rearrange-array-elements-by-sign/
	 * 
	 * @param nums
	 * @return
	 */
	public int[] rearrangeArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (i % 2 == 0) {
				if (nums[i] < 0) {
					int j = i + 1;
					while (j < nums.length && nums[j] < 0) {
						j++;
					}
					swap(nums, i, j);
				}
			} else {
				if (nums[i] > 0) {
					int j = i + 1;
					while (j < nums.length && nums[j] > 0) {
						j++;
					}
					swap(nums, i, j);
				}
			}
		}
		return nums;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
