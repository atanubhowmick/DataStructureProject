package dev.atanu.ds.java.array.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] nums = new int[] { 6, 5, 8, 9, 4, 10, 15, 12, 16, 1 };
		MergeSort mergeSort = new MergeSort();
		mergeSort.sort(nums, 0, nums.length - 1);
		System.out.println(Arrays.toString(nums));
	}

	private void sort(int[] nums, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		sort(nums, left, mid);
		sort(nums, mid + 1, right);
		merge(nums, left, right);
	}

	/*
	private void merge(int[] nums, int left, int right) {
		int mid = left + (right - left) / 2;
		int size = right - left + 1;
		int[] tmp = new int[size];
		int i = left, j = mid + 1, k = 0;
		while (i <= mid || j <= right) {
			if (i > mid || (j <= right && nums[i] > nums[j])) {
				tmp[k++] = nums[j++];
			} else {
				tmp[k++] = nums[i++];
			}
		}
		System.arraycopy(tmp, 0, nums, left, size);
	} */
	
	private void merge(int[] nums, int left, int right) {
		int mid = left + (right - left) / 2;
		int size = right - left + 1;
		int[] tmp = new int[size];
		int i = left, j = mid + 1, k = 0;
		while (i <= mid && j <= right) {
			if (nums[i] < nums[j]) {
				tmp[k++] = nums[i++];
			} else {
				tmp[k++] = nums[j++];
			}
		}
		while(i <= mid) {
			tmp[k++] = nums[i++];
		}
		while(j <= right) {
			tmp[k++] = nums[j++];
		}
		System.arraycopy(tmp, 0, nums, left, size);
	}
}
