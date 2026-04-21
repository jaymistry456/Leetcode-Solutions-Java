// https://leetcode.com/problems/maximum-depth-of-binary-tree/


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
// required: return the maximum depth of the binary tree (the number of nodes along the longest path from root to leaf)

// constraints
// number of nodes in the tree in [0, 10k]
// each value in [-100, 100]

// DFS
// tc: O(n), sc: O(height)
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}




// BFS
// tc: O(n), sc: O(n)
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }

            result++;
        }

        return result;
    }
}