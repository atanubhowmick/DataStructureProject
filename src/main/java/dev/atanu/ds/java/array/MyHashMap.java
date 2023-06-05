package dev.atanu.ds.java.array;

public class MyHashMap {

	Node[] nodes = new Node[100];
	
	public void put(int key, int value) {
		Integer.hashCode(key);
	}
	
	
	class Node {
		int key;
		int value;
		Node next;
	}
	
}
