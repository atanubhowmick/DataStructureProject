/**
 * 
 */
package dev.atanu.ds.java.array.sort;

import java.util.Arrays;

/**
 * @author Atanu Bhowmick
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] {6, 5, 8, 9, 3, 10, 15, 12, 16, 1};
		QuickSort quickSort = new QuickSort();
		quickSort.sort(nums, 0, nums.length - 1);
		System.out.println(Arrays.toString(nums));
	}

	/*
	private void sort(int[] nums, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = partition(nums, left, right);
		sort(nums, left, mid);
		sort(nums, mid + 1, right);
	}

	private int partition(int[] nums, int left, int right) {
		int pivot = nums[left];
		while (left < right) {
			while (left < right && nums[right] >= pivot) {
				right--;
			}
			nums[left] = nums[right];
			
			while (left < right && nums[left] <= pivot)
			{
				left++;
			}
			nums[right] = nums[left];
		}
		nums[left] = pivot;
		return left;
	}
	*/
	
	private void sort(int[] nums, int left, int right) {
        if(left >= right) {
            return;
        }
        int mid = partition(nums, left, right);
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
    }
    
	private int partition(int[] nums, int left, int right) {
		int pivot = nums[left];
		int low = left, high = right;
		while (left < right) {
			while (left < high && pivot >= nums[left]) {
				left++;
			}

			while (low < right && pivot <= nums[right]) {
				right--;
			}
			if (left < right) {
				swap(nums, left, right);
			}
		}
		swap(nums, low, right);
		return right;
	}
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
