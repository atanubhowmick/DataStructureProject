/**
 * 
 */
package dev.atanu.ds.java.array.search;

import java.util.Scanner;

/**
 * @author Atanu Bhowmick
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = { 1, 14, 24, 27, 32, 51, 57, 66, 85 };

		System.out.print("Search for int: ");
		Scanner scanner = new Scanner(System.in);
		int searchInt = scanner.nextInt();
		scanner.close();

		search(arr, searchInt);
	}

	public static void search(int[] arr, int searchInt) {
		int first = 0;
		int last = arr.length - 1;
		int mid = (first + last) / 2;
		boolean found = false;
		while (first <= last) {
			if (searchInt == arr[mid]) {
				found = true;
				break;
			} else if (searchInt < arr[mid]) {
				last = mid - 1;
				mid = (first + last) / 2;
			} else {
				first = mid + 1;
				mid = (first + last) / 2;
			}
		}
		if (found) {
			System.out.println("Found");
		} else {
			System.out.println("Not Found");
		}
	}
}
