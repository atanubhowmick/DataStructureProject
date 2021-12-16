/**
 * 
 */
package dev.atanu.ds.java.stack;

/**
 * @author Atanu Bhowmick
 *
 */
public class StackUsingLinkedList<T> {

	private Node<T> head;
	private int count;

	public StackUsingLinkedList() {
		head = null;
		count = 0;
	}

	public void push(T data) {
		Node<T> node = new Node<>(data);
		Node<T> current = head;
		if(current == null) {
			head = node;
		} else {
			// TODO
		}
		count++;
	}

	public T pop() {
		// TODO
		return null;
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
