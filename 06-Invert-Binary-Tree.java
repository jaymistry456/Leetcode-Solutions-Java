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

// given: a binary tree
// required: invert the binary tree and return it

// constraints
// number of nodes in [0, 100]
// each value in [-100, 100]

// DFS
// tc: O(n), sc: O(height)
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}



// BFS
// tc: O(n), sc: O(n)
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return root;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if(curr.left != null) {
                queue.offer(curr.left);
            }
            if(curr.right != null) {
                queue.offer(curr.right);
            }
        }

        return root;
    }
}