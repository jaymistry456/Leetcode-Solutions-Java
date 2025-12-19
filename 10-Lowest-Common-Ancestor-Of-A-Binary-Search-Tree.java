// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // // iterative
        // // TC: O(height), SC(1)
        // TreeNode curr = root;
        // while(curr != null) {
        //     if(curr.val < p.val && curr.val < q.val) {
        //         curr = curr.right;
        //     }
        //     else if(curr.val > p.val && curr.val > q.val) {
        //         curr = curr.left;
        //     }
        //     else {
        //         return curr;
        //     }
        // }
        // return null;


        // recursive
        // TC: O(height), SC: O(height)
        if(root == null) {
            return null;
        }
        if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        else {
            return root;
        }
    }
}