// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/


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
// required: flatten the binary tree into a linked list based on pre-order traversal of the tree, here the binary tree still remains, but we make the right child as the next node and make the left child null

// constraints
// no. of nodes in the tree in [0, 2000]
// each value in [-100, 100]

// tc: O(n), sc: O(height)
class Solution {
    // Returns the tail of the left subtree
    public TreeNode dfs(TreeNode curr) {
        if(curr == null) return null;

        // 1. Perform dfs on the left subtree and get the tail of it
        TreeNode leftTail = dfs(curr.left);

        // 2. Perform dfs on the right subtree and get the tail of it
        TreeNode rightTail = dfs(curr.right);

        // 3. Make leftTail's right point to the right subtree root, make curr's right pointer point to the left subtree root, make curr's left pointer null
        if(curr.left != null) {
            leftTail.right = curr.right;
            curr.right = curr.left;
            curr.left = null;
        }

        // 4. Return the right tail or the left tail or the root (in that exact order)
        if(rightTail != null) return rightTail;
        if(leftTail != null) return leftTail;
        return curr;
    }

    public void flatten(TreeNode root) {
        dfs(root);
    }
}