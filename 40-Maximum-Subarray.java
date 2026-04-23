// https://leetcode.com/problems/maximum-subarray/


// given: an array of integers
// required: return the largest subarray sum

// constraints
// length of the array in [1, 100k]
// each value in [-10k, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int result = 0;

        for(int i = 0; i < nums.length; i++) {
            int curr = 0;
            for(int j = i; j < nums.length; j++) {
                curr += nums[j];
                result = Math.max(result, curr);
            }   
        }

        return result;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int curr = 0;

        for(int i = 0; i < nums.length; i++) {
            curr += nums[i];
            result = Math.max(result, curr);
            if(curr < 0) {
                curr = 0;
            }
        }

        return result;
    }
}