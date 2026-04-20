// https://leetcode.com/problems/middle-of-the-linked-list/


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
// required: return the middle of the linked list

// constraints
// number of nodes in [1, 100]
// each value in [1, 100]

// tc: O(n), sc: O(1)
class Solution {
    public ListNode middleNode(ListNode head) {
        int length = 0;
        ListNode curr = head;

        while(curr != null) {
            length++;
            curr = curr.next;
        }

        curr = head;
        for(int i = 0; i < length / 2; i++) {
            curr = curr.next;
        }

        return curr;
    }
}



// tc: O(n), sc: O(1)
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}