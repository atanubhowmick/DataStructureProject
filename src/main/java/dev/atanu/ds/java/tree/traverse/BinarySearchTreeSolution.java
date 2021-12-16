/**
 * 
 */
package dev.atanu.ds.java.tree.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Atanu Bhowmick
 *
 */
public class BinarySearchTreeSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTreeSolution solution = new BinarySearchTreeSolution();
		TreeNode root = solution.createTree();
		//System.out.println(solution.isValidBST(root));
		//System.out.println(solution.isValidBSTUsingRecurssion(root));
		//System.out.println("Count of BTSs : " + solution.numOfBST(6));
		//solution.generateTrees(4);
		TreeNode node = solution.sortedArrayToBST(new int[] {4, 8, 12, 15, 20, 25, 30});
		//System.out.println("Is balanced tree : " + solution.isBalanced(node));
		//solution.invertTree(node);
		solution.bstFromPreorder(new int[] {8, 5, 1, 7, 10, 12});
	}
	
	/**
	 * {@link https://leetcode.com/problems/validate-binary-search-tree/}
	 * 
	 * @param root
	 * @return isValid
	 */
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;

		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;

		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				if (pre != null && root.val <= pre.val)
					return false;
				pre = root;
				root = root.right;
			}
		}
		return true;
	}
	
	private TreeNode pre = null;
	
	/**
	 * {@link https://leetcode.com/problems/validate-binary-search-tree/}
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBSTUsingRecurssion(TreeNode root) {
        if(root == null) {
            return true;
        }
        
        // Inorder traverse
        if(!isValidBST(root.left))
            return false;
        
        if(pre != null && pre.val >= root.val) {
            return false;
        }
        
        pre = root;
        
        if(!isValidBST(root.right))
            return false;
        
        return true;
    }
	
	/**
	 * {@link https://leetcode.com/problems/unique-binary-search-trees/}
	 * @param n
	 * @return count
	 */
	public int numOfBST(int n) {
		// Using Catalan number
		// https://en.wikipedia.org/wiki/Catalan_number
		// https://stackoverflow.com/questions/3042412/with-n-no-of-nodes-how-many-different-binary-and-binary-search-trees-possib
		long count = 1;

		for (int i = 0; i < n; ++i)
			count = count * 2 * (2 * i + 1) / (i + 2);
		return (int) count;
	}
	
	/**
	 * {@link https://leetcode.com/problems/unique-binary-search-trees-ii/}
	 * 
	 * @param n
	 * @return list
	 */
	public List<TreeNode> generateTrees(int n) {
	    return genTreeList(1,n);
	}

	private List<TreeNode> genTreeList (int start, int end) {
	    List<TreeNode> list = new ArrayList<>(); 
	    if (start > end) {
	        list.add(null);
	    }
	    for(int idx = start; idx <= end; idx++) {
	        List<TreeNode> leftList = genTreeList(start, idx - 1);
	        List<TreeNode> rightList = genTreeList(idx + 1, end);
	        for (TreeNode left : leftList) {
	            for(TreeNode right: rightList) {
	                TreeNode root = new TreeNode(idx);
	                root.left = left;
	                root.right = right;
	                list.add(root);
	            }
	        }
	    }
	    return list;
	}
	
	/**
	 * {@link https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/}
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if(start > end)
            return null;
        int mid = (start + end) >>> 1;
        TreeNode node = new TreeNode(nums[mid]);
        TreeNode left = sortedArrayToBST(nums, start, mid - 1);
        node.left = left;
        TreeNode right = sortedArrayToBST(nums, mid + 1, end);
        node.right = right;
        return node;
    }
    
    /**
     * This is bottom up approach. First look into the leaf nodes set their levels.
     * Then go up and check the level of left and right
     * 
     * {@link https://leetcode.com/problems/balanced-binary-tree/}
     * @param root
     * @return
     */
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return heightBalanced(root) != -1;
	}

	private int heightBalanced(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int left = heightBalanced(node.left);
		if (left == -1) {
			return -1;
		}
		
		int right = heightBalanced(node.right);
		if (right == -1) {
			return -1;
		}
		
		if (Math.abs(left - right) > 1) {
			return -1;
		}
		
		return Math.max(left, right) + 1;
	}
	
	/**
	 * {@link https://leetcode.com/problems/invert-binary-tree/}
	 * 
	 * @param root
	 * @return Inverted Tree
	 */
	public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
	
	/**
	 * {@link https://leetcode.com/problems/binary-tree-right-side-view/}
	 * 
	 * @param root
	 * @return list
	 */
	public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        
        LinkedList<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()) {
            int size = que.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if(node.left != null) {
                    que.offer(node.left);
                }
                if(node.right != null) {
                    que.offer(node.right);
                }
                if(i == size - 1) {
                    list.add(node.val);
                }
            }
        }
        
        return list;
    }
	
	/**
	 * {@link https://leetcode.com/problems/binary-tree-right-side-view/}
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideViewUsingStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }
    
    private void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
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
        
        test(root, targetSum);
        return count;
    }
    
    private int test(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        int sum = 0;
        sum += root.val;
        sum += test(root.left, targetSum);
        if(sum == targetSum) {
            count++;
        }
        sum += test(root.right, targetSum);
        if(sum == targetSum) {
            count++;
        }
        return sum;
    }
    
    /**
     * {@link https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/}
     * 
     * @param arr
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, Integer.MAX_VALUE, new int[]{0});
    }

    private TreeNode bstFromPreorder(int[] preorder, int bound, int[] arr) {
		if (arr[0] == preorder.length || preorder[arr[0]] > bound) {
			return null;
		}
        	
        TreeNode root = new TreeNode(preorder[arr[0]++]);
        root.left = bstFromPreorder(preorder, root.val, arr);
        root.right = bstFromPreorder(preorder, bound, arr);
        return root;
    }
    
    /**
     * {@link https://leetcode.com/problems/search-in-a-binary-search-tree/}
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val) 
            return root;
        return val < root.val ? searchBST(root.left, val) : 
                    searchBST(root.right, val);
    }


	/**
	 * The tree is like this
	 * 
	 * <br>          20
	 * <br>         / \
	 * <br>        10  30
	 * <br>       /   / \
	 * <br>      5   25  35
	 * <br>     / \       \
	 * <br>    3   8      40
	 *     
	 * @return root
	 */
	private TreeNode createTree() {
		TreeNode root = new TreeNode(20);
		
		TreeNode left1 = new TreeNode(10);
		TreeNode right1 = new TreeNode(30);
		root.setLeft(left1);
		root.setRight(right1);
		
		TreeNode left2 = new TreeNode(5);
		left1.setLeft(left2);
		
		TreeNode left3 = new TreeNode(3);
		TreeNode right3 = new TreeNode(8);
		left2.setLeft(left3);
		left2.setRight(right3);
		
		TreeNode left4 = new TreeNode(25);
		TreeNode right4 = new TreeNode(35);
		right1.setLeft(left4);
		right1.setRight(right4);
		
		TreeNode right5 = new TreeNode(40);
		right4.setRight(right5);
		
		return root;
	}
}
