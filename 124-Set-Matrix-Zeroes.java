// https://leetcode.com/problems/set-matrix-zeroes/


// given: an mxn matrix
// required: set the entire row and column 0 if a cell is 0

// constraints
// m, n in [1, 200]
// each value in [-2^31, 2^31 - 1]

// tc: O(m*n), sc: O(m+n)
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] rows = new int[m];
        int[] cols = new int[n];
        Arrays.fill(rows, 1);
        Arrays.fill(cols, 1);
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    rows[i] = 0;
                    cols[j] = 0;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rows[i] == 0 || cols[j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}