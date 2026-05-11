// https://leetcode.com/problems/remove-nth-node-from-end-of-list/


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

// given: a linked list and an integer n
// required: remove nth node from the end of the list

// constraints
// number of nodes in [1, 30]
// each node's value in [0, 100]

// Two-pass
// tc: O(n), sc: O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;
        ListNode dummy = new ListNode(0, head);

        while(curr != null) {
            length++;
            curr = curr.next;
        }

        curr = dummy;
        for(int i = 0; i < length - n; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;

        return dummy.next;
    }
}



// One-pass
// tc: O(n), sc: O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;

        for(int i = 0; i < n; i++) {
            right = right.next;
        }

        while(right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;

        return dummy.next;
    }
}