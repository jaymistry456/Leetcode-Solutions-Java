// https://leetcode.com/problems/copy-list-with-random-pointer/


/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// given: a modified linked list with a random pointer in addition to next pointer
// required: a deep copy of the linked list

// constraints
// length of linked list in [0, 1000]
// each node's value in [-10k, 10k]
// random can either point to any of the nodes in the linked list or to null

// tc: O(n), sc: O(n)
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();   // original -> copy

        // Create the map of original -> copy nodes
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Set the next and random pointers of copy nodes from original nodes using the map
        for (Map.Entry<Node, Node> entry: map.entrySet()) {
            Node original = entry.getKey();
            Node copy = entry.getValue();

            copy.next = map.getOrDefault(original.next, null);
            copy.random = map.getOrDefault(original.random, null);
        }

        return map.getOrDefault(head, null);
    }
}