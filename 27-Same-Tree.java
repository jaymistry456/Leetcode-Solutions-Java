// https://leetcode.com/problems/same-tree/


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

// given: two binary trees p and q
// required: check whether they are the same tree or not

// constraints
// number of nodes in the trees in [0, 100]
// each value in [-10k, 10k]

// DFS
// tc: O(n), sc: O(height)
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        else if(p == null || q == null) {
            return false;
        }
        else {
            return (
                p.val == q.val && 
                isSameTree(p.left, q.left) && 
                isSameTree(p.right, q.right)
            );
        }
    }
}



// BFS
// tc: O(n), sc: O(n)
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        else if(p == null || q == null) {
            return false;
        }
        else {
            Deque<TreeNode[]> queue = new ArrayDeque<>();   // [p, q]
            queue.offer(new TreeNode[]{p, q});

            while(!queue.isEmpty()) {
                TreeNode[] pair = queue.poll();
                TreeNode first = pair[0];
                TreeNode second = pair[1];

                if(first == null && second == null) {
                    continue;
                }
                else if(first == null || second == null) {
                    return false;
                }
                else {
                    if(first.val != second.val) {
                        return false;
                    }
                    queue.offer(new TreeNode[]{first.left, second.left});
                    queue.offer(new TreeNode[]{first.right, second.right});
                }
            }
        }

        return true;
    }
}