// https://leetcode.com/problems/maximum-product-subarray/


// given: an array of integers
// required: return the largest product that can be formed from a subarray

// constraints
// length of nums in [1, 20k]
// each value in [-10, 10]

// tc: O(n^2), sc: O(1)
class Solution {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            int curr = 1;
            for(int j = i; j < nums.length; j++) {
                curr *= nums[j];
                result = Math.max(result, curr);
            }
        }

        return result;
    }
}



// tc: O(n), sc: O(1)
class Solution {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int max = 1;
        int min = 1;

        for(int i = 0; i < nums.length; i++) {
            int temp = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(temp * nums[i], min * nums[i]));
            result = Math.max(result, max);
        }

        return result;
    }
}