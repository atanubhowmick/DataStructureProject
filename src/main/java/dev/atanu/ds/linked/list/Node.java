/**
 * 
 */
package dev.atanu.ds.linked.list;

/**
 * @author Atanu Bhowmick
 *
 */
public class Node<T> {

	private T data;
	private Node<T> next;

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	/**
	 * Constructor
	 * 
	 * @param data
	 * @param next
	 */
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
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
