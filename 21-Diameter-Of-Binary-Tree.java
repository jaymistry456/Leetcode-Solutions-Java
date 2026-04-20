// https://leetcode.com/problems/diameter-of-binary-tree/


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
// required: return the diameter of the binary tree (number of edges between any two nodes in the tree)

// constraints
// number of nodes in the tree in [1, 10k]
// each value in [-100, 100]

// tc: O(n^2), sc: O(height)
class Solution {
    public int height(TreeNode node) {
        if(node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int getDiameter(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int leftRes = getDiameter(node.left);
        int rightRes = getDiameter(node.right);

        return Math.max(
            leftHeight + rightHeight,
            Math.max(
                leftRes,
                rightRes
            )
        );
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return getDiameter(root);
    }
}




// tc: O(n), sc: O(height)
class Solution {
    int diameter = 0;

    public int dfs(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);

        diameter = Math.max(diameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);

        return diameter;
    }
}