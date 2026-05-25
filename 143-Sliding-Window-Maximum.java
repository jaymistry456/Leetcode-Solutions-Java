// https://leetcode.com/problems/sliding-window-maximum/


// given: an array of numbers and an integer k
// required: return an array of maximum number in each sliding window of size k

// constraints
// length of the array in [1, 100k]
// each number in [-10k, 10k]
// k in [1, nums.length]

// tc: O(n*k), sc: O(1)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for(int i = 0; i < n - k + 1; i++) {
            int currMax = Integer.MIN_VALUE;
            for(int j = i; j < i + k; j++) {
                currMax = Math.max(currMax, nums[j]);
            }

            result[i] = currMax;
        }

        return result;
    }
}




// tc: O(n), sc: O(k)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        // Queue keeps the currMax value idx on the front
        // Any time a new value is inserted, it pops all the smaller values than itself
        // This means we keep max -> min values from front -> rear
        Deque<Integer> queue = new ArrayDeque<>();
        
        int left = 0;
        int right = 0;
        while(right < n) {
            // 1. Pop all the smaller values
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[right]) {
                queue.pollLast();
            }

            // 2. Insert the new value idx
            queue.offerLast(right);

            // 3. Check whether we are atleast k values in
            if(right >= k - 1) {
                result[left] = nums[queue.peekFirst()];
                left++;
            }

            // 4. Check the window size
            if(left > queue.peekFirst()) {
                queue.pollFirst();
            }


            // 5. Update the right pointer
            right++;
        }

        return result;
    }
}