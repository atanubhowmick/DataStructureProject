/**
 * 
 */
package dev.atanu.ds.java.tree.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Atanu Bhowmick
 * 
 *         <br>
 *         {@link https://leetcode.com/problems/path-sum-ii/}
 * 
 */
public class PathSumSolutionII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PathSumSolutionII solution = new PathSumSolutionII();
		TreeNode root = solution.createTree();
		List<List<Integer>> list = solution.pathSum(root, 22);
		System.out.println(list);
	}

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		List<List<Integer>> list = new ArrayList<>();
		pathSum(root, list, new LinkedList<Integer>(), 0, targetSum);
		return list;
	}

	private void pathSum(TreeNode node, List<List<Integer>> list, LinkedList<Integer> path, int sum,
			int targetSum) {
		path.offer(node.getData());
		sum = sum + node.getData();
		if (sum == targetSum && node.getLeft() == null && node.getRight() == null) {
			list.add(new LinkedList<Integer>(path));
		}
		if(node.getLeft() != null) {
			pathSum(node.getLeft(), list, path, sum, targetSum);
			path.pollLast();
		}
		
		if(node.getRight() != null) {
			pathSum(node.getRight(), list, path, sum, targetSum);
			path.pollLast();
		}
	}
	
	/**
	 * The tree is like this
	 * 
	 * <br>          5
	 * <br>         / \
	 * <br>        4   8
	 * <br>       /   / \
	 * <br>      11  12  4
	 * <br>     /  \      \
	 * <br>    7    2      1
	 *     
	 * @return root
	 */
	private TreeNode createTree() {
		TreeNode root = new TreeNode(5);
		
		TreeNode left1 = new TreeNode(4);
		TreeNode right1 = new TreeNode(8);
		root.setLeft(left1);
		root.setRight(right1);
		
		TreeNode left2 = new TreeNode(11);
		left1.setLeft(left2);
		
		TreeNode left3 = new TreeNode(7);
		TreeNode right3 = new TreeNode(2);
		left2.setLeft(left3);
		left2.setRight(right3);
		
		TreeNode left4 = new TreeNode(12);
		TreeNode right4 = new TreeNode(4);
		right1.setLeft(left4);
		right1.setRight(right4);
		
		TreeNode right5 = new TreeNode(1);
		right4.setRight(right5);
		
		return root;
	}

}
