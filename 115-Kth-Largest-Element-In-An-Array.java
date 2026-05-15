// https://leetcode.com/problems/kth-largest-element-in-an-array/


// given: an array of integers and an integer k
// required: return the kth largest element from the array

// constraints
// k, length of the array in [1, 100k]
// each value in [-10k, 10k]

// Max heap
// tc: O(nlogn + nlogk), sc: O(n)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for(int num: nums) {
            pq.offer(num);
        }

        for(int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        return pq.poll();
    }
}




// Min heap
// tc: O(nlogk), sc: O(k)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for(int num: nums) {
            pq.offer(num);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        
        return pq.poll();
    }
}