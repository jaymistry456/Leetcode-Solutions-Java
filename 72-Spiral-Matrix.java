// https://leetcode.com/problems/spiral-matrix/


// given: a 2D matrix of numbers
// required: return all the elements in the matrix in the spiral order

// constraints
// m, n in [1, 10]
// each matrix value in [-100, 100]

// tc: O(m*n), sc: O(1)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        List<Integer> result = new ArrayList<>();

        while(left <= right && top <= bottom) {
            // left to right
            for(int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // top to bottom
            for(int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if(left > right || top > bottom) {
                break;
            }

            // right to left
            for(int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;

            for(int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }

        return result;
    }
}