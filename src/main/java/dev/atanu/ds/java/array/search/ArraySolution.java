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
		int[] arr1 = new int[] { 1, 1, 1 };
		int[][] arr2 = new int[][] { { 0, 0, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 0 } };
		ArraySolution solution = new ArraySolution();
		System.out.println(solution.skyline(arr1));
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
	 * https://leetcode.com/problems/container-with-most-water/
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int maxArea = 0;

		while (left < right) {
			int area = (right - left) * Math.min(height[left], height[right]);
			maxArea = Math.max(maxArea, area);

			if (height[left] > height[right]) {
				right -= 1;
			} else {
				left += 1;
			}
		}
		return maxArea;
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
	 * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
	 * 
	 * @param nums
	 * @return
	 */
	public boolean canBeIncreasing(int[] nums) {
		int previous = nums[0];
		boolean used = false;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] <= previous) {
				if (used) {
					return false;
				}
				used = true;
				if (i == 1 || nums[i] > nums[i - 2]) {
					previous = nums[i];
				}
			} else {
				previous = nums[i];
			}
		}
		return true;
	}

	/**
	 * https://leetcode.com/problems/group-anagrams/
	 * 
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] ca = new char[26];
			for (char c : s.toCharArray())
				ca[c - 'a']++;
			String keyStr = String.valueOf(ca);
			if (!map.containsKey(keyStr))
				map.put(keyStr, new ArrayList<>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<>(map.values());
	}

	/**
	 * https://leetcode.com/problems/3sum-with-multiplicity/
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public int threeSumMulti(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		int res = 0;
		int mod = 1000000007;
		for (int i = 0; i < arr.length; i++) {
			res = (res + map.getOrDefault(target - arr[i], 0)) % mod;

			for (int j = 0; j < i; j++) {
				int temp = arr[i] + arr[j];
				map.put(temp, map.getOrDefault(temp, 0) + 1);
			}
		}
		return res;
	}

	/**
	 * https://leetcode.com/problems/search-suggestions-system/
	 * 
	 * @param products
	 * @param searchWord
	 * @return
	 */
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> result = new ArrayList<>();
		for (int i = 1; i <= searchWord.length(); i++) {
			List<String> list = new ArrayList<>();
			String str = searchWord.substring(0, i);
			for (String product : products) {
				if (product.startsWith(str)) {
					list.add(product);
				}
			}
			result.add(list);
		}
		return result;
	}

	/**
	 * https://leetcode.com/problems/find-k-closest-elements/
	 * 
	 * @param arr
	 * @param k
	 * @param x
	 * @return
	 */
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int left = 0, right = arr.length - k;
		while (left < right) {
			int mid = (left + right) / 2;
			if (x - arr[mid] > arr[mid + k] - x)
				left = mid + 1;
			else
				right = mid;
		}
		return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
	}

	/**
	 * https://leetcode.com/problems/rotate-array/
	 * 
	 * @param nums
	 * @param k
	 */
	public void rotate(int[] nums, int k) {
		k = k % nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	/**
	 * https://leetcode.com/problems/find-the-duplicate-number/
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate(int[] nums) {
		int fast = 0, slow = 0;

		fast = nums[nums[fast]];
		slow = nums[slow];

		// First while loop to get the meeting point inside loop
		while (nums[fast] != nums[slow]) {
			fast = nums[nums[fast]];
			slow = nums[slow];
		}

		fast = 0;

		// Second while loop to get the initial point of the loop
		while (nums[fast] != nums[slow]) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return nums[fast];
	}

	/**
	 * https://leetcode.com/problems/count-primes/
	 * 
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {
		if (n <= 2) {
			return 0;
		}

		List<Integer> primeList = new ArrayList<>();
		primeList.add(2);

		for (int i = 3; i < n; i += 2) {
			int sqrt = (int) Math.sqrt(i);
			boolean isPrime = true;
			for (int prime : primeList) {
				if (prime > sqrt) {
					break;
				}
				if (i % prime == 0) {
					isPrime = false;
				}
			}
			if (isPrime) {
				primeList.add(i);
			}
		}

		return primeList.size();
	}

	private int skyline(int[] arr) {
		int result = 0;
		int prev = 0;

		for (int element : arr) {
			if (element > prev) {
				result += (element - prev);
			}
			prev = element;

			if (result > 1000000000 || result < 0) {
				return -1;
			}
		}

		return result;
	}

	private int attendTraining(String[] employees) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < employees.length; i++) {
			for (char day : employees[i].toCharArray()) {
				int dayInt = day - '0';
				if(!map.containsKey(dayInt)) {
					map.put(dayInt, new ArrayList<>());
				}
				map.get(dayInt).add(i);
			}
		}
		return 0;
	}
}
