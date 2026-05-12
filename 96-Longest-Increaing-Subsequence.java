// https://leetcode.com/problems/longest-increasing-subsequence/


// given: an array of integers
// required: return the length of the longest strictly increasing subsequence

// constraints
// length of the array in [1, 2500]
// each value in [-10k, 10k]

// tc: O(n*2^n), sc: O(n)
class Solution {
    public int dfs(int[] nums, int i, int prevMax) {
        if(i == nums.length) {
            return 0;
        }

        int include = 0;
        if(nums[i] > prevMax) {
            include = 1 + dfs(nums, i + 1, nums[i]);
        }

        int exclude = dfs(nums, i + 1, prevMax);

        return Math.max(include, exclude);
    }

    public int lengthOfLIS(int[] nums) {
        return dfs(nums, 0, Integer.MIN_VALUE);
    }
}




// tc: O(n^2), sc: O(n^2)
class Solution {
    Integer[][] dp;   // [i, prevIdx + 1] -> max increasing sequence from this state to the end of the array

    public int dfs(int[] nums, int i, int prevIdx) {
        if(i == nums.length) {
            return 0;
        }

        if(dp[i][prevIdx + 1] != null) {
            return dp[i][prevIdx + 1];
        }

        int include = 0;
        if(prevIdx == -1 || nums[i] > nums[prevIdx]) {
            include = 1 + dfs(nums, i + 1, i);
        }

        int exclude = dfs(nums, i + 1, prevIdx);

        dp[i][prevIdx + 1] = Math.max(include, exclude);

        return dp[i][prevIdx + 1];
    }

    public int lengthOfLIS(int[] nums) {
        dp = new Integer[nums.length][nums.length + 1];

        return dfs(nums, 0, -1);
    }
}