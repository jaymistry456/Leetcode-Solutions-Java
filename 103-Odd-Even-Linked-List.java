// https://leetcode.com/problems/odd-even-linked-list/


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
// required: group all nodes with odd indices together, same with even indices and return the new linke list

// constraints
// number of nodes in [0, 10k]
// each value in [-10^6, 10^6]

// tc: O(n), sc: O(1)
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode odd = dummy.next;
        ListNode even = dummy.next.next;
        ListNode evenHead = even;

        while(even != null && even.next != null) {
            ListNode third = even.next;
            ListNode fourth = even.next.next;

            odd.next = third;
            even.next = fourth;

            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return dummy.next;
    }
}