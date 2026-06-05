// https://leetcode.com/problems/path-sum/


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

// given: a binary tree and a targetSum
// required: check whether any root-to-leaf paths in the binary tree sum upto targetSum

// constraints
// no. of nodes in the tree in [0, 5000]
// each value in [-1000, 1000]
// targetSum in [-1000, 1000]

// tc: O(n), sc: O(height)
class Solution {
    public boolean dfs(TreeNode curr, int targetSum, int currSum) {
        if(curr == null) {
            return false;
        }

        currSum += curr.val;
        if(curr.left == null && curr.right == null) {
            return currSum == targetSum;
        }
        
        return (
            dfs(curr.left, targetSum, currSum) || 
            dfs(curr.right, targetSum, currSum)
        );
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum, 0);
    }
}