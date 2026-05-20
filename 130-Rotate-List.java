// https://leetcode.com/problems/rotate-list/


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

// given: head of a linked list and an integer k
// required: rotate the linked list by k places and return the head of the rotated list

// constraints
// no. of nodes in [0, 500]
// each node value in [-100, 100]
// k in [0, 2 * 10^9]

// tc: O(n), sc: O(1)
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) {
            return head;
        }

        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length++;
            curr = curr.next;
        }

        ListNode left = head;
        ListNode right = head;

        for(int i = 0; i < k % length; i++) {
            right = right.next;
        }

        while(right.next != null) {
            left = left.next;
            right = right.next;
        }

        if(left.next != null) {
            ListNode newHead = left.next;
            left.next = null;
            right.next = head;
            return newHead;
        }

        return head;
    }
}