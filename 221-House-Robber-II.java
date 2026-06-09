// https://leetcode.com/problems/house-robber-ii/


// given: an array of integers
// required: return the max amount of money the robber can rob without stealing from adjacent houses (first and last are adjacent in a circle)

// constraints
// length of nums in [1, 100]
// each value in [0, 1000]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] nums, int start, int end, int i) {
        if(i > end) return 0;

        return Math.max(
            dfs(nums, start, end, i + 1),
            dfs(nums, start, end, i + 2) + nums[i]
        );
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);

        return Math.max(
            dfs(nums, 0, n - 2, 0),
            dfs(nums, 1, n - 1, 1)
        );
    }
}




// Top-down
// tc: O(n), sc: O(n)
class Solution {
    public int dfs(int[] nums, int start, int end, int i, Integer[] dp) {
        if(i > end) return 0;

        if(dp[i] != null) return dp[i];

        dp[i] = Math.max(
            dfs(nums, start, end, i + 1, dp),
            dfs(nums, start, end, i + 2, dp) + nums[i]
        );

        return dp[i];
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);

        return Math.max(
            dfs(nums, 0, n - 2, 0, new Integer[n]),
            dfs(nums, 1, n - 1, 1, new Integer[n])
        );
    }
}




// Bottom-up
// tc: O(n), sc: O(n)
class Solution {
    public int helper(int[] nums, int start, int end) {
        int n = nums.length;

        int[] dp = new int[n + 2];
        
        for(int i = end; i >= start; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }

        return dp[start];
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);

        return Math.max(
            helper(nums, 0, n - 2),
            helper(nums, 1, n - 1)
        );
    }
}