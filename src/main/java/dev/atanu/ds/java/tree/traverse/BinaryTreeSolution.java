package dev.atanu.ds.java.tree.traverse;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeSolution {
	
	public static void main(String[] args) {
		BinaryTreeSolution solution = new BinaryTreeSolution();
		TreeNode root = solution.createTree();
		//solution.flatten(root);
		//while(root != null) {
		//	System.out.println(root.getData());
		//	root = root.getRight();
		//}
		System.out.println("Total counts: " + solution.pathSumIII(root, 17));
		TreeNode node = solution.createBinaryElementTree();
		System.out.println(solution.sumRootToLeaf(node));
	}

	private TreeNode prev = null;

	/**
	 * {@link https://leetcode.com/problems/flatten-binary-tree-to-linked-list/}
	 * 
	 * @param root
	 */
	public void flatten(TreeNode root) {
		if (root == null)
			return;
		flatten(root.getRight());
		flatten(root.getLeft());
		root.setRight(prev);
		root.setLeft(null);
		prev = root;
	}
	
	private int count = 0;
    
    /**
     * {@link https://leetcode.com/problems/path-sum-iii/}
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSumIII(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        pathSumHelperIII(root, targetSum);
        return count;
    }
    
    private int pathSumHelperIII(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        int sum = 0;
        sum += root.val;
        sum += pathSumHelperIII(root.left, targetSum);
        if(sum == targetSum) {
            count++;
        }
        sum += pathSumHelperIII(root.right, targetSum);
        if(sum == targetSum) {
            count++;
        }
        return sum;
    }
    
    /**
     * {@link https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/}
     * 
     * @param root
     * @return sum
     */
    public int sumRootToLeaf(TreeNode root) {
        List<String> list = new ArrayList<>();
        preorder(root, list, new StringBuilder());
        int sum = 0;
        for(String s : list) {
            sum += decimalStringToBinary(s);
        }
        return sum;
    }
    
    private void preorder(TreeNode node, List<String> list, StringBuilder builder) {
        if(node == null) {
            return;
        }
        builder.append(node.val);
        if(node.left == null && node.right == null) {
            list.add(builder.toString());
        }
        preorder(node.left, list, builder);
        preorder(node.right, list, builder);
        builder.setLength(builder.length() - 1);
    }
    
    private int decimalStringToBinary(String decimal) {
        char[] arr = decimal.toCharArray();
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            sum = sum * 2 + (arr[i] - '0');
        }
        return sum;
    }
	
	/**
	 * The tree is like this
	 * 
	 * <br>          15
	 * <br>         / \
	 * <br>        2   6
	 * <br>       /   / \
	 * <br>      3   11  8
	 * <br>     /  \      \
	 * <br>    10    5      9
	 *     
	 * @return root
	 */
	private TreeNode createTree() {
		TreeNode root = new TreeNode(15);
		
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(6);
		root.setLeft(left1);
		root.setRight(right1);
		
		TreeNode left2 = new TreeNode(3);
		left1.setLeft(left2);
		
		TreeNode left3 = new TreeNode(10);
		TreeNode right3 = new TreeNode(5);
		left2.setLeft(left3);
		left2.setRight(right3);
		
		TreeNode left4 = new TreeNode(11);
		TreeNode right4 = new TreeNode(8);
		right1.setLeft(left4);
		right1.setRight(right4);
		
		TreeNode right5 = new TreeNode(9);
		right4.setRight(right5);
		
		return root;
	}
	
	/**
	 * The tree is like this
	 * 
	 * <br>          1
	 * <br>         / \
	 * <br>       0     1
	 * <br>      / \   / \
	 * <br>     0   1 0   1
	 *     
	 * @return root
	 */
	private TreeNode createBinaryElementTree() {
		TreeNode root = new TreeNode(1);
		
		TreeNode left1 = new TreeNode(0);
		TreeNode right1 = new TreeNode(1);
		root.setLeft(left1);
		root.setRight(right1);
		
		TreeNode left2 = new TreeNode(0);
		TreeNode right2 = new TreeNode(1);
		left1.setLeft(left2);
		left1.setRight(right2);
		
		TreeNode left4 = new TreeNode(0);
		TreeNode right4 = new TreeNode(1);
		right1.setLeft(left4);
		right1.setRight(right4);
		
		return root;
	}

}
