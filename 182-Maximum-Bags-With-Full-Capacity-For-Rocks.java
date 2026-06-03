// https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/


// given: two arrays capacity and rocks and an integer addtionalRocks
// required: max no. of bags that could have have full capacity after placing additional rocks in any of the bags that are lacking the required number

// constraints
// length of the arrays in [1, 50k]
// each capacity value in [1, 10^9]
// each rock value <= capacity value
// additionalRocks in [1, 10^9]

// We want to sort the bags in such a way that the bags with min diff are first
// tc: O(nlogn), sc: O(n)
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int result = 0;
        
        int[] diff = new int[capacity.length];
        for(int i = 0; i < capacity.length; i++) {
            diff[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(diff);

        for(int i = 0; i < diff.length; i++) {
            additionalRocks -= diff[i];

            if(additionalRocks < 0) return result;

            result++;
        }

        return result;
    }
}




// tc: O(nlogk), sc: O(k) where k is the no of bags with diff > 0
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a, b)
        );

        for(int i = 0; i < capacity.length; i++) {
            int currDiff = capacity[i] - rocks[i];
            
            if(currDiff == 0) {
                result++;
                continue;
            }

            pq.offer(currDiff);
        }

        while(!pq.isEmpty()) {
            additionalRocks -= pq.poll();
            
            if(additionalRocks < 0) return result;

            result++;
        }

        return result;
    }
}