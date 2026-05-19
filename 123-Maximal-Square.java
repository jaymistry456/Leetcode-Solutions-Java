// https://leetcode.com/problems/maximal-square/


// given: a mxn matrix containing 0s and 1s
// required: find the largest square containing only 1s and return its area

// constraints
// m, n in [1, 300]

// Top-down
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;   // i, j -> maximal square length possible from it to its right and bottom

    public int dfs(char[][] matrix, int i, int j) {
        if(i == matrix.length || j == matrix[0].length) {
            return 0;
        }

        if(dp[i][j] != null) {
            return dp[i][j];
        }

        if(matrix[i][j] == '1') {
            int right = dfs(matrix, i + 1, j);
            int diagonal = dfs(matrix, i + 1, j + 1);
            int bottom = dfs(matrix, i, j + 1);
            dp[i][j] = 1 + Math.min(right, Math.min(diagonal, bottom));
        }
        else {
            dp[i][j] = 0;
        }

        return dp[i][j];
    }

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        dp = new Integer[m + 1][n + 1];

        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                result = Math.max(result, dfs(matrix, i, j));
            }
        }

        return result * result;
    }
}




// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Integer[][] dp = new Integer[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            dp[i][n] = 0;
        }
        for(int i = 0; i <= n; i++) {
            dp[m][i] = 0;
        }

        int result = 0;
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    int right = dp[i + 1][j];
                    int diagonal = dp[i + 1][j + 1];
                    int bottom = dp[i][j + 1];
                    dp[i][j] = 1 + Math.min(right, Math.min(diagonal, bottom));
                    result = Math.max(result, dp[i][j]);
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }

        return result * result;
    }
}