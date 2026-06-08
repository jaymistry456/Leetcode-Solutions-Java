// https://leetcode.com/problems/swim-in-rising-water/


// given: an nxn grid of integers
// required: start from the top-left of the grid (0, 0) and reach the bottom-right (n - 1, n - 1) and return the minimum time it takes to reach

// constraints
// n in [1, 50]
// each value in [0, n^2]
// each grid value is unique

// tc: O((m*n)^2), sc: O(m*n)
class Solution {
    int m;
    int n;
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    boolean[][] visited;

    public int dfs(int[][] grid, int i, int j, int currMax) {
        if(i == m - 1 && j == n - 1) {
            return Math.max(currMax, grid[i][j]);
        }

        int result = Integer.MAX_VALUE;
        visited[i][j] = true;
        currMax = Math.max(currMax, grid[i][j]);
        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(
                neiI >= 0 && neiI < m &&
                neiJ >= 0 && neiJ < n &&
                !visited[neiI][neiJ]
            ) {

                result = Math.min(
                    result, 
                    dfs(grid, neiI, neiJ, currMax)
                );
            }
        }
        visited[i][j] = false;

        return result;
    }

    public int swimInWater(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        return dfs(grid, 0, 0, 0);
    }
}




// BFS
// tc: O(n^2 * log(n^2)), sc: O(n^2)
class Solution {
    public int swimInWater(int[][] grid) {
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int m = grid.length;
        int n = grid[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );   // [i, j, maxTime]
        boolean[][] visited = new boolean[m][n];

        pq.offer(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            int[] item = pq.poll();
            int i = item[0];
            int j = item[1];
            int currMax = item[2];

            if(i == m - 1 && j == n - 1) return currMax;

            for(int[] dir: directions) {
                int neiI = i + dir[0];
                int neiJ = j + dir[1];

                if(
                    neiI >= 0 && neiI < m &&
                    neiJ >= 0 && neiJ < n &&
                    !visited[neiI][neiJ]
                ) {
                    int newMax = Math.max(currMax, grid[neiI][neiJ]);
                    pq.offer(new int[]{neiI, neiJ, newMax});
                    visited[neiI][neiJ] = true;
                }
            }
        }

        return -1;
    }
}





// Binary Search
// tc: O(n^2 * log(n^2)), sc: O(n^2)
class Solution {
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int m;
    int n;
    boolean[][] visited;

    public boolean dfs(int[][] grid, int i, int j, int limit) {
        if(grid[i][j] > limit) return false;

        if(i == m - 1 && j == n - 1) return true;

        visited[i][j] = true;
        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(
                neiI >= 0 && neiI < m &&
                neiJ >= 0 && neiJ < n &&
                !visited[neiI][neiJ]
            ) {
                if(dfs(grid, neiI, neiJ, limit)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int swimInWater(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                start = Math.min(start, grid[i][j]);
                end = Math.max(end, grid[i][j]);
            }
        }

        int result = -1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            visited = new boolean[m][n];

            if(dfs(grid, 0, 0, mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }
}