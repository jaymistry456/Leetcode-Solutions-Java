// https://leetcode.com/problems/unique-paths/


// given: two integer m and n
// required: return the number of unique paths that start from top-left (0, 0) and go till bottom-right (m - 1, n - 1)

// constraints
// m, n in [1, 100]

// tc: O((m*n)^2), sc: O(m*n)
class Solution {
    public int dfs(int i, int j, int m, int n) {
        if(i == m - 1 && j == n - 1) {
            return 1;
        }

        if(i >= m || j >= n) {
            return 0;
        }

        int result = 0;
        
        // move to the right
        result += dfs(i, j + 1, m, n);

        // move to the bottom
        result += dfs(i + 1, j, m, n);

        return result;
    }

    public int uniquePaths(int m, int n) {
        return dfs(0, 0, m, n);
    }
}




// Top-down
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;  // [i, j] -> total unique paths to reach [m - 1, n - 1] from [i, j]

    public int dfs(int i, int j, int m, int n) {
        if(i == m - 1 && j == n - 1) {
            return 1;
        }

        if(i >= m || j >= n) {
            return 0;
        }

        if(dp[i][j] != null) {
            return dp[i][j];
        }

        int result = 0;
        
        // move to the right
        result += dfs(i, j + 1, m, n);

        // move to the bottom
        result += dfs(i + 1, j, m, n);

        dp[i][j] = result;

        return result;
    }

    public int uniquePaths(int m, int n) {
        dp = new Integer[m][n];

        return dfs(0, 0, m, n);
    }
}




// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int uniquePaths(int m, int n) {
        Integer[][] dp = new Integer[m + 1][n + 1];
        
        for(int i = 0; i < m; i++) {
            dp[i][n] = 0;
        }
        for(int i = 0; i < n; i++) {
            dp[m][i] = 0;
        }

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }
}