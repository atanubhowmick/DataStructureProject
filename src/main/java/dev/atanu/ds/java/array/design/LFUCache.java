/**
 * 
 */
package dev.atanu.ds.java.array.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author Atanu Bhowmick
 *
 */
public class LFUCache {
	
	public static void main(String[] args) {
		LFUCache lfuCache = new LFUCache(3);
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		lfuCache.put(1, 1);
		lfuCache.get(2);
		lfuCache.get(1);
		lfuCache.get(2);
		
		lfuCache.put(5, 5);
		lfuCache.get(5);
		lfuCache.put(5, 6);
		
		lfuCache.put(7, 7);
	}

	private int min;
	private final int capacity;

	private final Map<Integer, Integer> keyValueMap;
	private final Map<Integer, Integer> keyCountMap;
	private final Map<Integer, LinkedHashSet<Integer>> countToKeysMap;

	public LFUCache(int capacity) {
		this.min = 0;
		this.capacity = capacity;
		this.keyValueMap = new HashMap<>();
		this.keyCountMap = new HashMap<>();
		this.countToKeysMap = new HashMap<>();
	}

	public int get(int key) {
		if (!keyValueMap.containsKey(key))
			return -1;

		int count = keyCountMap.get(key);
		
		// remove key from current count as we will increase the count
		countToKeysMap.get(count).remove(key); 
		
		if (count == min && countToKeysMap.get(count).isEmpty()) {
			min++; // nothing in the current min bucket
		}

		putCount(key, count + 1);
		return keyValueMap.get(key);
	}

	public void put(int key, int value) {
		if (capacity <= 0)
			return;

		if (keyValueMap.containsKey(key)) {
			keyValueMap.put(key, value); // update key's value
			get(key); // update key's count
			return;
		}

		if (keyValueMap.size() >= capacity) {
			evict(countToKeysMap.get(min).iterator().next()); // evict LRU from this min count bucket
		}

		min = 1;
		putCount(key, min); // adding new key and count
		keyValueMap.put(key, value); // adding new key and value
	}

	private void evict(int key) {
		countToKeysMap.get(min).remove(key);
		keyValueMap.remove(key);
	}

	private void putCount(int key, int count) {
		keyCountMap.put(key, count);
		countToKeysMap.computeIfAbsent(count, ignore -> new LinkedHashSet<>());
		countToKeysMap.get(count).add(key);
	}
}
