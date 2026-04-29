// https://leetcode.com/problems/number-of-islands/


// given: an mxn grid of '0' and '1'
// required: return the number of islands (groups of '1's)

// constraints
// m, n in [1, 300]

// DFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] visited;

    public void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || 
            j < 0 || j >= grid[0].length || 
            visited[i][j] ||
            grid[i][j] == '0') {
            return;
        }

        visited[i][j] = true;

        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            dfs(grid, neiI, neiJ);
        }
    }

    public int numIslands(char[][] grid) {
        visited = new boolean[grid.length][grid[0].length];

        int result = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;
                }
            }
        }

        return result;
    }
}





// BFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] visited;

    public void bfs(char[][] grid, int i, int j) {
        visited[i][j] = true;

        Deque<int[]> queue = new ArrayDeque<>();  // [i, j]
        queue.offer(new int[]{i, j});

        while(!queue.isEmpty()) {
            int[] item = queue.poll();

            for(int[] dir: directions) {
                int neiI = item[0] + dir[0];
                int neiJ = item[1] + dir[1];

                if(neiI < 0 || neiI >= grid.length ||
                    neiJ < 0 || neiJ >= grid[0].length ||
                    visited[neiI][neiJ] ||
                    grid[neiI][neiJ] == '0') {
                        continue;
                    }
                queue.offer(new int[]{neiI, neiJ});
                visited[neiI][neiJ] = true;
            }
        }

    }

    public int numIslands(char[][] grid) {
        visited = new boolean[grid.length][grid[0].length];

        int result = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, i, j);
                    result++;
                }
            }
        }

        return result;
    }
}