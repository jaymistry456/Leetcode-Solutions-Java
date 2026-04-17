// https://leetcode.com/problems/flood-fill/


// given: an mxn image (grid), and 3 integers sr, sc and color
// required: start from the [sr, sc] position and fill every adjacent position with the given color whose original color is the same as the starting postion color

// constraints
// m, n in [1, 50]
// each value in [0, 2^16]
// sr in [0, m - 1]
// sc in [0, n - 1]

// DFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int m;
    int n;
    int original;

    public void dfs(int[][] image, int i, int j, int color) {
        image[i][j] = color;

        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(neiI >= 0 && neiI < m && neiJ >= 0 && neiJ < n && image[neiI][neiJ] == original) {
                dfs(image, neiI, neiJ, color);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color) {
            return image;
        }

        m = image.length;
        n = image[0].length;
        original = image[sr][sc];

        dfs(image, sr, sc, color);

        return image;
    }
}




// BFS
// tc: O(m*n), sc: O(m*n)
class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color) {
            return image;
        }

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = image.length;
        int n = image[0].length;
        int original = image[sr][sc];

        Deque<int[]> queue = new ArrayDeque<>();   // [i, j]
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = color;

        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            int i = item[0];
            int j = item[1];

            for(int[] dir: directions) {
                int neiI = i + dir[0];
                int neiJ = j + dir[1];

                if(neiI >= 0 && neiI < m && neiJ >= 0 && neiJ < n && image[neiI][neiJ] == original) {
                    queue.offer(new int[]{neiI, neiJ});
                    image[neiI][neiJ] = color;
                }
            }
        }

        return image;
    }
}