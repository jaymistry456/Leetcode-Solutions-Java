// https://leetcode.com/problems/last-stone-weight/


// given: an array of stones
// required: keep smashing the 2 heaviest stones in the array until either all stones are destroyed (0) or only 1 stone remains and return last stone

// constraints
// length of stones in [1, 30]
// each value in [1, 1000]

// tc: O(nlogn), sc: O(n)
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );   // Max-heap

        for(int stone: stones) {
            pq.offer(stone);
        }

        while(pq.size() > 1) {
            int largest = pq.poll();
            int secondLargest = pq.poll();

            if(largest != secondLargest) {
                pq.offer(largest - secondLargest);
            }
        }

        return pq.isEmpty() ? 0: pq.poll();
    }
}