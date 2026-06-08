// https://leetcode.com/problems/unique-paths-ii/


// given: a 2D array of size mxn
// required: return the number of possible paths from top-left to the bottom-right of the grid, which don't pass through obstacles

// constraints
// m, n in [1, 100]
// each value is either 0 (space) or 1 (obstacle)
// we can simply do a dfs from the top-left (0, 0) and try to reach bottom-right (m - 1, n - 1)

// tc: O((m*n)^2), sc: O(m*n)
class Solution {
    public int dfs(int[][] grid, int i, int j) {
        if(i >= grid.length || j >= grid[0].length) return 0;

        if(grid[i][j] == 1) return 0;

        if(i == grid.length - 1 && j == grid[0].length - 1) return 1;

        return dfs(grid, i + 1, j) + dfs(grid, i, j + 1);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return dfs(obstacleGrid, 0, 0);
    }
}



// Top-down
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;   // [i, j] -> no of ways to reach bottom-right from [i, j]

    public int dfs(int[][] grid, int i, int j) {
        if(i >= grid.length || j >= grid[0].length) return 0;

        if(grid[i][j] == 1) return 0;

        if(i == grid.length - 1 && j == grid[0].length - 1) return 1;

        if(dp[i][j] != null) return dp[i][j];

        dp[i][j] = dfs(grid, i + 1, j) + dfs(grid, i, j + 1);

        return dp[i][j];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        dp = new Integer[obstacleGrid.length][obstacleGrid[0].length];

        return dfs(obstacleGrid, 0, 0);
    }
}




// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m + 1][n + 1];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if(i == m - 1 && j == n - 1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                    }
                }
            }
        }
        
        return dp[0][0];
    }
}