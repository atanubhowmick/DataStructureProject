/**
 * 
 */
package dev.atanu.ds.java.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Atanu Bhowmick
 *
 */
public class PermutationCombination {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 6, 4 };
		PermutationCombination pc = new PermutationCombination();
		pc.nextPermutation(arr);
		System.out.println(pc.getMinSwaps(arr, 2));
	}
	
	/**
	 * Provide all permutations. For input string 'ABC' it returns
	 * [AAA, AAB, AAC, ABA, ABB, ABC, ACA, ACB, ACC, BAA, BAB, BAC, BBA, BBB, BBC, BCA, BCB, BCC, CAA, CAB, CAC, CBA, CBB, CBC, CCA, CCB, CCC]
	 * 
	 * @param str
	 */
	public List<String> getAllPermutations(String str) {
		List<String> list = new ArrayList<>();
		getAllPermutations(list, str, new StringBuilder());
		return list;
	}

	private void getAllPermutations(List<String> list, String str, StringBuilder sb) {
		if (sb.length() == str.length()) {
			list.add(sb.toString());
		} else {
			for (int i = 0; i < str.length(); i++) {
				sb.append(str.charAt(i));
				getAllPermutations(list, str, sb);
				sb.setLength(sb.length() - 1);
			}
		}
	}
	
	
	/**
	 * https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
	 * <br>
	 * 
	 * Get all combinations. For input string 'ABC', it returns
	 * [ABC, ACB, BAC, BCA, CBA, CAB]
	 * 
	 * @param str - should not contains any duplicate character
	 */
	public List<String> getValidPermutations(String str) {
		List<String> list = new ArrayList<>();
		validPermutations(list, str.toCharArray(), 0);
		return list;
	}

	private void validPermutations(List<String> list, char[] arr, int start) {
		if (start == arr.length - 1) {
			list.add(new String(arr));
		} else {
			for (int i = start; i < arr.length; i++) {
				swap(arr, start, i);
				validPermutations(list, arr, start + 1);
				swap(arr, start, i);
			}
		}
	}
	
	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	/**
	 * Get all valid (non-repetitive) permutations. 
	 * 
	 * For input string 'ABC', it returns
	 * [ABC, ACB, BAC, BCA, CBA, CAB] 
	 * Please see the topmost method - getAllPermutations()
	 * 
	 * <br> 
	 * Same concept as (next method - permute())
	 * https://leetcode.com/problems/permutations/
	 * 
	 * @param str
	 * @return
	 */
	public List<String> getValidPermutations1(String str) {
		List<String> list = new ArrayList<>();
		getValidPermutations1(list, str, new ArrayList<>());
		return list;
	}

	private void getValidPermutations1(List<String> list, String str, List<Character> tempList) {
		if (tempList.size() == str.length()) {
			StringBuilder builder = new StringBuilder();
			for (Character ch : tempList) {
				builder.append(ch);
			}
			list.add(builder.toString());
		} else {
			for (int i = 0; i < str.length(); i++) {
				if(tempList.contains(str.charAt(i))) {
					continue;
				}
				tempList.add(str.charAt(i));
				getValidPermutations1(list, str, tempList);
				tempList.remove(tempList.size() - 1);
			}
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
        //Arrays.sort(nums); Not required
        permutationBackTracking(list, new ArrayList<>(), nums);
        return list;
    }
    
    private void permutationBackTracking(List<List<Integer>> list, List<Integer> tempList,
                                        int[] nums) {
        if(nums.length == tempList.size()) {
            list.add(new ArrayList<>(tempList));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(tempList.contains(nums[i])) {
                    continue;
                }
                tempList.add(nums[i]);
                permutationBackTracking(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    
    
	/**
	 * By following the swap approach. Please check the above method - getValidPermutations() 
	 * https://leetcode.com/problems/permutations/
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute1(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		validPermutations1(list, nums, 0);
		return list;
	}

	private void validPermutations1(List<List<Integer>> list, int[] nums, int start) {
		if (start == nums.length - 1) {
			list.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		} else {
			for (int i = start; i < nums.length; i++) {
				swap(nums, start, i);
				validPermutations1(list, nums, start + 1);
				swap(nums, start, i);
			}
		}
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
    
    /**
	 * https://leetcode.com/problems/permutations-ii/
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permuteWithDuplicate(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums); // Sorting required for duplicates
		validPermutations2(list, nums, 0);
		return list;
	}

	private void validPermutations2(List<List<Integer>> list, int[] nums, int start) {
		if (start == nums.length - 1) {
			// Not an efficient solution. O(n) is added for below line. Check below solution
			list.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
		} else {
			for (int i = start; i < nums.length; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				swap(nums, start, i);
				validPermutations2(list, nums, start + 1);
				swap(nums, start, i);
			}
		}
	}
	
	/**
	 * If input array contains duplicate use a boolean flag array to track if the
	 * element already used. 
	 * 
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

	private void backTrackPermuteUnique(List<List<Integer>> list, List<Integer> tempList, 
						int[] nums, boolean[] used) {
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
	 * Get all combinations. For input string 'ABC' and k = 2, it returns
	 * [AA, AB, AC, BB, BC, CC]
	 * 
	 * @param str - should not contains any duplicate character
	 */
	public List<String> getAllCombinations(String str, int k) {
		List<String> list = new ArrayList<>();
		getAllCombinations(list, str, new StringBuilder(), k, 0);
		return list;
	}

	private void getAllCombinations(List<String> list, String str, StringBuilder sb, int k, int start) {
		if (sb.length() == k) {
			list.add(sb.toString());
		} else {
			for (int i = start; i < str.length(); i++) {
				sb.append(str.charAt(i));
				getAllCombinations(list, str, sb, k, i); // Need to send i for combination
				sb.setLength(sb.length() - 1);
			}
		}
	}
	
	/**
	 * Get all valid combinations without repetition. 
	 * For input string 'ABC' and k = 2, it returns
	 * [AB, AC, BC]
	 * 
	 * @param str
	 * @param k
	 * @return
	 */
	public List<String> getValidCombinations(String str, int k) {
		List<String> list = new ArrayList<>();
		getValidCombinations(list, str, new StringBuilder(), k, 0);
		return list;
	}

	private void getValidCombinations(List<String> list, String str, StringBuilder sb, int k, int start) {
		if (sb.length() == k) {
			list.add(sb.toString());
		} else {
			for (int i = start; i < str.length(); i++) {
				sb.append(str.charAt(i));
				getValidCombinations(list, str, sb, k, i + 1); // Make a note of i+1
				sb.setLength(sb.length() - 1);
			}
		}
	}
	
	
	/**
	 * https://leetcode.com/problems/combinations/
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> getValidCombinations(int n, int k) {
		List<List<Integer>> list = new ArrayList<>();
		getValidCombinations(list, new ArrayList<>(), n, k, 1);
		return list;
	}

	private void getValidCombinations(List<List<Integer>> list, List<Integer> tempList, int n, int k, int start) {
		if (tempList.size() == k) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i <= n; i++) {
				tempList.add(i);
				// only diff b/w permutation and combination is sending (i+1) and starting the loop from there
				getValidCombinations(list, tempList, n, k, i + 1);  
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	
	/**
	 * https://leetcode.com/problems/combination-sum/
	 * 
	 * All combinations along with repeating elements in output.
	 * If input array is [2, 3, 6, 5] and target is 8,  
	 * the output is [[2, 2, 2, 2], [2, 3, 3], [2, 6], [3, 5]]
	 * 
	 * @param nums - array should not contain any duplicate
	 * @param target
	 * @return
	 */
	public List<List<Integer>> targetSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		targetSum(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private void targetSum(List<List<Integer>> list, List<Integer> tempList, int[] nums, int target, int start) {
		if(target < 0) {
			return;
		}
		
		if(target == 0) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				targetSum(list, tempList, nums, target - nums[i], i); // i for repeating o/p
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/combination-sum-ii/
	 * 
	 * Combinations of non-repeating elements in output.
	 * If input array is [2, 3, 6, 5] and target is 8,  
	 * the output is [[2, 6], [3, 5]]
	 * 
	 * @param nums - array should not contain any duplicate
	 * @param target
	 * @return
	 */
	public List<List<Integer>> targetSumWithoutRepeatingElementsInOutput(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		targetSum1(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private void targetSum1(List<List<Integer>> list, List<Integer> tempList, int[] nums, int target, int start) {
		if(target < 0) {
			return;
		}
		
		if(target == 0) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				targetSum1(list, tempList, nums, target - nums[i], i + 1); // (i+1) for non-repeating o/p
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/combination-sum-ii/
	 * 
	 * Combinations of non-repeating elements in output.
	 * If input array is [10, 1, 2, 7, 6, 1, 5] and target is 8,  
	 * the output is [[1, 2, 5], [1, 7], [2, 6]]
	 * 
	 * @param nums - array should not contain any duplicate
	 * @param target
	 * @return
	 */
	public List<List<Integer>> targetSumWithDuplicatesInInput(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		targetSum2(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private void targetSum2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int target, int start) {
		if (target < 0) {
			return;
		}

		if (target == 0) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < nums.length; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue; // Skip duplicate element
				}
				tempList.add(nums[i]);
				targetSum2(list, tempList, nums, target - nums[i], i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/combination-sum-iii/
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> targetSum3(int n, int k, int target) {
		List<List<Integer>> list = new ArrayList<>();
		targetSum3(list, new ArrayList<>(), n, k, target, 1);
		return list;
	}
	
	private void targetSum3(List<List<Integer>> list, List<Integer> tempList, int n, int k, int target, int start) {
		if(target < 0 || tempList.size() > k) {
			return;
		}
		
		if(target == 0 && tempList.size() == k) {
			list.add(new ArrayList<>(tempList));
		} else {
			for(int i = start; i <= n; i++) {
				tempList.add(i);
				targetSum3(list, tempList, n, k, target - i, i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	
	/**
	 * https://leetcode.com/problems/combination-sum-iv/
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public int targetSum4(int[] nums, int target) {
		int[] count = new int[]{0};
		targetSum4(count, new ArrayList<>(), nums, target);
		return count[0];
	}

	private void targetSum4(int[] count, List<Integer> tempList, int[] nums, int target) {
		if(target < 0) {
			return;
		}
		
		if(target == 0) {
			count[0] = count[0] + 1;
			System.out.println(tempList);
		} else {
			for (int i = 0; i < nums.length; i++) {
				tempList.add(nums[i]);
				targetSum4(count, tempList, nums, target - nums[i]);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/combination-sum-iv/
	 * 
	 * The above method can be simplified to as this one
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public int targetSum4WithoutCountArray(int[] nums, int target) {
		if(target < 0) {
			return 0;
		} else if(target == 0) {
			return 1;
		}
		
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			result += targetSum4WithoutCountArray(nums, target - nums[i]);
		}
		return result;
	}
	
	
	/**
	 * https://leetcode.com/problems/combination-sum-iv/
	 * 
	 * Solution with DP
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public int targetSum4UsingDP(int[] nums, int target) {
		int[] dp = new int[target + 1];
		//dp[0] = 1; This is a edge case
		for(int i = 1; i <= target; i++) {
			dp[i] = -1;
		}
		
		return targetSum4UsingDP(dp, nums, target);
	}
	
	private int targetSum4UsingDP(int[] dp, int[] nums, int target) {
		if(target < 0) {
			return 0;
		} else if(target == 0) {
			return 1;
		} else if(dp[target] != -1) {
			return dp[target];
		}
		
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			result += targetSum4UsingDP(dp, nums, target - nums[i]);
		}
		dp[target] = result;
		return result;
	}

	/**
	 * It's similar as - getValidCombinations() method
	 * Only need to remove the if block to add the combinations with 
	 * different length.
	 * 
	 * For input string 'ABC', it returns
	 * [, A, AB, ABC, AC, B, BC, C]
	 * 
	 * @param str - should not contain duplicate
	 * @param k
	 * @return
	 */
	public List<String> getAllSubsets(String str) {
		List<String> list = new ArrayList<>();
		getAllSubsets(list, str, new StringBuilder(), 0);
		return list;
	}

	private void getAllSubsets(List<String> list, String str, StringBuilder sb, int start) {
		list.add(sb.toString());
		for (int i = start; i < str.length(); i++) {
			sb.append(str.charAt(i));
			getAllSubsets(list, str, sb, i + 1); // Make a note of i+1
			sb.setLength(sb.length() - 1);
		}

	}
	
	/**
	 * https://leetcode.com/problems/subsets/
	 * 
	 * It's similar as - getValidCombinations() method
	 * Only need to remove the if block to add the combinations with 
	 * different length.
	 * 
	 * @param nums - should not contain duplicate
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrackSubsets(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrackSubsets(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList)); // if block removed to get all subsets
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrackSubsets(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
	
	/**
	 * https://leetcode.com/problems/subsets-ii/
	 * 
	 * @param nums - may contain duplicates
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> list = new ArrayList<>();
		backtrackingSubsetsWithDup(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrackingSubsetsWithDup(List<List<Integer>> list, 
				List<Integer> tempList, int[] nums, int start) {
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
	 * Find the next permutation
	 * <br>
	 * https://www.geeksforgeeks.org/next-permutation/
	 *  
	 * @param arr
	 */
	public void nextPermutation(int[] arr) {
		int n = arr.length, i, j;

		// Find for the pivot element. A pivot is the first element from end
		// of sequence which doesn't follow property of non-increasing suffix
		for (i = n - 2; i >= 0; i--) {
			if (arr[i] < arr[i + 1]) {
				break;
			}
		}

		// Check if pivot is found
		if (i < 0) {
			// If pivot not found then reverse the array.
			reverse(arr, 0, arr.length - 1);
		} else {
			// If pivot is not found
			// Find for the successor of pivot in suffix
			for (j = n - 1; j > i; j--) {
				if (arr[j] > arr[i]) {
					break;
				}
			}

			// Swap the pivot and successor
			swap(arr, i, j);

			// Minimize the suffix part
			reverse(arr, i + 1, arr.length - 1);
		}
	}

	private void reverse(int[] arr, int start, int end) {
		while (start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}
	
	
	/**
	 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public int getMinSwaps(int[] nums, int k) {
		int n = nums.length;
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = nums[i];
		}
		
		for(int i = 1; i <= k; i++) {
			nextPermutation(arr);
		}
		
		return countSteps(nums, arr, n);
    }
	
	
	/**
	 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
	 * 
	 * @param s1
	 * @param s2
	 * @param size
	 * @return
	 */
	private int countSteps(int[] s1, int[] s2, int size) {
        int i = 0, j = 0;
        int count = 0;

        while (i < size) {
            j = i;

            while (s1[j] != s2[i]) {
                j += 1;
            }
            
            while (i < j) {
                swap(s1, j, j - 1);
                j -= 1;
                count++;
            }
            i++;
        }
        return count;
    }
	
	
	/**
	 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public int getMinSwaps(String num, int k) {
        char[] arr = num.toCharArray();
        for(int i = 1; i <= k; i++) {
            nextPermutation(arr);
        }

        return countSteps(num.toCharArray(), arr, num.length());
    }

    private void nextPermutation(char[] arr) {
		int n = arr.length, i, j;

		// Find for the pivot element. A pivot is the first element from end
		// of sequence which doesn't follow property of non-increasing suffix
		for (i = n - 2; i >= 0; i--) {
			if (arr[i] < arr[i + 1]) {
				break;
			}
		}

		// Check if pivot is found
		if (i < 0) {
			// If pivot not found then reverse the array.
			reverse(arr, 0, arr.length - 1);
		} else {
			// If pivot is not found
			// Find for the successor of pivot in suffix
			for (j = n - 1; j > i; j--) {
				if (arr[j] > arr[i]) {
					break;
				}
			}

			// Swap the pivot and successor
			swap(arr, i, j);

			// Minimize the suffix part
			reverse(arr, i + 1, arr.length - 1);
		}
	}

	private void reverse(char[] arr, int start, int end) {
		while (start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}

    private int countSteps(char[] s1, char[] s2, int size) {
        int i = 0, j = 0;
        int count = 0;

        while (i < size) {
            j = i;

            while (s1[j] != s2[i]) {
                j += 1;
            }
            
            while (i < j) {
                swap(s1, j, j - 1);
                j -= 1;
                count++;
            }
            i++;
        }
        return count;
    }
}
