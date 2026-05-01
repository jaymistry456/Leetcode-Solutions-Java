// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// given: a binary tree and two nodes p and q
// required: find the lowest common ancestor (node) of the two nodes in the binary tree

// constraints
// no. of nodes in range [2, 100k]
// each node value in [-10^9, 10^9]
// all values are unique
// p != q

// tc: O(n), sc: O(height)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) {
            // As we perform a dfs traversal, we go to the deepest node first 
            // where this condition holds true and return the LCA, 
            // this means we simply pass this answer upwards and this ensures 
            // we don't return any answer further up
            return root;
        }

        return left != null ? left : right;
    }
}