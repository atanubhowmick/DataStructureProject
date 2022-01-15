package dev.atanu.ds.java.array.search;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private LinkedHashMap<Integer, Integer> map;

	public LRUCache(int capacity) {
		map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {

			private static final long serialVersionUID = 5682603781795576645L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > capacity;
			}
		};
	}

	public int get(int key) {
		return map.getOrDefault(key, -1);
	}

	public void set(int key, int value) {
		map.put(key, value);
	}
}
