// https://leetcode.com/problems/valid-sudoku/


// given: a 9x9 grid of numbers
// required: whether the grid is a valid sudoku

// constraints
// length of the board is 9x9
// each cell is either '.', or a number from '1' to '9'

// tc: O(m*n), sc: O(1)
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set;
        
        // rows
        for(int i = 0; i < board.length; i++) {
            set = new HashSet<>();
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '.') continue;
                if(set.contains(board[i][j])) return false;
                set.add(board[i][j]);
            }
        }

        // columns
        for(int i = 0; i < board[0].length; i++) {
            set = new HashSet<>();
            for(int j = 0; j < board.length; j++) {
                if(board[j][i] == '.') continue;
                if(set.contains(board[j][i])) return false;
                set.add(board[j][i]);
            }
        }

        // 3x3 grids
        for(int i = 0; i < board.length; i = i + 3) {
            for(int j = 0; j < board[0].length; j = j + 3) {
                set = new HashSet<>();
                for(int x = i; x < i + 3; x++) {
                    for(int y = j; y < j + 3; y++) {
                        if(board[x][y] == '.') continue;
                        if(set.contains(board[x][y])) return false;
                        set.add(board[x][y]);
                    }
                }
            }
        }

        return true;
    }
}