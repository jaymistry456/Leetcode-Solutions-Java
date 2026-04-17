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

// given: a linked list
// required: check if the linked list has a cycle

// constraints
// number of nodes in [0, 10k]
// each value in [-100k, 100k]

// tc: O(n), sc: O(n)
public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode curr = head;
        while(curr != null) {
            if(set.contains(curr)) {
                return true;
            }
            set.add(curr);
            curr = curr.next;
        }

        return false;
    }
}



// tc: O(n), sc: O(1)
public class Solution {
    public boolean hasCycle(ListNode head) {
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