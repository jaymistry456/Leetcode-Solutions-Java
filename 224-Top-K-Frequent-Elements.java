// https://leetcode.com/problems/top-k-frequent-elements/


// given: an array of integers and an integer k
// required: return the k most frequent elements in any order

// constraints
// length of the array in [1, 100k]
// each value in [-10k, 10k]

// tc: O(nlogn), sc: O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();   // num -> freq of num
        for(int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[1], a[1])
        );   // [num, freqOfNum] sorted by freqOfNum desc (Max-heap)
        
        for(Map.Entry<Integer, Integer> entry: freq.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }

        return result;
    }
}




// tc: O(nlogk), sc: O(k)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();   // num -> freq of num
        for(int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );   // [num, freqOfNum] sorted by freqOfNum asc (Min-heap)
        
        for(Map.Entry<Integer, Integer> entry: freq.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});

            if(pq.size() > k) pq.poll();
        }

        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }

        return result;
    }
}