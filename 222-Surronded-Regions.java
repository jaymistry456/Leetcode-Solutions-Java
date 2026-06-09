// https://leetcode.com/problems/surrounded-regions/


// given: an mxn board containing 'X' and 'O'
// required: convert all the regions to 'X's

// constraints
// m, n in [1, 200]
// each value is either 'X' or 'O'

// a surround is formed around a region when the region's 'O' are surrounded by 'X's either vertically or horizontally and they are not on the edge of the board ('O' can not be on the edges)
// we have to ignore the 'O' on the edges of the boards and any 'O' adjacent to such 'O's as well

// tc: O(m*n), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int m;
    int n;

    public void dfs(char[][] board, int i, int j) {
        board[i][j] = '#';

        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(
                neiI >= 0 && neiI < m &&
                neiJ >= 0 && neiJ < n &&
                board[neiI][neiJ] == 'O'
            ) {
                dfs(board, neiI, neiJ);
            }
        }
    }

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;

        // 1. Convert all edge 'O' into '#'
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') dfs(board, i, 0);
            if(board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }

        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') dfs(board, 0, j);
            if(board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }

        // 2. Convert the remaining 'O' into 'X' and any '#' back to 'O'
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }
}