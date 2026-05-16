// https://leetcode.com/problems/search-a-2d-matrix/


// given: an mxn matrix and a target
// required: check whether target is present in the matrix (true/false)

// constraints
// m, n in [1, 100]
// each value in [-10k, 10k]

// tc: O(log(m*n)), sc: O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0;
        int end = m * n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            int i = mid / n;
            int j = mid % n;

            if(matrix[i][j] == target) {
                return true;
            }
            else if(matrix[i][j] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return false;
    }
}