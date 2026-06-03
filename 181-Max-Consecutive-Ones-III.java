// https://leetcode.com/problems/max-consecutive-ones-iii/


// given: an array of integers and an integer k
// required: max no. of consecutive 1s if we can flip atmost k bits to 1

// constraints
// length of the array in [1, 100k]
// each value is either 0 or 1
// k in [0, length of the array]

// tc: O(n^2), sc: O(1)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int result = 0;

        for(int i = 0; i < nums.length; i++) {
            int currK = 0;
            for(int j = i; j < nums.length; j++) {
                if(nums[j] != 1) {
                    currK++;
                    if(currK > k) {
                        break;
                    }
                }
                result = Math.max(result, j - i + 1);
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int result = 0;

        int left = 0;
        int right = 0;
        int currK = 0;

        while(right < nums.length) {
            // 1. Process right
            if(nums[right] != 1) currK++;

            // 2. Validate the condition
            while(currK > k) {
                if(nums[left] != 1) {
                    currK--;
                }
                left++;
            }

            // 3. Recalculate the result
            result = Math.max(result, right - left + 1);

            // 4. Increment right
            right++;
        }

        return result;
    }
}