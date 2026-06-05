// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

// given: a binary tree
// required: point next pointer to the right node of it for each node

// constraints
// no. of nodes in the tree in [0, 6000]
// each value in [-100, 100]

// tc: O(n), sc: O(n)
class Solution {
    public Node connect(Node root) {
        if(root == null) return root;

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;       
                 
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if(prev != null) {
                    prev.next = curr;
                }
                prev = curr;

                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }

        return root;
    }
}