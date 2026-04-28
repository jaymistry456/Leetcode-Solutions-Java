// https://leetcode.com/problems/validate-binary-search-tree/


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

// given: a binary search tree
// required: validate the tree

// constraints
// no. of nodes in the tree in [1, 10k]
// each node val in [-2^31, 2^31 - 1]

// tc: O(n), sc: O(height)
class Solution {
    public boolean dfs(TreeNode node, long minVal, long maxVal) {
        if(node == null) {
            return true;
        }

        return (
            node.val > minVal && 
            node.val < maxVal &&
            dfs(node.left, minVal, node.val) &&
            dfs(node.right, node.val, maxVal)
        );
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);   
    }
}