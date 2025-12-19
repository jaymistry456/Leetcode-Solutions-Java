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
class Solution {
    // public int getHeight(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }
    //     return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    // }


    public int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftHeight = dfs(root.left);
        int rightHeight = dfs(root.right);
        if(leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        if(Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean isBalanced(TreeNode root) {
        // // brute-force
        // // TC: O(n^2), SC: O(height)
        // if(root == null) {
        //     return true;
        // }
        // int leftHeight = getHeight(root.left);
        // int rightHeight = getHeight(root.right);
        // if(Math.abs(leftHeight - rightHeight) > 1) {
        //     return false;
        // }
        // return isBalanced(root.left) && isBalanced(root.right);


        // optimal
        // TC: O(n), SC: O(height)
        return dfs(root) != -1;
    }
}