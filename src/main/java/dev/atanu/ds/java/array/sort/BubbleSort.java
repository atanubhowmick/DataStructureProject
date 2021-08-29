/**
 * 
 */
package dev.atanu.ds.java.array.sort;

/**
 * @author Atanu Bhowmick
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = { 32, 51, 27, 85, 66, 24, 14, 57, 1};
		sort(arr);
	}

	public static void sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}
}
