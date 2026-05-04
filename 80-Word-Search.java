// https://leetcode.com/problems/word-search/


// given: an mxn board of characters and a string word
// required: check whether word can be formed from the board using horizontal and vertical navigation

// constraints
// m, n in [1, 6]
// length of word in [1, 15]
// board, word contain lowercase letters only

// tc: O((m*n)*4^k), sc: O(k)
class Solution {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean dfs(char[][] board, int r, int c, String word, int i) {
        if(r < 0 || r >= m || c < 0 || c >= n || board[r][c] != word.charAt(i)) {
            return false;
        }

        if(i == word.length() - 1) {
            return true;
        }

        char temp = board[r][c];
        board[r][c] = '#';
        for(int[] dir: directions) {
            if(dfs(board, r + dir[0], c + dir[1], word, i + 1)) {
                board[r][c] = temp;
                return true;
            }
        }
        board[r][c] = temp;

        return false;
    }

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }
}