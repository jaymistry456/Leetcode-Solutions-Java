// https://leetcode.com/problems/n-queens/


// given: an integer n representing the size of a chessboard
// required: all the distinct solutions of where to place n queens on the chessboard

// constraints
// n in [1, 9]

// tc: O(n!), sc: O(n^2)
class Solution {
    List<List<String>> result = new ArrayList<>();
    List<List<Character>> currResult = new ArrayList<>();

    public boolean isValid(int r, int c, int n) {
        char ch = currResult.get(r).get(c);

        // Row
        for(int i = 0; i < n; i++) {
            char curr = currResult.get(i).get(c);
            if(i != r && curr == ch) {
                return false;
            }
        }

        // Column
        for(int i = 0; i < n; i++) {
            char curr = currResult.get(r).get(i);
            if(i != c && curr == ch) {
                return false;
            }
        }

        // Diagonals
        // Positive diagonal
        int tempR = r - 1;
        int tempC = c - 1;
        while(tempR >= 0 && tempC >= 0) {
            char curr = currResult.get(tempR).get(tempC);
            if(curr == ch) {
                return false;
            }
            tempR--;
            tempC--;
        }
        tempR = r + 1;
        tempC = c + 1;
        while(tempR < n && tempC < n) {
            char curr = currResult.get(tempR).get(tempC);
            if(curr == ch) {
                return false;
            }
            tempR++;
            tempC++;
        }

        // Negative diagonal
        tempR = r - 1;
        tempC = c + 1;
        while(tempR >= 0 && tempC < n) {
            char curr = currResult.get(tempR).get(tempC);
            if(curr == ch) {
                return false;
            }
            tempR--;
            tempC++;
        }
        tempR = r + 1;
        tempC = c - 1;
        while(tempR < n && tempC >= 0) {
            char curr = currResult.get(tempR).get(tempC);
            if(curr == ch) {
                return false;
            }
            tempR++;
            tempC--;
        }

        return true;
    }

    public void dfs(int r, int n) {
        if(r == n) {
            List<String> tempResult = new ArrayList<>();

            for(List<Character> currRow: currResult) {
                StringBuilder sb = new StringBuilder("");
                for(char ch: currRow) {
                    sb.append(ch);
                }
                tempResult.add(sb.toString());
            }

            result.add(tempResult);
            return;
        }

        for(int c = 0; c < n; c++) {
            if(currResult.get(r).get(c) == '.') {
                currResult.get(r).set(c, 'Q');

                if(isValid(r, c, n)) {
                    dfs(r + 1, n);
                }

                currResult.get(r).set(c, '.');
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        for(int i = 0; i < n; i++) {
            List<Character> currRow = new ArrayList<>();

            for(int j = 0; j < n; j++) {
                currRow.add('.');
            }

            currResult.add(currRow);
        }

        dfs(0, n);

        return result;
    }
}