// https://leetcode.com/problems/minimum-absolute-difference-in-bst/


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
// required: the minimum diff between any 2 nodes in the binary tree

// constraints
// no. of nodes in [2, 10k]
// each value in [0, 100k]

// tc: O(n), sc: O(height)
class Solution {
    public long dfs(TreeNode curr, long min, long max) {
        if(curr == null) {
            return max - min;
        }

        return Math.min(
            dfs(curr.left, min, curr.val),
            dfs(curr.right, curr.val, max)
        );
    }

    public int getMinimumDifference(TreeNode root) {
        return (int) dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}