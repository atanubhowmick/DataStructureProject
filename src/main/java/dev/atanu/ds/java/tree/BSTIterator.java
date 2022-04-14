package dev.atanu.ds.java.tree;

import java.util.Stack;

/**
 * {@link https://leetcode.com/problems/binary-search-tree-iterator/}
 * 
 * @author Atanu Bhowmick
 *
 */
public class BSTIterator {

	private Stack<TreeNode> stack = new Stack<>();

	public BSTIterator(TreeNode root) {
		TreeNode current = root;
		while (current != null) {
			stack.push(current);
			current = current.left;
		}
	}

	public int next() {
		TreeNode node = stack.pop();
		TreeNode nextNode = node.right;
		while (nextNode != null) {
			stack.push(nextNode);
			nextNode = nextNode.left;
		}
		return node.val;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

}
