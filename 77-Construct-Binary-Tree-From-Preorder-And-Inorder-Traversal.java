// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/


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

// given: two arrays preorder and inorder
// required: construct a binary tree from these two arrays

// constraints
// length of both arrays are equal in range [1, 3000]
// each value in [-3000, 3000]
// both arrays are correct

// tc: O(n^2), sc: O(n^2)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) {
            return null;
        }

        int mid = -1;
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == preorder[0]) {
                mid = i;
                break;
            }
        }

        TreeNode root = new TreeNode(preorder[0]);

        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, mid + 1);
        int[] rightPreorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, mid);
        int[] rightInorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);

        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    int preorderIdx = 0;
    Map<Integer, Integer> inorderMap = new HashMap<>();   // val -> idx of val in inorder

    public TreeNode dfs(int[] preorder, int start, int end) {
        if(start > end) {
            return null;
        }

        int rootVal = preorder[preorderIdx];
        TreeNode root = new TreeNode(rootVal);
        preorderIdx++;

        int mid = inorderMap.get(rootVal);

        root.left = dfs(preorder, start, mid - 1);
        root.right = dfs(preorder, mid + 1, end);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return dfs(preorder, 0, inorder.length - 1);
    }
}