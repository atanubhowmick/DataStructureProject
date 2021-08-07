/**
 * 
 */
package dev.atanu.ds.linked.list;

/**
 * @author Atanu Bhowmick
 *
 */
public class MyLinkedList<T> {

	private Node<T> head;
	private int count;

	/**
	 * Constructor
	 */
	public MyLinkedList() {
		this.head = null;
		this.count = 0;
	}

	/**
	 * Add a node
	 * 
	 * @param data
	 */
	public void add(T data) {
		Node<T> node = new Node<T>(data);
		Node<T> current = head;
		if (current == null) {
			this.head = node;
		} else {
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(node);
		}
		count += 1;
	}

	public void remove(Node<T> node) {

	}

	/**
	 * Print the list
	 */
	public void print() {
		if (this.head == null) {
			System.out.println("No element present");
		} else {
			Node<T> current = head;
			StringBuilder builder = new StringBuilder("[");
			while (current != null) {
				builder.append(current.getData());
				if (current.getNext() != null) {
					builder.append(", ");
				}
				current = current.getNext();
			}
			builder.append("]");
			System.out.println(builder.toString());
		}
	}

	/**
	 * Get Count
	 * 
	 * @return
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Check if empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.head == null;
	}
}
