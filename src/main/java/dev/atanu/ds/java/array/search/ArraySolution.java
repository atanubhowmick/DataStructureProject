package dev.atanu.ds.java.array.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ArraySolution {

	public static void main(String[] args) {
		ArraySolution solution = new ArraySolution();
		int[] nums = new int[] {3, 2, 3};
		List<Integer> list = solution.majorityElement(nums);
		System.out.println(list);
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
        for(int num: nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > n/3) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

}
