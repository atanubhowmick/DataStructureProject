/**
 * 
 */
package dev.atanu.ds.java.array.sort;

/**
 * @author Atanu Bhowmick
 *
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = { 32, 51, 27, 85, 66, 24, 14, 57, 48 };
		sort(arr);
	}

	public static void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}
}
