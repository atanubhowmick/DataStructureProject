/**
 * 
 */
package dev.atanu.ds.java.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Atanu Bhowmick
 *
 */
public class PermutationCombination {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PermutationCombination pc = new PermutationCombination();
		System.out.println(pc.getValidPermutations("ABC"));
	}

	/**
	 * Provide permutations. For input string 'ABC' it returns
	 * [AAA, AAB, AAC, ABB, ABC, ACC, BBB, BBC, BCC, CCC]
	 * 
	 * @param str - should not contains any duplicate character
	 */
	public List<String> getPermutations(String str) {
		List<String> list = new ArrayList<>();
		permute(list, str, new StringBuilder(), 0);
		return list;
	}

	private void permute(List<String> list, String str, StringBuilder sb, int start) {
		if (sb.length() == str.length()) {
			list.add(sb.toString());
		} else {
			for (int i = start; i < str.length(); i++) {
				sb.append(str.charAt(i));
				permute(list, str, sb, i);
				sb.setLength(sb.length() - 1);
			}
		}
	}

	
	/**
	 * Provide all combinations. For input string 'ABC' it returns
	 * [ABC, ACB, BAC, BCA, CBA, CAB]
	 * 
	 * @param str - should not contains any duplicate character
	 */
	public List<String> getValidPermutations(String str) {
		List<String> list = new ArrayList<>();
		validPermutations(list, str.toCharArray(), 0, str.length() - 1);
		return list;
	}

	private void validPermutations(List<String> list, char[] str, int start, int end) {
		if (start == end) {
			list.add(new String(str));
		} else {
			for (int i = start; i <= end; i++) {
				swap(str, start, i);
				validPermutations(list, str, start + 1, end);
				swap(str, start, i);
			}
		}
	}

	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
