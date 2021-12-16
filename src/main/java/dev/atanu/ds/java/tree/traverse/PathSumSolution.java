/**
 * 
 */
package dev.atanu.ds.java.tree.traverse;

/**
 * @author Atanu Bhowmick
 *
 */
public class PathSumSolution {

	public static void main(String[] args) {
		PathSumSolution pathSumSolution = new PathSumSolution();
		TreeNode root = pathSumSolution.createTree();
		boolean isValid = pathSumSolution.hasPathSum(root, 27);
		System.out.println(isValid);
	}

	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return false;
		}
		return hasPathSum(root, 0, targetSum);
	}

	public boolean hasPathSum(TreeNode node, int sum, int targetSum) {
		if (node == null) {
			return false;
		}
		sum += node.getData();
		if (node.getLeft() == null && node.getRight() == null && sum == targetSum) {
			return true;
		}
		return hasPathSum(node.getLeft(), sum, targetSum) || hasPathSum(node.getRight(), sum, targetSum);
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
	
	private TreeNode createTree1() {
		TreeNode root = new TreeNode(1);
		
		TreeNode left1 = new TreeNode(2);
		root.setLeft(left1);
		
		return root;
	}
}
