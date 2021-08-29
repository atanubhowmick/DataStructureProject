/**
 * 
 */
package dev.atanu.ds.java.stack;

/**
 * @author Atanu Bhowmick
 *
 */
public class Stack {

	int[] arr;
	int top;
	int size;

	/**
	 * Constructor
	 * 
	 * @param size
	 */
	public Stack(int size) {
		arr = new int[size];
		this.size = size;
		top = -1;
	}

	/**
	 * Push to the stack
	 * 
	 * @param data
	 */
	public void push(int data) {
		if (top >= size - 1) {
			throw new IllegalArgumentException("Stack Overflow");
		}
		arr[++top] = data;
	}

	/**
	 * Pop from the stack
	 * 
	 * @return data
	 */
	public int pop() {
		if (top <= -1) {
			throw new IllegalArgumentException("Stack Underflow");
		}
		return arr[top--];
	}

	/**
	 * Get the last element from the stack
	 * 
	 * @return the last element
	 */
	public int peek() {
		if (top <= -1) {
			throw new IllegalArgumentException("Stack Underflow");
		}
		return arr[top];
	}

	/**
	 * Check if the stack is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return top <= -1;
	}

	/**
	 * Print the stack
	 */
	public void print() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
		} else {
			StringBuilder builder = new StringBuilder("[");
			for (int i = 0; i <= top; i++) {
				builder.append(arr[i]);
				if (i != top) {
					builder.append(", ");
				}
			}
			builder.append("]");
			System.out.println(builder.toString());
		}
	}
}
