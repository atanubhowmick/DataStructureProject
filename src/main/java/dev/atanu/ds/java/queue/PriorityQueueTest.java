package dev.atanu.ds.java.queue;

import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((a1, a2) -> (a1 > a2 ? -1 : (a1 < a2 ? 1 : 0)));
		queue.add(5);
		queue.add(1);
		queue.add(10);
		queue.add(7);
		queue.add(12);
		queue.add(4);

		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
	}

}
