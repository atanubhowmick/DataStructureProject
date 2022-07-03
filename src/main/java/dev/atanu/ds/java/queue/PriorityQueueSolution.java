/**
 * 
 */
package dev.atanu.ds.java.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Atanu Bhowmick
 *
 */
public class PriorityQueueSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	/**
	 * https://leetcode.com/problems/merge-intervals/
	 * 
	 * @param intervals
	 * @return
	 */
	public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (a1, a2) -> Integer.compare(a1[0], a2[0]));
        
        for(int[] interval : intervals) {
            queue.offer(interval);
        }
        
        List<int[]> result = new ArrayList<>();
        int[] prevInterval = queue.poll();
        result.add(prevInterval);
        
        while(!queue.isEmpty()) {
            int[] interval = queue.poll();
            if(interval[0] <= prevInterval[1]) {
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            } else {
                result.add(interval);
                prevInterval = interval;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

}
