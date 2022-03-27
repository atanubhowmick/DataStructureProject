package dev.atanu.ds.java.array.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import dev.atanu.ds.java.linked.list.ListNode;

public class ArraySolution {

	public static void main(String[] args) {
		int[][] arr1 = new int[][] { { 2, 3 }, { 6, 3 }, { 7, 5 }, { 11, 3 }, { 15, 2 }, { 18, 1 } };
		int[] arr2 = new int[] { 2 };
		ArraySolution solution = new ArraySolution();
		double kthSmallest = solution.averageWaitingTime(arr1);
		System.out.println(kthSmallest);
	}

	/**
	 * {@link https://leetcode.com/problems/next-greater-element-ii/}
	 * 
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] next = new int[n];
		Arrays.fill(next, -1);
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n * 2; i++) {
			int num = nums[i % n];
			while (!stack.isEmpty() && nums[stack.peek()] < num) {
				int prevIndex = stack.pop();
				next[prevIndex] = num;
			}
			if (i < n) {
				stack.push(i);
			}
		}
		return next;
	}

	/**
	 * https://leetcode.com/problems/next-greater-node-in-linked-list/
	 * 
	 * @param head
	 * @return
	 */
	public int[] nextLargerNodes(ListNode head) {
		List<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		int[] arr = new int[list.size()];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < list.size(); i++) {
			while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
				int prevIndex = stack.pop();
				arr[prevIndex] = list.get(i);
			}
			stack.push(i);
		}
		return arr;
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
		Stack<Integer> stack = new Stack<>();
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

	/**
	 * https://leetcode.com/problems/kth-largest-element-in-an-array/
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargestQuickSelect(int[] nums, int k) {
		int searchIdx = nums.length - k;
		int start = 0, end = nums.length - 1;
		while (start < end) {
			int pivotIdx = partition(nums, start, end);
			if (pivotIdx == searchIdx) {
				return nums[pivotIdx];
			} else if (pivotIdx > searchIdx) {
				end = pivotIdx - 1;
			} else {
				start = pivotIdx + 1;
			}
		}
		return nums[start];
	}

	private int partition(int[] nums, int left, int right) {
		int pivot = nums[left], low = left, high = right;
		while (left < right) {
			while (left < high && pivot >= nums[left]) {
				left++;
			}
			while (low < right && pivot <= nums[right]) {
				right--;
			}
			if (left < right) {
				swap(nums, left, right);
			}
		}
		swap(nums, low, right);
		return right;
	}

	/**
	 * 
	 * @param nums
	 */
	public void nextPermutation(int[] nums) {
		int pivot = indexOfLastPeak(nums) - 1;
		if (pivot != -1) {
			int nextPrefix = lastIndexOfGreater(nums, nums[pivot]);
			swap(nums, pivot, nextPrefix);
		}
		reverse(nums, pivot + 1, nums.length - 1);
	}

	private int indexOfLastPeak(int[] nums) {
		for (int i = nums.length - 1; 0 < i; --i) {
			if (nums[i - 1] < nums[i])
				return i;
		}
		return 0;
	}

	private int lastIndexOfGreater(int[] nums, int threshold) {
		for (int i = nums.length - 1; 0 <= i; --i) {
			if (threshold < nums[i])
				return i;
		}
		return -1;
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			swap(nums, start++, end--);
		}
	}

	/**
	 * https://leetcode.com/problems/largest-odd-number-in-string/
	 * 
	 * @param num
	 * @return
	 */
	public String largestOddNumber(String num) {
		for (int i = num.length() - 1; i >= 0; i--) {
			if (num.charAt(i) % 2 != 0) {
				System.out.println(num.charAt(i) % 2);
				return num.substring(0, i + 1);
			}
		}
		return "";
	}

	/**
	 * https://leetcode.com/problems/longest-palindrome/
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}

		int start = 0;
		int end = 0;

		for (int i = 0; i < s.length(); i++) {
			int len1 = expandFromMiddle(s, i, i);
			int len2 = expandFromMiddle(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - ((len - 1) / 2);
				end = i + (len / 2);
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandFromMiddle(String s, int left, int right) {
		if (s == null || left > right) {
			return 0;
		}

		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}

	/**
	 * https://leetcode.com/problems/pancake-sorting/
	 * 
	 * @param arr
	 * @return list
	 */
	public List<Integer> pancakeSort(int[] arr) {
		List<Integer> list = new ArrayList<>();
		for (int i = arr.length - 1; i > 0; i--) {
			if (i + 1 != arr[i]) {
				int maxIndex = findMaxIndex(arr, 0, i);
				reverse(arr, 0, maxIndex);
				if (maxIndex > 0) {
					list.add(maxIndex + 1);
				}
				reverse(arr, 0, i);
				list.add(i + 1);

			}
		}
		return list;
	}

	private int findMaxIndex(int[] arr, int start, int end) {
		int maxIndex = start;
		for (int i = start; i <= end; i++) {
			if (arr[maxIndex] < arr[i]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	/**
	 * https://leetcode.com/problems/max-area-of-island/
	 * 
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					maxArea = Math.max(maxArea, areaOfIsland(grid, i, j));
				}
			}
		}
		return maxArea;
	}

	private int areaOfIsland(int[][] grid, int i, int j) {
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
			grid[i][j] = 0;
			return 1 + areaOfIsland(grid, i + 1, j) + areaOfIsland(grid, i - 1, j) + areaOfIsland(grid, i, j - 1)
					+ areaOfIsland(grid, i, j + 1);
		}
		return 0;
	}

	/**
	 * https://leetcode.com/problems/trapping-rain-water/
	 * 
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
		int left = 0, right = height.length - 1;
		int maxLeft = 0, maxRight = 0;
		int totalWater = 0;

		while (left < right) {
			if (height[left] < height[right]) {
				if (height[left] >= maxLeft) {
					maxLeft = height[left];
				} else {
					totalWater += maxLeft - height[left];
				}
				left++;
			} else {
				if (height[right] >= maxRight) {
					maxRight = height[right];
				} else {
					totalWater += maxRight - height[right];
				}
				right--;
			}
		}
		return totalWater;
	}

	/**
	 * https://leetcode.com/problems/trapping-rain-water-ii/
	 * 
	 * @param heightMap
	 * @return
	 */
	public int trapRainWater(int[][] heightMap) {
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];

		PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.height - b.height);

		for (int i = 0; i < m; i++) {
			visited[i][0] = true;
			visited[i][n - 1] = true;
			queue.offer(new Cell(i, 0, heightMap[i][0]));
			queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
		}

		for (int i = 0; i < n; i++) {
			visited[0][i] = true;
			visited[m - 1][i] = true;
			queue.offer(new Cell(0, i, heightMap[0][i]));
			queue.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
		}

		int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int res = 0;
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			for (int[] dir : dirs) {
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
					visited[row][col] = true;
					res += Math.max(0, cell.height - heightMap[row][col]);
					queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
				}
			}
		}

		return res;
	}

	private class Cell {
		int row;
		int col;
		int height;

		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}

	/**
	 * https://leetcode.com/problems/median-of-two-sorted-arrays/
	 * https://www.youtube.com/watch?v=LPFhl65R7ww
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		if (nums1.length > nums2.length) {
			return findMedianSortedArrays(nums2, nums1);
		}

		int x = nums1.length;
		int y = nums2.length;

		int low = 0;
		int high = x;

		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = (x + y + 1) / 2 - partitionX;

			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
			int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
			int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				if ((x + y) % 2 == 0) {
					return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (double) Math.max(maxLeftX, maxLeftY);
				}
			} else if (maxLeftX > minRightY) {
				high = partitionX - 1;
			} else {
				low = partitionX + 1;
			}
		}
		return -1d;
	}

	/**
	 * https://leetcode.com/problems/jump-game/
	 * 
	 * @param nums
	 * @return
	 */
	public boolean canJump(int[] nums) {
		int reachableIdx = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > reachableIdx) {
				return false;
			}
			// Greedy algorithm. Take the max jump from the position.
			reachableIdx = Math.max(reachableIdx, i + nums[i]);
		}
		return true;
	}

	/**
	 * https://leetcode.com/problems/jump-game-ii/
	 * 
	 * @param nums
	 * @return
	 */
	public int jump(int[] nums) {
		int jumps = 0, curEnd = 0, reachableIdx = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			reachableIdx = Math.max(reachableIdx, i + nums[i]);
			if (i == curEnd) {
				jumps++;
				curEnd = reachableIdx;
			}
		}
		return jumps;
	}

	/**
	 * https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> findLonely(int[] nums) {
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1 && !map.containsKey(entry.getKey() - 1) && !map.containsKey(entry.getKey() + 1)) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	/**
	 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallestWithBinarySearch(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length; // For general, the matrix need not be a square

		int left = matrix[0][0];
		int right = matrix[m - 1][n - 1];

		int ans = -1;

		while (left <= right) {
			int mid = (left + right) >> 1;
			if (countLessOrEqual(matrix, mid) >= k) {
				ans = mid;
				right = mid - 1; // try to looking for a smaller value in the left side
			} else {
				left = mid + 1; // try to looking for a bigger value in the right side
			}
		}
		return ans;
	}

	private int countLessOrEqual(int[][] matrix, int x) {
		int m = matrix.length;
		int n = matrix[0].length;
		int count = 0;
		int col = n - 1; // start with the rightmost column
		for (int row = 0; row < m; ++row) {
			while (col >= 0 && matrix[row][col] > x) {
				--col; // decrease column until matrix[r][c] <= x
			}
			count += (col + 1);
		}
		return count;
	}
	
	/**
	 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length; 
        int n = matrix[0].length;
        
        int ans = -1;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        
        for (int row = 0; row < Math.min(m, k); ++row) {
        	// Take the values from first column initially 
            minHeap.offer(new int[]{matrix[row][0], row, 0});
        }

        for (int i = 1; i <= k; ++i) {
            int[] top = minHeap.poll();
            int row = top[1];
            int col = top[2];
            ans = top[0];
            if (col + 1 < n) {
            	// Add values through the row in queue.
            	minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }
        }
        return ans;
    }
	
	/**
	 * https://leetcode.com/problems/average-waiting-time/
	 * 
	 * @param customers
	 * @return
	 */
	public double averageWaitingTime(int[][] customers) {
		long waitingTime = 0;
        long currentTime = customers[0][0];
        for(int i = 0; i < customers.length; i++) {
            if(currentTime > customers[i][0]) {
                waitingTime += currentTime - customers[i][0];  
            } else {
                currentTime = customers[i][0];
            }
            currentTime += customers[i][1];
            waitingTime += customers[i][1];
        }
        
        return (double) waitingTime / customers.length;
    }
}
