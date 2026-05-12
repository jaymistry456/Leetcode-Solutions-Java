// https://leetcode.com/problems/swap-nodes-in-pairs/


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

// given: a linked list
// required: swap node in pairs and return the resulting linked list

// constraints
// number of nodes in [0, 100]
// each node's value in [0, 100]

// tc: O(n), sc: O(1)
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = dummy.next;

        while(curr != null && curr.next != null) {
            ListNode first = curr;
            ListNode second = curr.next;
            ListNode third = curr.next.next;

            second.next = first;
            prev.next = second;
            first.next = third;
            prev = first;
            curr = third;
        }

        return dummy.next;
    }
}