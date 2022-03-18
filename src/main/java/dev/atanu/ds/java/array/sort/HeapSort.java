package dev.atanu.ds.java.array.sort;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[] nums = new int[] { 6, 5, 8, 9, 3, 10, 15, 12, 16, 1 };
		HeapSort heapSort = new HeapSort();
		heapSort.sort(nums);
		System.out.println(Arrays.toString(nums));
	}

	public void sort(int[] nums) {
		for (int i = nums.length / 2 - 1; i >= 0; i--) {
			heapify(nums, i, nums.length - 1);
		}
		for (int i = nums.length - 1; i >= 1; i--) {
			swap(nums, 0, i);
			heapify(nums, 0, i - 1);
		}
	}

	private void heapify(int[] nums, int i, int end) {
		while (i <= end) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int maxIndex = i;
			if (left <= end && nums[left] > nums[maxIndex])
				maxIndex = left;
			if (right <= end && nums[right] > nums[maxIndex])
				maxIndex = right;
			if (maxIndex == i)
				break;
			swap(nums, i, maxIndex);
			i = maxIndex;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
