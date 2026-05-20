// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// given: root of a binary tree, a target node target and an integer k
// required: return an array of node values which are at a distance of k from the target node

// constraints
// no. of nodes in [1, 500]
// each node value in [0, 500]
// all values are unique
// target exists as one of the node values
// k in [0, 1000]

// DFS
// tc: O(n), sc: O(n)
class Solution {
    Map<TreeNode, TreeNode> map = new HashMap<>();   // node -> its parent
    List<Integer> result = new ArrayList<>();

    public void createParentEdge(TreeNode curr, TreeNode parent) {
        if(curr == null) {
            return;
        }

        if(parent != null) {
            map.put(curr, parent);
        }

        createParentEdge(curr.left, curr);
        createParentEdge(curr.right, curr);
    }

    public void dfs(TreeNode curr, TreeNode prev, int k) {
        if(curr == null) {
            return;
        }

        if(k == 0) {
            result.add(curr.val);
            return;
        }

        // Left child
        if(curr.left != prev) {
            dfs(curr.left, curr, k - 1);
        }

        // Right child
        if(curr.right != prev) {
            dfs(curr.right, curr, k - 1);
        }

        // Parent node
        if(map.containsKey(curr) && map.get(curr) != prev) {
            dfs(map.get(curr), curr, k - 1);
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        createParentEdge(root, null);

        dfs(target, null, k);

        return result;
    }
}




// BFS
// tc: O(n), sc: O(n)
record QueueItem(TreeNode curr, TreeNode prev, int k) {}

class Solution {
    Map<TreeNode, TreeNode> map = new HashMap<>();   // node -> its parent

    public void createParentEdge(TreeNode curr, TreeNode parent) {
        if(curr == null) {
            return;
        }

        if(parent != null) {
            map.put(curr, parent);
        }

        createParentEdge(curr.left, curr);
        createParentEdge(curr.right, curr);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        createParentEdge(root, null);

        List<Integer> result = new ArrayList<>();

        Deque<QueueItem> queue = new ArrayDeque<>();
        queue.offer(new QueueItem(target, null, k));

        while(!queue.isEmpty()) {
            QueueItem item = queue.poll();
            TreeNode curr = item.curr();
            TreeNode prev = item.prev();
            int kLeft = item.k();

            if(kLeft == 0) {
                result.add(curr.val);
                continue;
            }

            if(curr.left != null && curr.left != prev) {
                queue.offer(new QueueItem(curr.left, curr, kLeft - 1));
            }
            if(curr.right != null && curr.right != prev) {
                queue.offer(new QueueItem(curr.right, curr, kLeft - 1));
            }
            if(map.containsKey(curr) && map.get(curr) != prev) {
                queue.offer(new QueueItem(map.get(curr), curr, kLeft - 1));
            }
        }

        return result;
    }
}