// https://leetcode.com/problems/partition-equal-subset-sum/


// given: an array of integers
// required: check whether the array can be partitioned into 2 subsets, each having equal sum to the other

// constraints
// length of nums in [1, 200]
// each value in [1, 100]

// tc: O(2^t), sc: O(t)
class Solution {
    public boolean dfs(int[] nums, int i, int currSum, int target) {
        if(currSum == target) {
            return true;
        }

        if(i == nums.length || currSum > target) {
            return false;
        }

        // include
        boolean include = dfs(nums, i + 1, currSum + nums[i], target);

        // exclude
        boolean exclude = dfs(nums, i + 1, currSum, target);

        return include || exclude;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }

        if(sum % 2 == 1) {
            return false;
        }

        return dfs(nums, 0, 0, sum / 2);
    }
}




// tc: O(n*t), sc: O(n*t)
class Solution {
    Boolean[][] dp;   // [i, currSum] -> if target can be achieved from this state

    public boolean dfs(int[] nums, int i, int currSum, int target) {
        if(currSum == target) {
            return true;
        }

        if(i == nums.length || currSum > target) {
            return false;
        }

        if(dp[i][currSum] != null) {
            return dp[i][currSum];
        }

        // include
        boolean include = dfs(nums, i + 1, currSum + nums[i], target);

        // exclude
        boolean exclude = dfs(nums, i + 1, currSum, target);

        dp[i][currSum] = include || exclude;

        return dp[i][currSum];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }

        if(sum % 2 == 1) {
            return false;
        }

        dp = new Boolean[nums.length][sum / 2 + 1];

        return dfs(nums, 0, 0, sum / 2);
    }
}