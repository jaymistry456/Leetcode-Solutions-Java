// https://leetcode.com/problems/minimum-size-subarray-sum/


// given: an array of integers nums and an integer target
// required: return the minimal length of subarray whose sum == target, else return 0

// constraints
// target in [1, 10^9]
// length of nums in [1, 100k]
// each value in nums in [1, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for(int j = i; j < nums.length; j++) {
                currSum += nums[j];
                if(currSum >= target) {
                    result = Math.min(result, j - i + 1);
                }
            }
        }

        return result != Integer.MAX_VALUE ? result : 0;
    }
}



// tc: O(n), sc: O(1)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int currSum = 0;

        while(right < nums.length) {
            currSum += nums[right];

            while(currSum >= target) {
                result = Math.min(result, right - left + 1);
                currSum -= nums[left];
                left++;
            }

            right++;
        }

        return result != Integer.MAX_VALUE ? result : 0;
    }
}