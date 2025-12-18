// https://leetcode.com/problems/flood-fill/


class Solution {
    // public void dfs(int[][] image, int r, int c, int originalColor, int color) {
    //     image[r][c] = color;
    //     int[][] directions ={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //     for(int i = 0; i < directions.length; i++) {
    //         int neiRow = r + directions[i][0];
    //         int neiCol = c + directions[i][1];
    //         if(neiRow >= 0 && neiRow < image.length && neiCol >= 0 && neiCol < image[0].length && image[neiRow][neiCol] == originalColor) {
    //             dfs(image, neiRow, neiCol, originalColor, color);
    //         }
    //     }
    // }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // // dfs
        // // TC: O(m*n), SC: O(m*n)
        // int originalColor = image[sr][sc];
        // if(originalColor == color) {
        //     return image;
        // }
        // dfs(image, sr, sc, originalColor, color);
        // return image;


        // bfs
        // TC: O(m*n), SC: O(m*n)
        int originalColor = image[sr][sc];
        if(originalColor == color) {
            return image;
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<int[]> q = new ArrayDeque<>();
        image[sr][sc] = color;
        q.offer(new int[]{sr, sc});
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for(int i = 0; i < directions.length; i++) {
                int neiRow = curr[0] + directions[i][0];
                int neiCol = curr[1] + directions[i][1];
                if(neiRow >= 0 && neiRow < image.length && neiCol >= 0 && neiCol < image[0].length && image[neiRow][neiCol] == originalColor) {
                    image[neiRow][neiCol] = color;
                    q.offer(new int[]{neiRow, neiCol});
                }
            }
        }
        return image;
    }
}