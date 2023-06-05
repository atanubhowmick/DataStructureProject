/**
 * 
 */
package dev.atanu.ds.java.array;

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
		int[] arr = new int[] { 10, 1, 2, 7, 6, 1, 5 };
		PermutationCombination pc = new PermutationCombination();
		System.out.println(pc.targetSum3(9, 3, 9));
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
		validPermutations(list, str.toCharArray(), 0, str.length() - 1);
		return list;
	}

	private void validPermutations(List<String> list, char[] arr, int start, int end) {
		if (start == end) {
			list.add(new String(arr));
		} else {
			for (int i = start; i <= end; i++) {
				swap(arr, start, i);
				validPermutations(list, arr, start + 1, end);
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
			StringBuilder builder = new StringBuilder(tempList.size());
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
		validPermutations1(list, nums, 0, nums.length - 1);
		return list;
	}

	private void validPermutations1(List<List<Integer>> list, int[] nums, int start, int end) {
		if (start == end) {
			list.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		} else {
			for (int i = start; i <= end; i++) {
				swap(nums, start, i);
				validPermutations1(list, nums, start + 1, end);
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
		Arrays.sort(nums);
		validPermutations2(list, nums, 0, nums.length - 1);
		return list;
	}

	private void validPermutations2(List<List<Integer>> list, int[] nums, int start, int end) {
		if (start == end) {
			// Not an efficient solution. O(n) is added for below line. Check below solution
			list.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
		} else {
			for (int i = start; i <= end; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				swap(nums, start, i);
				validPermutations2(list, nums, start + 1, end);
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
	
	
	
	
}
