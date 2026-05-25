// https://leetcode.com/problems/reverse-nodes-in-k-group/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// given: a linked list and an integer k
// required: reverse nodes k at a time starting from the root node and return the linked list

// constraints
// no. of nodes, k in [1, 5000]
// each node's value in [0, 1000]

// tc: O(n), sc: O(n/k)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length++;
            curr = curr.next;
        }

        if(length < k) {
            return head;
        }

        ListNode prev = null;
        curr = head;
        for(int i = 0; i < k; i++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        head.next = reverseKGroup(curr, k);

        return prev;
    }
}