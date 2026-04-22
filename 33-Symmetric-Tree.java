// https://leetcode.com/problems/symmetric-tree/


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
// required: check whether the left and right subtrees are mirrors of each other

// constraints
// number of nodes in the tree in [1, 1000]
// each value in [-100, 100]

// DFS (recursively)
// tc: O(n), sc: O(height)
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
                isEqual(node1.left, node2.right) &&
                isEqual(node1.right, node2.left)
            );
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }

        return isEqual(root.left, root.right);
    }
}




// BFS (iteratively)
// tc: O(n), sc: O(n)
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }

        Deque<TreeNode[]> queue = new ArrayDeque<>();   // [node1, node2]
        queue.offer(new TreeNode[]{root.left, root.right});

        while(!queue.isEmpty()) {
            TreeNode[] pair = queue.poll();
            TreeNode node1 = pair[0];
            TreeNode node2 = pair[1];

            if(node1 == null && node2 == null) {
                continue;
            }
            else if(node1 == null || node2 == null) {
                return false;
            }
            else {
                if(node1.val != node2.val) {
                    return false;
                }
                queue.offer(new TreeNode[]{node1.left, node2.right});
                queue.offer(new TreeNode[]{node1.right, node2.left});
            }
        }

        return true;
    }
}