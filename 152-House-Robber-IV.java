// https://leetcode.com/problems/house-robber-iv/


// given: an array of integers and an integer k
// required: return the minimum capability  of the robber: the maximum money he can steal from a house by robbing k houses

// constraints
// length of the array in [1, 100k]
// each value in [1, 10^9]
// k in [1, (length + 1) / 2]

// We need to minimize the maximum amount of money (capability) the robber steals from any given house
// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] nums, int k, int i) {
        if(k == 0) {
            return 0;
        }

        if(i >= nums.length) {
            return Integer.MAX_VALUE;
        }

        // Include
        int include = Math.max(nums[i], dfs(nums, k - 1, i + 2));

        // Exclude
        int exclude = dfs(nums, k, i + 1);

        return Math.min(include, exclude);
    }

    public int minCapability(int[] nums, int k) {
        return dfs(nums, k, 0);
    }
}




// tc: O(n*k), sc: O(n*k)
class Solution {
    Integer[][] dp;

    public int dfs(int[] nums, int k, int i) {
        if(k == 0) {
            return 0;
        }

        if(i >= nums.length) {
            return Integer.MAX_VALUE;
        }

        if(dp[i][k] != null) {
            return dp[i][k];
        }

        // Include
        int include = Math.max(nums[i], dfs(nums, k - 1, i + 2));

        // Exclude
        int exclude = dfs(nums, k, i + 1);

        dp[i][k] = Math.min(include, exclude);

        return dp[i][k];
    }

    public int minCapability(int[] nums, int k) {
        dp = new Integer[nums.length][k + 1];

        return dfs(nums, k, 0);
    }
}




// tc: O(logn*k), sc: O(1)
class Solution {
    public boolean isValid(int[] nums, int k, int capability) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] > capability) {
                i++;
            }
            else {
                k--;
                if(k == 0) {
                    return true;
                }
                i += 2;
            }
        }

        return false;
    }

    public int minCapability(int[] nums, int k) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for(int num: nums) {
            start = Math.min(start, num);
            end = Math.max(end, num);
        }

        int result = 0;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(isValid(nums, k, mid)) {
                result = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }
}