/**
 * 
 */
package dev.atanu.ds.java.linked.list;

/**
 * @author Atanu Bhowmick
 *
 */
public class MyLinkedList<T> {

	private Node<T> head;
	private int count;
	private static final String INVALID_INDEX = "Invalid Index";

	/**
	 * Constructor
	 */
	public MyLinkedList() {
		this.head = null;
		this.count = 0;
	}

	/**
	 * Get by index
	 * 
	 * @param index
	 * @return data
	 */
	public T get(int index) {
		if (index < 0 || index >= count) {
			throw new IllegalArgumentException(INVALID_INDEX);
		} else if (index == 0) {
			return head.getData();
		} else {
			Node<T> current = head;
			for (int i = 1; i <= index; i++) {
				current = current.getNext();
			}
			return current.getData();
		}
	}

	/**
	 * Add a node into the linked list at the end
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

	/**
	 * Add a node into the linked list in the index position
	 * 
	 * @param data
	 * @param index
	 */
	public void add(T data, int index) {
		if (index < 0 || index > count) {
			throw new IllegalArgumentException(INVALID_INDEX);
		}
		Node<T> node = new Node<T>(data);
		Node<T> current = head;
		if (index == 0) {
			this.head = node;
			head.setNext(current);
		} else {
			for (int i = 1; i < index; i++) {
				current = current.getNext();
			}
			node.setNext(current.getNext());
			current.setNext(node);
		}
		count += 1;
	}

	/**
	 * Remove element from the end of the linked list
	 */
	public void remove() {
		Node<T> current = head;
		if (current == null) {
			System.out.println("Can't remove. Empty list");
		} else if (this.count == 1) {
			this.head = null;
			count -= 1;
		} else {
			while (current.getNext().getNext() != null) {
				current = current.getNext();
			}
			current.setNext(null);
			count -= 1;
		}
	}

	/**
	 * Remove element from the index position of the linked list
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index > count) {
			throw new IllegalArgumentException(INVALID_INDEX);
		}
		Node<T> current = head;
		if (index == 0) {
			head = current.getNext();

		} else {
			for (int i = 1; i < index; i++) {
				current = current.getNext();
			}
			current.setNext(current.getNext().getNext());
		}
		count -= 1;
	}

	/**
	 * Print the linked list
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
	 * Get count of total elements
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

	/**
	 * 
	 * @author Atanu bhowmick
	 *
	 * @param <T>
	 */
	private static class Node<T> {

		private T data;
		private Node<T> next;

		/**
		 * Constructor
		 * 
		 * @param data
		 */
		Node(T data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * @return the next
		 */
		public Node<T> getNext() {
			return next;
		}

		/**
		 * @param next the next to set
		 */
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
}
