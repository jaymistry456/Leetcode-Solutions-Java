// https://leetcode.com/problems/max-area-of-island/


// given: an mxn grid containing 0s and 1s
// required: return the max area formed by an island out of all the islands (groups of 1s in the grid)

// constraints
// m, n in [1, 50]

// DFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    int m;
    int n;
    boolean[][] visited;
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int dfs(int[][] grid, int i, int j) {
        visited[i][j] = true;

        int result = 1;
        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(
                neiI >= 0 && neiI < m &&
                neiJ >= 0 && neiJ < n &&
                !visited[neiI][neiJ] &&
                grid[neiI][neiJ] == 1
            ) {
                result += dfs(grid, neiI, neiJ);
            }
        }

        return result;
    }

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    result = Math.max(result, dfs(grid, i, j));
                }
            }
        }

        return result;
    }
}



// BFS
// tc: O(m*n), sc: O(m*n)
record Pair(int i, int j) {}

class Solution {
    int m;
    int n;
    boolean[][] visited;
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int bfs(int[][] grid, int i, int j) {
        visited[i][j] = true;
        Deque<int[]> queue = new ArrayDeque<>();   // [i, j]
        queue.offer(new int[]{i, j});

        int result = 1;
        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            int currI = item[0];
            int currJ = item[1];

            for(int[] dir: directions) {
                int neiI = currI + dir[0];
                int neiJ = currJ + dir[1];

                if(
                    neiI >= 0 && neiI < m &&
                    neiJ >= 0 && neiJ < n &&
                    !visited[neiI][neiJ] &&
                    grid[neiI][neiJ] == 1
                ) {
                    queue.offer(new int[]{neiI, neiJ});
                    visited[neiI][neiJ] = true;
                    result++;
                }
            }
        }

        return result;
    }

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    result = Math.max(result, bfs(grid, i, j));
                }
            }
        }

        return result;
    }
}