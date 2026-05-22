// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/


// given: an mxn matrix
// required: the length of the longest increasing path

// constraints
// m, n in [1, 200]
// each value in [0, 2^31 - 1]

// tc: O((m*n)^2), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int dfs(int[][] matrix, int i, int j) {
        int result = 0;

        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(neiI >= 0 && neiI < matrix.length &&
                neiJ >= 0 && neiJ < matrix[0].length &&
                matrix[neiI][neiJ] > matrix[i][j]
            ) {
                result = Math.max(result, dfs(matrix, neiI, neiJ));
            }
        }

        return 1 + result;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int result = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                result = Math.max(result, dfs(matrix, i, j));
            }
        }

        return result;
    }
}



// DFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;   // [i, j] -> longest path from [i, j]
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int dfs(int[][] matrix, int i, int j) {
        if(dp[i][j] != null) {
            return dp[i][j];
        }

        int result = 0;

        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(neiI >= 0 && neiI < matrix.length &&
                neiJ >= 0 && neiJ < matrix[0].length &&
                matrix[neiI][neiJ] > matrix[i][j]
            ) {
                result = Math.max(result, dfs(matrix, neiI, neiJ));
            }
        }

        dp[i][j] = 1 + result;

        return dp[i][j];
    }

    public int longestIncreasingPath(int[][] matrix) {
        dp = new Integer[matrix.length][matrix[0].length];

        int result = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                result = Math.max(result, dfs(matrix, i, j));
            }
        }

        return result;
    }
}



// BFS
// Can we use BFS for this?
// We have to use a special algorithm called Kahn's algorithm to calculate the
// indegrees of each node and start a multi phase from the nodes which have 
// indegrees of 0
// Here indegree means the no. of neighbors which are smaller than the given node
// It is analogous to how water starts filling in the valleys, till it reaches the peak
// Once the water reaches the largest peak, it represents the longest path which is the answer
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int[][] indegrees = new int[m][n];

        // 1. Calculate the indegrees of all the nodes in the matrix
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int[] dir: directions) {
                    int neiI = i + dir[0];
                    int neiJ = j + dir[1];

                    if(neiI >= 0 && neiI < m &&
                        neiJ >= 0 && neiJ < n &&
                        matrix[neiI][neiJ] < matrix[i][j]
                    ) {
                        indegrees[i][j]++;
                    }
                }
            }
        }

        // 2. Put all the nodes with indegree of 0 (deepest valleys) into a queue
        Deque<int[]> queue = new ArrayDeque<>();   // [i, j]
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(indegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 3. Start a multi-phase BFS where we process all the deepest valleys first and gradually move up
        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int k = 0; k < size; k++) {
                int[] item = queue.poll();
                int i = item[0];
                int j = item[1];

                for(int[] dir: directions) {
                    int neiI = i + dir[0];
                    int neiJ = j + dir[1];

                    if(neiI >= 0 && neiI < m &&
                        neiJ >= 0 && neiJ < n &&
                        matrix[neiI][neiJ] > matrix[i][j]
                    ) {
                        indegrees[neiI][neiJ]--;
                        if(indegrees[neiI][neiJ] == 0) {
                            queue.offer(new int[]{neiI, neiJ});
                        }
                    }
                }
            }

            result++;
        }

        return result;
    }
}