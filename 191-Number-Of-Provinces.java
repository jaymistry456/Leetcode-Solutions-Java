// https://leetcode.com/problems/number-of-provinces/


// given: a 2D nxn matrix isConnected where each value [i, j, 0/1]
// required: return the no. of provinces (connected components)

// constraints
// length of isConnected in [1, 200]

// DFS
// tc: O(n^2), sc: O(n)
class Solution {
    int n;
    boolean[] visited;

    public void dfs(int[][] isConnected, int i, int parent) {
        visited[i] = true;

        for(int j = 0; j < n; j++) {
            if(j != i && j != parent && isConnected[i][j] == 1 && !visited[j]) {
                dfs(isConnected, j, i);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        visited = new boolean[n];

        int result = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(isConnected, i, -1);
                result++;
            }
        }

        return result;
    }
}




// BFS
// tc: O(n^2), sc: O(n)
class Solution {
    int n;
    boolean[] visited;

    public void bfs(int[][] isConnected, int curr) {
        Deque<int[]> queue = new ArrayDeque<>();   // [node, parent]
        queue.offer(new int[]{curr, -1});
        visited[curr] = true;

        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            int i = item[0];
            int parent = item[1];

            for(int j = 0; j < n; j++) {
                if(j != i && j != parent && isConnected[i][j] == 1 && !visited[j]) {
                    queue.offer(new int[]{j, i});
                    visited[j] = true;
                }
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        visited = new boolean[n];

        int result = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(isConnected, i);
                result++;
            }
        }

        return result;
    }
}




// Union-Find
// tc: O(n^2), sc: O(n)
class Solution {
    int n;
    int[] parent;
    int[] rank;

    public int findParent(int i) {
        if(parent[i] != i) {
            parent[i] = findParent(parent[i]);
        }
        return parent[i];
    }

    public boolean union(int i, int j) {
        int rootI = findParent(i);
        int rootJ = findParent(j);

        if(rootI == rootJ) {
            return false;
        } else {
            if(rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if(rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
            return true;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        parent = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int result = n;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j && isConnected[i][j] == 1 && union(i, j)) {
                    result--;
                }
            }
        }

        return result;
    }
}