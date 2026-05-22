// https://leetcode.com/problems/binary-tree-maximum-path-sum/


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
// required: return the max path sum

// no. of nodes in [1, 30k]
// each node value in [-1000, 1000]

// tc: O(n^2), sc: O(n)
class Solution {
    int result = Integer.MIN_VALUE;

    public int oneSideMaxSum(TreeNode curr) {
        if(curr == null) {
            return 0;
        }

        int leftMaxSum = Math.max(0, oneSideMaxSum(curr.left));
        int rightMaxSum = Math.max(0, oneSideMaxSum(curr.right));

        return Math.max(curr.val + leftMaxSum, curr.val + rightMaxSum);
    }

    public void dfs(TreeNode curr) {
        if(curr == null) {
            return;
        }

        int leftMaxSum = Math.max(0, oneSideMaxSum(curr.left));
        int rightMaxSum = Math.max(0, oneSideMaxSum(curr.right));

        result = Math.max(result, curr.val + leftMaxSum + rightMaxSum);

        dfs(curr.left);
        dfs(curr.right);
    }

    public int maxPathSum(TreeNode root) {
        dfs(root);

        return result;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    int result = Integer.MIN_VALUE;

    public int dfs(TreeNode curr) {
        if(curr == null) {
            return 0;
        }

        int leftMaxSum = Math.max(0, dfs(curr.left));
        int rightMaxSum = Math.max(0, dfs(curr.right));

        result = Math.max(result, curr.val + leftMaxSum + rightMaxSum);

        return Math.max(curr.val + leftMaxSum, curr.val + rightMaxSum);
    }

    public int maxPathSum(TreeNode root) {
        dfs(root);

        return result;
    }
}