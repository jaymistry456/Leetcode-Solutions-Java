// https://leetcode.com/problems/balanced-binary-tree/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// given: a binary tree
// required: check whether it is height-balanced

// constraints
// no. of nodes in [0, 5000]
// each value in [-10k, 10k]

// tc: O(n^2), sc: O(height)
class Solution {
    public int height(TreeNode curr) {
        if(curr == null) {
            return 0;
        }
        return 1 + Math.max(height(curr.left), height(curr.right));
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if(Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }
}



// tc: O(n), sc: O(height)
class Solution {
    boolean balanced = true;

    public int dfs(TreeNode curr) {
        if(curr == null) {
            return 0;
        }

        int leftHeight = dfs(curr.left);
        int rightHeight = dfs(curr.right);
        if(Math.abs(leftHeight - rightHeight) > 1) {
            balanced = false;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean isBalanced(TreeNode root) {
        dfs(root);

        return balanced;
    }
}