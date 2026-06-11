/*
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.

Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.

Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.


Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["O","O","O","O","O","O","O","O"]]
Output: 5
*/

// given: an mxn grid where '*' represents the starting position, '#' is a food cell, 'O' is free space which can traversed and 'X' is an obstacle which cannot be traversed
// required: find the length of the shortest path to reach a food cell, if no such path exists return -1

// constraints
// m, n in [1, 200]
// grid[row][col] is '*', 'X', 'O', or '#'
// The grid contains exactly one '*'

// Multi-phase BFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int getFood(char[][] grid) {
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int m = grid.length;
        int n = grid[0].length;
        char FOOD = '#';
        char SPACE = 'O';
        char OBSTACLE = 'X';
        
        // 1. Find the starting position
        int[] start = null;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '*') {
                    start = new int[]{i, j};
                    break;
                }
            }
            
            if(start != null) break;
        }
        
        // 2. Create a queue with starting position and a visited array
        Deque<int[]> queue = new ArrayDeque<>();   // [i, j]
        queue.offer(start);
        
        boolean[][] visited = new boolean[m][n];        
        
        // 3. Traverse all current paths from the queue simulateneously (multi-phase BFS)
        int result = 0;
        while(!queue.isEmpty()) {
            result++;
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int[] item = queue.poll();
                
                for(int[] dir: directions) {
                    int neiI = item[0] + dir[0];
                    int neiJ = item[1] + dir[1];
                    
                    if(
                        neiI >= 0 && neiI < m &&
                        neiJ >= 0 &&  neiJ < n &&
                        !visited[neiI][neiJ]
                    ) {
                        if(grid[neiI][neiJ] == FOOD) {
                            return result;
                        }
                        else if(grid[neiI][neiJ] == SPACE) {
                            visited[neiI][neiJ] = true;
                            queue.offer(new int[]{neiI, neiJ});
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}