/**
 * 
 */
package dev.atanu.ds.java.tree.traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Atanu Bhowmick
 *
 */
public class TreeInorderTraversal {

	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(5);
		Stack<TreeNode> stack = new Stack<>();

	}

	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //inorderTraversal(root, list);
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null) {
            	stack.push(node);
            	node = node.getLeft();
            } else {
            	node = stack.pop();
            	list.add(node.getData());
            	node = node.getRight();
            }
        }
        return list;
    }

	private void inorderTraversal(TreeNode node, List<Integer> list) {
		if (node.getLeft() != null) {
			inorderTraversal(node.getLeft(), list);
		}
		list.add(node.getData());
		if (node.getRight() != null) {
			inorderTraversal(node.getRight(), list);
		}
	}
}
