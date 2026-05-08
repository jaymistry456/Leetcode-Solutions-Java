// https://leetcode.com/problems/house-robber/


// given: an array of integers representing a certain amount of money
// required: return the max amount of money that can be robbed without alerting the police

// constraints
// length of nums in [1, 100]
// each value in [0, 400]

// tc: O(n*2^n), sc: O(n)
class Solution {
    public int dfs(int[] nums, int i) {
        if(i >= nums.length) {
            return 0;
        }

        int include = nums[i] + dfs(nums, i + 2);
        int skip = dfs(nums, i + 1);

        return Math.max(include, skip);
    }

    public int rob(int[] nums) {
        return dfs(nums, 0);
    }
}




// Top-down
// tc: O(n), sc: O(n)
class Solution {
    Integer[] dp;   // max amoount of money that can be made from i to the end

    public int dfs(int[] nums, int i) {
        if(i >= nums.length) {
            return 0;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        int include = nums[i] + dfs(nums, i + 2);
        int skip = dfs(nums, i + 1);

        dp[i] = Math.max(include, skip);
        return dp[i];
    }

    public int rob(int[] nums) {
        dp = new Integer[nums.length];

        return dfs(nums, 0);
    }
}




// Bottom-up
// tc: O(n), sc: O(n)
class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 2];
        
        for(int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]);
        }

        return dp[0];
    }
}