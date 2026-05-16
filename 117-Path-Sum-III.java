// https://leetcode.com/problems/path-sum-iii/


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

// given: a binary tree and an targetSum
// required: return the no. of paths where the sum of the values along the path equals targetSum

// constraints
// no. of nodes in [0, 1000]
// each node's val in [-10^9, 10^9]
// targetSum in [-1000, 1000]

// tc: O(n^2), sc: O(n)
class Solution {
    public int dfs(TreeNode currNode, int targetSum, long currSum) {
        if(currNode == null) {
            return 0;
        }

        int result = 0;
        currSum += currNode.val;
        if(currSum == targetSum) {
            result++;
        }

        result += dfs(currNode.left, targetSum, currSum);
        result += dfs(currNode.right, targetSum, currSum);

        return result;
    }

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }

        return dfs(root, targetSum, 0) + 
                pathSum(root.left, targetSum) + 
                pathSum(root.right, targetSum);
    }
}




// tc: O(n), sc: O(n)
class Solution {
    Map<Long, Integer> map = new HashMap<>();   // currSum -> how many times it occurs

    public int dfs(TreeNode currNode, int targetSum, long currSum) {
        if(currNode == null) {
            return 0;
        }

        int result = 0;

        currSum += currNode.val;
        result += map.getOrDefault(currSum - targetSum, 0);
        
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        result += dfs(currNode.left, targetSum, currSum);
        result += dfs(currNode.right, targetSum, currSum);

        map.put(currSum, map.get(currSum) - 1);

        return result;
    }

    public int pathSum(TreeNode root, int targetSum) {
        map.put(0L, 1);

        return dfs(root, targetSum, 0);
    }
}