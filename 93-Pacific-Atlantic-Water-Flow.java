// https://leetcode.com/problems/pacific-atlantic-water-flow/


// given: an mxn grid representing height of a rectangular island
// required: return a 2D List which represents the cells of the island from where the water flows in both directions

// constraints
// m, n in [1, 200]
// each height in [0, 100k]

// DFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int m;
    int n;

    public void dfs(int[][] heights, int i, int j, Set<List<Integer>> visited) {
        visited.add(List.of(i, j));

        for(int[] dir: directions) {
            int neiI = i + dir[0];
            int neiJ = j + dir[1];

            if(neiI >= 0 && neiI < m && 
                neiJ >= 0 && neiJ < n &&
                heights[neiI][neiJ] >= heights[i][j] &&
                !visited.contains(List.of(neiI, neiJ))
            ) {
                dfs(heights, neiI, neiJ, visited);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        Set<List<Integer>> pacific = new HashSet<>();
        Set<List<Integer>> atlantic = new HashSet<>();

        for(int i = 0; i < m; i++) {
            dfs(heights, i, 0, pacific);
        }

        for(int i = 0; i < n; i++) {
            dfs(heights, 0, i, pacific);
        }

        for(int i = 0; i < m; i++) {
            dfs(heights, i, n - 1, atlantic);
        }

        for(int i = 0; i < n; i++) {
            dfs(heights, m - 1, i, atlantic);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                List<Integer> currCell = new ArrayList<>(List.of(i, j));
                if(pacific.contains(currCell) && atlantic.contains(currCell)) {
                    result.add(currCell);
                }
            }
        }

        return result;
    }
}





// BFS
// tc: O(m*n), sc: O(m*n)
class Solution {
    int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int m;
    int n;

    public void bfs(int[][] heights, int i, int j, Set<List<Integer>> visited) {
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(List.of(i, j));
        visited.add(List.of(i, j));

        while(!queue.isEmpty()) {
            List<Integer> item = queue.poll();
            int r = item.get(0);
            int c = item.get(1);

            for(int[] dir: directions) {
                int neiR = r + dir[0];
                int neiC = c + dir[1];

                if(neiR >= 0 && neiR < m &&
                    neiC >= 0 && neiC < n &&
                    !visited.contains(List.of(neiR, neiC)) &&
                    heights[neiR][neiC] >= heights[r][c]
                ) {
                    queue.offer(List.of(neiR, neiC));
                    visited.add(List.of(neiR, neiC));
                }
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        Set<List<Integer>> pacific = new HashSet<>();
        Set<List<Integer>> atlantic = new HashSet<>();

        for(int i = 0; i < m; i++) {
            bfs(heights, i, 0, pacific);
        }

        for(int i = 0; i < n; i++) {
            bfs(heights, 0, i, pacific);
        }

        for(int i = 0; i < m; i++) {
            bfs(heights, i, n - 1, atlantic);
        }

        for(int i = 0; i < n; i++) {
            bfs(heights, m - 1, i, atlantic);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                List<Integer> currCell = new ArrayList<>(List.of(i, j));
                if(pacific.contains(currCell) && atlantic.contains(currCell)) {
                    result.add(currCell);
                }
            }
        }

        return result;
    }
}