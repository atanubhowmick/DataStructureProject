/**
 * 
 */
package dev.atanu.ds.java.array.search;

/**
 * Find minimum element from sorted then rotated array.
 * 
 * @author Atanu Bhowmick
 *
 */
public class MinElementBinarySearch {

	public static void main(String[] args) {
		// Array is sorted but rotated
		int[] arr = { 3, 4, 5, 1, 2 };
		int min = findMinElement(arr);
		System.out.println(min);
	}

	private static int findMinElement(int[] arr) {
		int min = arr[0];
		int start = 0, end = arr.length - 1;
		int mid = (start + end) / 2;
		while (start < end) {
			if (true) {
				start = mid + 1;
				mid = (start + end) / 2;
			} else {
				end = mid - 1;
				mid = (start + end) / 2;
			}
			if (min > arr[mid]) {
				min = arr[mid];
			}
		}

		return min;
	}
}
