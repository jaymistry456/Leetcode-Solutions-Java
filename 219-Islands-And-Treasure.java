// https://neetcode.io/problems/islands-and-treasure/


// given: an mxn 2D grid with 3 possible values: -1 (obstacle), 0 (treasure) and INF (land)
// required: fill each land cell with the distance to its nearest treasure chest if it is possible to reach a treasure cell

// constraints
// m, n in [1, 100]
// each value in {-1, 0, 2147483647}

// tc: O(m*n), sc: O(m*n)
class Solution {
    public void islandsAndTreasure(int[][] grid) {
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int m = grid.length;
        int n = grid[0].length;

        int WATER = -1;
        int TREASURE = 0;
        int INF = 2147483647;
        
        Deque<int[]> queue = new ArrayDeque<>();   // [i, j] of TREASURE
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == TREASURE) queue.offer(new int[]{i, j});
            }
        }

        int dist = 0;
        while(!queue.isEmpty()) {
            dist++;
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] item = queue.poll();
                
                for(int[] dir: directions) {
                    int neiI = item[0] + dir[0];
                    int neiJ = item[1] + dir[1];

                    if(
                        neiI >= 0 && neiI < m &&
                        neiJ >= 0 && neiJ < n &&
                        grid[neiI][neiJ] == INF
                    ) {
                        grid[neiI][neiJ] = dist;
                        queue.offer(new int[]{neiI, neiJ});
                    }
                }
            }
        }
    }
}
