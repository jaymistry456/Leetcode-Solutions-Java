// https://leetcode.com/problems/kth-smallest-element-in-a-bst/


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

// given: a binary search tree and an integer k
// required: return the kth smallest value (1-indexed) from the binary search tree

// constraints
// k, n in [1, 10k]
// each node's value in [0, 10k]

// tc: O(n), sc: O(n)
class Solution {
    List<Integer> nodeValues = new ArrayList<>();

    public void inorder(TreeNode curr) {
        if(curr == null) {
            return;
        }

        inorder(curr.left);
        nodeValues.add(curr.val);
        inorder(curr.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        inorder(root);

        return nodeValues.get(k - 1);
    }
}




// Recursive
// tc: O(k), sc: O(height)
class Solution {
    int result = -1;
    int count;

    public void inorder(TreeNode curr) {
        if(curr == null) {
            return;
        }

        inorder(curr.left);
        count--;
        if(count == 0) {
            result = curr.val;
            return;
        }
        inorder(curr.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inorder(root);

        return result;
    }
}




// Iterative
// tc: O(k), sc: O(height)
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            k--;
            if(k == 0) {
                return curr.val;
            }

            curr = curr.right;
        }

        return -1;
    }
}