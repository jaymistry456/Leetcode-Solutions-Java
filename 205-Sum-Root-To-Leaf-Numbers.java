// https://leetcode.com/problems/sum-root-to-leaf-numbers/


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

// given: a binary tree where each node's value is in [0, 9]
// required: return sum of each root-to-leaf sums

// constraints
// no. of nodes in [1, 1000]
// each node's value in [0, 9]
// depth does not exceed 10

// tc: O(n), sc: O(height)
class Solution {
    int result = 0;

    public void dfs(TreeNode curr, int currSum) {
        if(curr == null) return;

        currSum = currSum * 10 + curr.val;
        if(curr.left == null && curr.right == null) {
            result += currSum;
            return;
        }
        dfs(curr.left, currSum);
        dfs(curr.right, currSum);
    }

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);

        return result;
    }
}