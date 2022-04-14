package dev.atanu.ds.java.linked.list;

public class LinkedListSolution {

	public static void main(String[] args) {

	}

	/**
	 * https://leetcode.com/problems/sort-list/
	 * 
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head, fast = head, prev = null;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		// Break the list in two parts
		prev.next = null;

		// Now sort both parts
		ListNode node1 = sortList(head);
		ListNode node2 = sortList(slow);
		return mergeNodes(node1, node2);
	}

	private ListNode mergeNodes(ListNode node1, ListNode node2) {
		ListNode tempHead = new ListNode(0);
		ListNode curr = tempHead;
		
		while (node1 != null && node2 != null) {
			if (node1.val <= node2.val) {
				curr.next = node1;
				node1 = node1.next;
			} else {
				curr.next = node2;
				node2 = node2.next;
			}
			curr = curr.next;
		}

		while (node1 != null) {
			curr.next = node1;
			node1 = node1.next;
			curr = curr.next;
		}

		while (node2 != null) {
			curr.next = node2;
			node2 = node2.next;
			curr = curr.next;
		}
		return tempHead.next;
	}
}
