// https://leetcode.com/problems/subtree-of-another-tree/


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

// given: two binary trees
// required: check whether subRoot structure exists in root

// constraints
// no. of nodes in root in [1, 2000]
// no. of nodes in subRoot in [1, 1000]
// each value in [-10k, 10k]

// tc: O(m*n), sc: O(m+n)
class Solution {
    public boolean isEqual(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        }
        else if(node1 == null || node2 == null) {
            return false;
        }
        else {
            return (
                node1.val == node2.val &&
                isEqual(node1.left, node2.left) &&
                isEqual(node1.right, node2.right)
            );
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) {
            return false;
        }

        if(isEqual(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}