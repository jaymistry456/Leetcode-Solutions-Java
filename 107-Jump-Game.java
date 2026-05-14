// https://leetcode.com/problems/jump-game/


// given: an array of integers
// required: check whether it is possible to reach the end of the array while jumping

// constraints
// length of the array in [1, 10k]
// each value in [0, 100k]

// tc: O(2^n*n), sc: O(n)
class Solution {
    public boolean dfs(int[] nums, int i) {
        if(i >= nums.length - 1) {
            return true;
        }

        for(int j = i + 1; j <= Math.min(i + nums[i], nums.length - 1); j++) {
            if(dfs(nums, j)) {
                return true;
            }
        }

        return false;
    }

    public boolean canJump(int[] nums) {
        return dfs(nums, 0);
    }
}




// tc: O(n^2), sc: O(n)
class Solution {
    Boolean[] dp;   // i -> is it possible to reach the end of the array from i

    public boolean dfs(int[] nums, int i) {
        if(i >= nums.length - 1) {
            return true;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        for(int j = i + 1; j <= Math.min(i + nums[i], nums.length - 1); j++) {
            if(dfs(nums, j)) {
                dp[i] = true;
                return true;
            }
        }

        dp[i] = false;
        return false;
    }

    public boolean canJump(int[] nums) {
        dp = new Boolean[nums.length];

        return dfs(nums, 0);
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public boolean canJump(int[] nums) {
        int nextPossible = nums.length - 1;

        for(int i = nums.length - 1; i >= 0; i--) {
            if(nextPossible <= i + nums[i]) {
                nextPossible = i;
            }
        }

        return nextPossible == 0;
    }
}