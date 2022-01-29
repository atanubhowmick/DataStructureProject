/**
 * 
 */
package dev.atanu.ds.java.array.sort;

/**
 * @author Atanu Bhowmick
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] {6, 5, 8, 9, 3, 10, 15, 12, 16};
		QuickSort quickSort = new QuickSort();
		quickSort.sort(nums, 0, nums.length - 1);
		for (int i : nums) {
			System.out.print(i + " ");
		}
	}


	private void sort(int[] nums, int l, int r) {
		if (l >= r)
			return;
		int mid = partition(nums, l, r);
		sort(nums, l, mid);
		sort(nums, mid + 1, r);
	}

	private int partition(int[] nums, int l, int r) {
		int pivot = nums[l];
		while (l < r) {
			while (l < r && nums[r] >= pivot)
				r--;
			nums[l] = nums[r];
			while (l < r && nums[l] <= pivot)
				l++;
			nums[r] = nums[l];
		}
		nums[l] = pivot;
		return l;
	}
}
