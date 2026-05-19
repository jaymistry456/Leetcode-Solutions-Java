// https://leetcode.com/problems/reorder-list/


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
// required: reorder the list in a such a way that the second half is reversed and then the remaining 2 halves are interleaved

// constraints
// no. of nodes in [1, 50k]
// each value in [1, 1000]

// tc: O(n), sc: O(1)
class Solution {
    public void reorderList(ListNode head) {
        // 1. Find the mid point
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Split the list at mid point
        ListNode rightHalf = slow.next;
        slow.next = null;

        // 3. Reverse the second half
        ListNode prev = null;
        ListNode curr = rightHalf;
        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // 4. Combine both lists
        ListNode first = head;
        ListNode second = prev;
        while(first != null && second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
    }
}