/**
 * 
 */
package dev.atanu.ds.java.array.sort;

import java.util.Arrays;

/**
 * @author Atanu Bhowmick
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = { 32, 51, 27, 85, 66, 24, 14, 57, 1 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}
}
