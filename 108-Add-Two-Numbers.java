// https://leetcode.com/problems/add-two-numbers/


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

// given: two linked lists
// required: add them and return the new list

// constraints
// number of nodes in [1, 100]
// each node's value in [0, 9]

// tc: O(n), sc: O(1)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        int carry = 0;
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        while(l1 != null || l2 != null) {
            if(l1 != null && l2 != null) {
                sum = l1.val + l2.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                curr.next = new ListNode(sum);

                l1 = l1.next;
                l2 = l2.next;
                curr = curr.next;
            } else if (l1 != null) {
                sum = l1.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                curr.next = new ListNode(sum);

                l1 = l1.next;
                curr = curr.next;
            } else {
                sum = l2.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                curr.next = new ListNode(sum);

                l2 = l2.next;
                curr = curr.next;
            }
        }

        if (carry != 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }
}