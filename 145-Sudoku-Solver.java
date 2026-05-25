// https://leetcode.com/problems/sudoku-solver/


// given: a 9x9 grid
// required: solve the sudoku

// constraints
// each char is either a digit from '1' to '9' or a '.'

// tc: O(1), sc: O(1)
class Solution {
    public boolean isValid(char[][] board, int i, int j) {
        char ch = board[i][j];

        // Row
        for(int k = 0; k < 9; k++) {
            if(k != i && board[k][j] == ch) {
                return false;
            }
        }

        // Column
        for(int k = 0; k < 9; k++) {
            if(k != j && board[i][k] == ch) {
                return false;
            }
        }

        // 3x3 Grid
        int gridRow = i / 3;
        int gridCol = j / 3;
        for(int r = gridRow * 3; r < gridRow * 3 + 3; r++) {
            for(int c = gridCol * 3; c < gridCol * 3 + 3; c++) {
                if(r != i && c != j && board[r][c] == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean dfs(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '.') {
                    for(int k = 1; k <= 9; k++) {
                        board[i][j] = (char) (k + '0');
                        if(isValid(board, i, j) && dfs(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
        }

        return true;
    }
    public void solveSudoku(char[][] board) {
        dfs(board);
    }
}