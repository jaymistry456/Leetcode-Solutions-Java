// https://leetcode.com/problems/rotting-oranges/


// given: an mxn grid where each cell is either 0 (empty), 1 (fresh) and 2 (rotten)
// required: return the minimum no. of minutes for each orange to be rotten if possible, else return -1

// constraints
// m, n in [1, 10]

// tc: O(m*n), sc: O(m*n)
class Solution {
    public int orangesRotting(int[][] grid) {
        int EMPTY = 0;
        int FRESH = 1;
        int ROTTEN = 2;
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        Deque<int[]> queue = new ArrayDeque<>();   // [i, j] of ROTTEN oranges
        int freshCount = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == FRESH) {
                    freshCount++;
                }
                else if(grid[i][j] == ROTTEN) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if(freshCount == 0) {
            return 0;
        }

        int result = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] item = queue.poll();

                for(int[] dir: directions) {
                    int neiI = item[0] + dir[0];
                    int neiJ = item[1] + dir[1];

                    if(neiI < 0 || neiI >= m || 
                        neiJ < 0 || neiJ >= n || 
                        grid[neiI][neiJ] != FRESH) {
                        continue;
                    }
                    grid[neiI][neiJ] = ROTTEN;
                    freshCount--;
                    queue.offer(new int[]{neiI, neiJ});
                }
            }

            result++;
        }

        return freshCount == 0 ? result : -1;
    }
}