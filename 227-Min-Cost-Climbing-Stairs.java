// https://leetcode.com/problems/min-cost-climbing-stairs/


// given: an array of cost where each ith value is the cost of the ith staircase by either climbing 1 or 2 staircase
// required: return the minimum cost to reach the top of the floor, starting from either 0th staircase or 1st staircase

// constraints
// length of the array in [2, 1000]
// each value in cost in [0, 999]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] cost, int i) {
        if(i >= cost.length) return 0;

        return Math.min(
            dfs(cost, i + 1) + cost[i],
            dfs(cost, i + 2) + cost[i]
        );
    }

    public int minCostClimbingStairs(int[] cost) {
        return Math.min(
            dfs(cost, 0),
            dfs(cost, 1)
        );
    }
}




// tc: O(n), sc: O(n)
class Solution {
    Integer[] dp;   // i -> min cost to reach top of the floor from i

    public int dfs(int[] cost, int i) {
        if(i >= cost.length) return 0;

        if(dp[i] != null) return dp[i];

        dp[i] = Math.min(
            dfs(cost, i + 1) + cost[i],
            dfs(cost, i + 2) + cost[i]
        );

        return dp[i];
    }

    public int minCostClimbingStairs(int[] cost) {
        dp = new Integer[cost.length];

        return Math.min(
            dfs(cost, 0),
            dfs(cost, 1)
        );
    }
}




// Bottom-up
// tc: O(n), sc: O(n)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 2];   // i -> min cost to reach top of the floor from i

        dp[n] = 0;
        dp[n + 1] = 0;

        for(int i = n - 1; i >= 0; i--) {
            dp[i] = Math.min(
                dp[i + 1] + cost[i],
                dp[i + 2] + cost[i]
            );
        }

        return Math.min(dp[0], dp[1]);
    }
}