/**
 * 
 */
package dev.atanu.ds.linked.list;

/**
 * @author Atanu Bhowmick
 *
 */
public class LinkedListTest {

	public static void main(String[] args) {
		MyLinkedList<Integer> myLinkedList = new MyLinkedList<Integer>();
		myLinkedList.add(2);
		myLinkedList.add(5);
		myLinkedList.add(10);
		myLinkedList.add(4);

		System.out.println(myLinkedList.getCount());
		myLinkedList.print();
	}
}
