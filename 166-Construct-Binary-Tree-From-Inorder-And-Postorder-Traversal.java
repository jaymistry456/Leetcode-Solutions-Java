// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/


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

// given: inorder and postorder traversal arrays of a binary tree
// required: construct the binary tree from the traversals

// constraints
// length of each array in [1, 3000]
// each value in [-3000, 3000]

// tc: O(n^2), sc: O(n^2)
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        if(n == 0) return null;

        int rootVal = postorder[n - 1];
        int inorderIdx = -1;
        for(int i = 0; i < n; i++) {
            if(inorder[i] == rootVal) {
                inorderIdx = i;
                break;
            }
        }

        int[] inorderLeft = Arrays.copyOfRange(inorder, 0, inorderIdx);
        int[] inorderRight = Arrays.copyOfRange(inorder, inorderIdx + 1, n);
        int[] postorderLeft = Arrays.copyOfRange(postorder, 0, inorderIdx);
        int[] postorderRight = Arrays.copyOfRange(postorder, inorderIdx, n - 1);

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorderLeft, postorderLeft);
        root.right = buildTree(inorderRight, postorderRight);

        return root;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    int n;
    int postorderIdx;
    Map<Integer, Integer> map = new HashMap<>();   // val -> idx in inorder

    public TreeNode helper(int[] postorder, int left, int right) {
        if(left > right) {
            return null;
        }

        int rootVal = postorder[postorderIdx];
        postorderIdx--;
        int inorderIdx = map.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.right = helper(postorder, inorderIdx + 1, right);
        root.left = helper(postorder, left, inorderIdx - 1);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        n = inorder.length;
        if(n == 0) return null;

        for(int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        postorderIdx = n - 1;

        return helper(postorder, 0, n - 1);
    }
}