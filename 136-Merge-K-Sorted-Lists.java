// https://leetcode.com/problems/merge-k-sorted-lists/


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
 
// given: an array of k linked list, each sorted in ascending order
// required: merge all the linked lists into one sorted list

// constraints
// k in [0, 10k]
// length of each linked list in [0, 500]
// each node value in [-10k, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        while(l1 != null || l2 != null) {
            if(l1 != null && l2 != null) {
                if(l1.val <= l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                }
                else {
                    curr.next = l2;
                    l2 = l2.next;
                }
                curr = curr.next;
            }
            else if(l1 != null) {
                curr.next = l1;
                break;
            }
            else {
                curr.next = l2;
                break;
            }
        }

        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }
        
        ListNode result = lists[0];
        for(int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }

        return result;
    }
}




// tc: O(nlogn), sc: O(n)
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        while(l1 != null || l2 != null) {
            if(l1 != null && l2 != null) {
                if(l1.val <= l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                }
                else {
                    curr.next = l2;
                    l2 = l2.next;
                }
                curr = curr.next;
            }
            else if(l1 != null) {
                curr.next = l1;
                break;
            }
            else {
                curr.next = l2;
                break;
            }
        }

        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }

        List<ListNode> mergedLists = Arrays.asList(lists);
        while(mergedLists.size() > 1) {
            List<ListNode> temp = new ArrayList<>();

            for(int i = 0; i < mergedLists.size(); i = i + 2) {
                ListNode l1 = mergedLists.get(i);
                ListNode l2 = i + 1 < mergedLists.size() ? mergedLists.get(i + 1) : null;

                temp.add(mergeTwoLists(l1, l2));
            }

            mergedLists = temp;
        }

        return mergedLists.get(0);
    }
}