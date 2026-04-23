// https://leetcode.com/problems/01-matrix/


// given: a 2D matrix of size mxn
// required: return the distance of the nearest 0 for each cell

// constraints
// m, n in [1, 10k]
// each cell is either 0 or 1

// tc: O(m*n), sc: O(m*n)
record Pair(int i, int j) {}

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        Deque<int[]> queue = new ArrayDeque<>();   // [i, j]
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int distance = 1;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Set<Pair> set = new HashSet<>();
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] item = queue.poll();
                
                for(int[] dir: directions) {
                    int neiI = item[0] + dir[0];
                    int neiJ = item[1] + dir[1];

                    if(neiI >= 0 && neiI < mat.length &&
                        neiJ >= 0 && neiJ < mat[0].length &&
                        !set.contains(new Pair(neiI, neiJ)) &&
                        mat[neiI][neiJ] != 0) {
                            mat[neiI][neiJ] = distance;
                            queue.offer(new int[]{neiI, neiJ});
                            set.add(new Pair(neiI, neiJ));
                        }
                }
            }

            distance++;
        }

        return mat;
    }
}