/**
 * 
 */
package dev.atanu.ds.java.linked.list;

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
		System.out.println(myLinkedList.get(2));
		
		myLinkedList.add(20, 2);
		myLinkedList.print();
		
		myLinkedList.remove(3);
		myLinkedList.print();
	}
}
