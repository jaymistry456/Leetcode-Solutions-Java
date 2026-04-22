// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/


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

// given: an array of integers
// required: convert the array to a height-balanced binary tree and return it

// constraints
// length of the array in [1, 10k]
// each value in [-10k, 10k]
// the array is in strictly increasing order

// tc: O(n^2), sc: O(n*height)
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) {
            return null;
        }

        int n = nums.length;
        int middle = n / 2;

        TreeNode root = new TreeNode(nums[middle]);

        int[] firstHalf = Arrays.copyOfRange(nums, 0, middle);
        int[] secondHalf = Arrays.copyOfRange(nums, middle + 1, n);

        root.left = sortedArrayToBST(firstHalf);
        root.right = sortedArrayToBST(secondHalf);

        return root;
    }
}




// DFS
// tc: O(n), sc: O(height)
class Solution {
    public TreeNode dfs(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }

        int middle = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = dfs(nums, start, middle - 1);
        root.right = dfs(nums, middle + 1, end);

        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
}