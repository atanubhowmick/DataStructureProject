/**
 * 
 */
package dev.atanu.ds.java.tree.traverse;

/**
 * @author Atanu Bhowmick
 *
 */
public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	/**
	 * Tree Node Constructor
	 * 
	 * @param data
	 */
	public TreeNode(int data) {
		this.val = data;
	}

	/**
	 * Tree Node Constructor
	 * 
	 * @param data
	 * @param left
	 * @param right
	 */
	public TreeNode(int data, TreeNode left, TreeNode right) {
		this.val = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return val;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.val = data;
	}

	/**
	 * @return the left
	 */
	public TreeNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TreeNode left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TreeNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TreeNode right) {
		this.right = right;
	}

}
