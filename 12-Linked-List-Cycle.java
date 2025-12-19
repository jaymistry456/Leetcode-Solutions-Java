// https://leetcode.com/problems/linked-list-cycle/description/


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // // brute-force
        // // TC: O(n), SC: O(n)
        // ListNode curr = head;
        // Set<ListNode> set = new HashSet<>();
        // while(curr != null) {
        //     if(set.contains(curr)) {
        //         return true;
        //     }
        //     set.add(curr);
        //     curr = curr.next;
        // }
        // return false;


        // optimal
        // TC: O(n), SC: O(1)
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }
}