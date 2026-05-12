// https://leetcode.com/problems/path-sum-ii/


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

// given: a binary tree and a targetSum
// required: return all root-to-leaf paths where the sum of the node values in the path equals targetSum

// constraints
// number of nodes in the tree in [0, 5000]
// each node's value in [-1000, 1000]
// targetSum in [-1000, 1000]
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currResult = new ArrayList<>();

    public void dfs(TreeNode curr, int targetSum, int currSum) {
        if(curr == null) {
            return;
        }

        currSum += curr.val;
        currResult.add(curr.val);

        if(curr.left == null && curr.right == null) {
            if(currSum == targetSum) {
                result.add(new ArrayList<>(currResult));
            }
        }
        else {
            dfs(curr.left, targetSum, currSum);
            dfs(curr.right, targetSum, currSum);
        }
        
        currResult.remove(currResult.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0);

        return result;
    }
}