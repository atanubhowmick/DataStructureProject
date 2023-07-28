/**
 * 
 */
package dev.atanu.ds.java.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/discuss/study-guide/3722472/mastering-sliding-window-technique-a-comprehensive-guide
 * 
 * @author Atanu Bhowmick
 *
 */
public class SlidingWindow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SlidingWindow slidingWindow = new SlidingWindow();
		int[] arr = new int[] { 0, 1, 1, 1, 0, 1, 1, 0, 1 };
		System.out.println(slidingWindow.characterReplacement("KKKKKKJJJJ", 2));
	}

	/**
	 * https://leetcode.com/problems/minimum-size-subarray-sum/
	 * 
	 * @param target
	 * @param nums
	 * @return
	 */
	public int minSubArrayLen(int target, int[] nums) {
		int i = 0, n = nums.length;
		int len = n + 1;
		for (int j = 0; j < n; j++) {
			target -= nums[j];

			while (target <= 0) {
				len = Math.min(len, j - i + 1);
				target += nums[i++];
			}
		}
		return len == n + 1 ? 0 : len;
	}

	/**
	 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
	 * 
	 * @param nums
	 * @return
	 */
	public int longestSubarray(int[] nums) {
		int i = 0, k = 1;
		int maxLen = 0;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] == 0) {
				k--;
			}
			if (k < 0) {
				if (nums[i] == 0) {
					k++;
				}
				i++;
			}
			maxLen = Math.max(maxLen, j - i);
		}
		return maxLen;
	}

	/**
	 * https://leetcode.com/problems/arithmetic-slices/
	 * 
	 * @param nums
	 * @return
	 */
	public int numberOfArithmeticSlices(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int prevDiff = nums[i + 1] - nums[i];
			int j = i + 1;
			while (j + 1 < nums.length && nums[j + 1] - nums[j] == prevDiff) {
				count++;
				j++;
			}
		}

		return count;
	}

	/**
	 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		int maxLen = 0, i = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int j = 0; j < s.length(); j++) {
			char ch = s.charAt(j);
			if (map.containsKey(ch)) {
				i = Math.max(i, map.get(ch) + 1);
			}
			maxLen = Math.max(j - i + 1, maxLen);
			map.put(ch, j);
		}
		return maxLen;
	}

	/**
	 * https://leetcode.com/problems/longest-repeating-character-replacement/
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement(String s, int k) {
		int maxLen = 0, maxCount = 0;
		int[] count = new int[26];

		for (int i = 0; i < s.length(); i++) {
			int endIndex = s.charAt(i) - 'A';
			++count[endIndex];
			maxCount = Math.max(maxCount, count[endIndex]);

			if (maxLen - maxCount < k) {
				maxLen++;
			} else {
				int startIndex = s.charAt(i - maxLen) - 'A';
				--count[startIndex];
			}
		}
		return maxLen;
	}

	/**
	 * Two problems are same.
	 * 
	 * https://leetcode.com/problems/longest-repeating-character-replacement/
	 * https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
	 * 
	 * @param answerKey
	 * @param k
	 * @return
	 */
	public int maxConsecutiveAnswers(String answerKey, int k) {
		int maxLen = 0, maxCount = 0;
		int[] count = new int[26];

		for (int i = 0; i < answerKey.length(); i++) {
			int endIndex = answerKey.charAt(i) - 'A';
			++count[endIndex];
			maxCount = Math.max(maxCount, count[endIndex]);

			if (maxLen - maxCount < k) {
				maxLen++;
			} else {
				int startIndex = answerKey.charAt(i - maxLen) - 'A';
				--count[startIndex];
			}
		}
		return maxLen;
	}

	/**
	 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
	 * 
	 * @param s
	 * @return
	 */
	public int numberOfSubstrings(String s) {
		int count = 0, n = s.length();
		int p1 = -1, p2 = -1, p3 = -1;

		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'a') {
				p1 = i;
			} else if (s.charAt(i) == 'b') {
				p2 = i;
			} else {
				p3 = i;
			}

			if (p1 == -1 || p2 == -1 || p3 == -1) {
				continue;
			}

			int min = Math.min(p1, Math.min(p2, p3));
			count += min + 1;
		}
		return count;
	}

	/**
	 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
	 * 
	 * @param s
	 * @return
	 */
	public int numberOfSubstrings1(String s) {
		int count = 0, n = s.length();
		int lastIdx[] = { -1, -1, -1 };
		
		for (int i = 0; i < n; ++i) {
			lastIdx[s.charAt(i) - 'a'] = i;
			int min = Math.min(lastIdx[0], Math.min(lastIdx[1], lastIdx[2]));
			count += min + 1;
		}
		return count;
	}
}
