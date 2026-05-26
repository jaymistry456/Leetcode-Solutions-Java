// https://leetcode.com/problems/maximum-width-of-binary-tree/


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


// tc: O(n), sc: O(n)
record NodeInfo(TreeNode node, long nodeNumber) {}

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }

        Deque<NodeInfo> queue = new ArrayDeque<>();
        queue.offer(new NodeInfo(root, 1));
        long result = 0;

        while(!queue.isEmpty()) {
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                NodeInfo item = queue.poll();
                
                min = Math.min(min, item.nodeNumber());
                max = Math.max(max, item.nodeNumber());

                if(item.node().left != null) {
                    queue.offer(new NodeInfo(item.node().left, 2 * item.nodeNumber()));
                }
                if(item.node().right != null) {
                    queue.offer(new NodeInfo(item.node().right, 2 * item.nodeNumber() + 1));
                }
            }

            result = Math.max(result, max - min + 1);
        }

        return (int) result;
    }
}