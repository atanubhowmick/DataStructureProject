/**
 * 
 */
package dev.atanu.ds.java.array;

/**
 * @author Atanu Bhowmick
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = {4, 7, 11, 15, 20, 24};
		int searchInt = 7;

		int index = search(arr, searchInt);
		System.out.println(index);
	}

	/*
	public static int search(int[] arr, int target) {
		int first = 0;
		int last = arr.length - 1;
		int mid = (first + last) / 2;
		boolean found = false;
		while (first <= last) {
			if (target == arr[mid]) {
				found = true;
				break;
			} else if (target < arr[mid]) {
				last = mid - 1;
				mid = (first + last) / 2;
			} else {
				first = mid + 1;
				mid = (first + last) / 2;
			}
		}
		return found ? mid : -1;
	}
	*/
	
	public static int search(int[] arr, int target) {
		int first = 0;
		int last = arr.length - 1;
		while (first < last) {
			int mid = (first + last) >>> 1;
			if (target > arr[mid]) {
				first = mid + 1;
			} else {
				last = mid;
			}
		}
		return arr[first] == target ? first : -1;
	}
}
