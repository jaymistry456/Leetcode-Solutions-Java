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

// given: a binary search tree root and 2 nodes p and q
// required: return the lowest common ancestor of p and q in the tree

// constraints
// no. of nodes in [2, 100k]
// each value in [-10^9, 10^9]
// all values are unique
// p != q and both exist in the tree

// recursive
// tc: O(n), sc: O(height)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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




// iterative
// tc: O(n), sc: O(1)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while(true) {
            if(curr.val < p.val && curr.val < q.val) {
                curr = curr.right;
            }
            else if(curr.val > p.val && curr.val > q.val) {
                curr = curr.left;
            }
            else {
                return curr;
            }
        }
    }
}