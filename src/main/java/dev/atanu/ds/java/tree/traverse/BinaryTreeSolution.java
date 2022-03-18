package dev.atanu.ds.java.tree.traverse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BinaryTreeSolution {

	public static void main(String[] args) {
		BinaryTreeSolution solution = new BinaryTreeSolution();
		List<Integer> list = new ArrayList<Integer>();
		solution.dfsPostorder(solution.createTree(), list);
		System.out.println(list);
	}

	/**
	 * Inorder Traversal
	 * 
	 * @param node
	 * @param list
	 */
	public void dfsInorder(TreeNode node, List<Integer> list) {
		if (node == null) {
			return;
		}
		dfsInorder(node.left, list);
		list.add(node.val);
		dfsInorder(node.right, list);
	}

	/**
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> dfsInorderStack(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			list.add(node.val);
			node = node.right;
		}
		return list;
	}

	/**
	 * @param root
	 * @return
	 */
	public List<Integer> dfsInorderStackIfElse(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
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

	/**
	 * 
	 * @param node
	 * @param list
	 */
	public void dfsPreorder(TreeNode node, List<Integer> list) {
		if (node == null) {
			return;
		}
		list.add(node.val);
		dfsPreorder(node.left, list);
		dfsPreorder(node.right, list);
	}

	/**
	 * 
	 * @param node
	 * @param list
	 */
	public void dfsPostorder(TreeNode node, List<Integer> list) {
		if (node == null) {
			return;
		}
		dfsPostorder(node.left, list);
		dfsPostorder(node.right, list);
		list.add(node.val);
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
	 * 
	 * @param root
	 * @param targetSum
	 * @return
	 */
	public int pathSumIII(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}
		pathSumHelperIII(root, targetSum);
		return count;
	}

	private int pathSumHelperIII(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}
		int sum = 0;
		sum += root.val;
		sum += pathSumHelperIII(root.left, targetSum);
		if (sum == targetSum) {
			count++;
		}
		sum += pathSumHelperIII(root.right, targetSum);
		if (sum == targetSum) {
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
		for (String s : list) {
			sum += decimalStringToBinary(s);
		}
		return sum;
	}

	private void preorder(TreeNode node, List<String> list, StringBuilder builder) {
		if (node == null) {
			return;
		}
		builder.append(node.val);
		if (node.left == null && node.right == null) {
			list.add(builder.toString());
		}
		preorder(node.left, list, builder);
		preorder(node.right, list, builder);
		builder.setLength(builder.length() - 1);
	}

	private int decimalStringToBinary(String decimal) {
		char[] arr = decimal.toCharArray();
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum = sum * 2 + (arr[i] - '0');
		}
		return sum;
	}

	/**
	 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
	 * 
	 * @param root
	 * @return
	 */
	public int sumEvenGrandparent(TreeNode root) {
		int[] arr = new int[1];
		sumEvenGrandparent(root, false, false, arr);
		return arr[0];
	}

	private void sumEvenGrandparent(TreeNode node, boolean evenGrandParent, boolean evenParent, int[] arr) {
		if (node == null) {
			return;
		}

		if (evenGrandParent) {
			arr[0] = arr[0] + node.val;
		}

		sumEvenGrandparent(node.left, evenParent, node.val % 2 == 0, arr);
		sumEvenGrandparent(node.right, evenParent, node.val % 2 == 0, arr);
	}

	/**
	 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
	 * 
	 * @param preorder
	 * @return
	 */
	public TreeNode bstFromPreorder(int[] preorder) {
		if (preorder == null) {
			return null;
		}
		int[] nodeIdx = new int[] { 0 };
		return bstHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, nodeIdx);
	}

	private TreeNode bstHelper(int[] preorder, int left, int right, int[] nodeIdx) {
		if (nodeIdx[0] == preorder.length || preorder[nodeIdx[0]] < left || preorder[nodeIdx[0]] > right) {
			return null;
		}
		int val = preorder[nodeIdx[0]++];
		TreeNode node = new TreeNode(val);
		node.left = bstHelper(preorder, left, val, nodeIdx);
		node.right = bstHelper(preorder, val, right, nodeIdx);
		return node;
	}

	public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTarget(root, k, set);
    }
    
    private boolean findTarget(TreeNode node, int k, Set<Integer> set) {
        if(node == null) {
            return false;
        }
        if(set.contains(k - node.val)) {
            return true;
        } else {
            set.add(node.val);
        }
        return findTarget(node.left, k, set) || findTarget(node.right, k, set);
    }
    
    /**
     * https://leetcode.com/problems/validate-binary-search-tree/
     * 
     * @param root
     * @return
     */
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (pre != null && root.val <= pre.val)
				return false;
			pre = root;
			root = root.right;
		}
		return true;
	}
	
	/**
	 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
	 * 
	 * @param root
	 * @return
	 */
	public int maxAncestorDiff(TreeNode root) {
        return inorder(root, root.val, root.val);
    }
    
	private int inorder(TreeNode node, int min, int max) {
        if(node == null) {
            return max - min;
        }
        min = Math.min(node.val, min);
        max = Math.max(node.val, max);
        return Math.max(inorder(node.left, min, max), inorder(node.right, min, max));
    }
    
	/**
	 * The tree is like this
	 * 
	 *      15
	 *      / \
	 *     4   6
	 *    /   / \
	 *   3   11  8
	 *  / \       \
	 * 10  5       9
	 * 
	 * @return root
	 */
	private TreeNode createTree() {
		TreeNode root = new TreeNode(15);

		TreeNode left1 = new TreeNode(4);
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
	 * <br>
	 * 1 <br>
	 * / \ <br>
	 * 0 1 <br>
	 * / \ / \ <br>
	 * 0 1 0 1
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
