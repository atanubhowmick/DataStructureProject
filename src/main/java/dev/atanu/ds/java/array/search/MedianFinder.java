package dev.atanu.ds.java.array.search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * @author Atanu Bhowmick
 *
 */
public class MedianFinder {

	private PriorityQueue<Integer> maxHeap;
	private PriorityQueue<Integer> minHeap;

	private boolean isEven = true;

	public MedianFinder() {
		maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		minHeap = new PriorityQueue<>();
	}

	public void addNum(int num) {
		if (isEven) {
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		} else {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		}
		isEven = !isEven;
	}

	public double findMedian() {
		double median = 0d;
		if (isEven) {
			median = ((double) (maxHeap.peek() + minHeap.peek())) / 2;
		} else {
			median = (double) maxHeap.peek();
		}
		return median;
	}

	public static void main(String[] args) {
		MedianFinder medianFinder = new MedianFinder();
		medianFinder.addNum(1);
		medianFinder.addNum(2);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(3);
		System.out.println(medianFinder.findMedian());
	}

}
