// https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/


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
// required: return the sum of all the nodes with a grandparent whose value is even

// constraints
// no. of nodes in [1, 10k]
// each value in [1, 100]

// tc: O(n), sc: O(height)
class Solution {
    public int dfs(TreeNode curr, TreeNode parent, TreeNode grandParent) {
        if(curr == null) {
            return 0;
        }

        int result = 0;
        if(grandParent != null && grandParent.val % 2 == 0) {
            result += curr.val;
        }

        result += dfs(curr.left, curr, parent);
        result += dfs(curr.right, curr, parent);

        return result;
    }

    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, null, null);
    }
}