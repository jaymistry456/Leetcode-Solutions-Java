// https://leetcode.com/problems/climbing-stairs/


// given: an integer n representing the total no. of stairs
// required: return the no. of ways that we can climb to the top

// constraints
// n in [1, 45]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int i, int n) {
        if(i == n) {
            return 1;
        }
        if(i > n) {
            return 0;
        }

        return dfs(i + 1, n) + dfs(i + 2, n);
    }

    public int climbStairs(int n) {
        return dfs(0, n);
    }
}



// top-down
// tc: O(n), sc: O(n)
class Solution {
    Integer[] dp;   // i -> no. of ways to reach the top (n) from i

    public int dfs(int i, int n) {
        if(i == n) {
            return 1;
        }
        if(i > n) {
            return 0;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        dp[i] = dfs(i + 1, n) + dfs(i + 2, n);

        return dp[i];
    }

    public int climbStairs(int n) {
        dp = new Integer[n];

        return dfs(0, n);
    }
}



// bottom-up
// tc: O(n), sc: O(n)
class Solution {

    public int climbStairs(int n) {
        Integer[] dp = new Integer[n + 2];   // i -> no. of ways to reach the top (n) from i
        dp[n] = 1;
        dp[n + 1] = 0;

        for(int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }

        return dp[0];
    }
}