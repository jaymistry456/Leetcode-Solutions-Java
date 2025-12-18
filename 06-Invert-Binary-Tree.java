// https://leetcode.com/problems/invert-binary-tree/description/


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
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // // dfs
        // // TC: O(n), SC: O(height)
        // if(root == null) {
        //     return root;
        // }
        // TreeNode temp = root.left;
        // root.left = root.right;
        // root.right = temp;
        // invertTree(root.left);
        // invertTree(root.right);
        // return root;


        // bfs
        // TC: O(n), SC: O(n)
        if(root == null) {
            return root;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if(curr.left != null) {
                q.offer(curr.left);
            }
            if(curr.right != null) {
                q.offer(curr.right);
            }
        }
        return root;
    }
}