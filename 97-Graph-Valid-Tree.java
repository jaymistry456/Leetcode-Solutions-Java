// https://neetcode.io/problems/valid-tree


// given: no. of nodes n (0 to n - 1) and an array of edges of an undirected graph
// required: check whether it is a valid tree

// constraints
// n in [1, 100]
// length of edges in [0, n*(n-1)/2]


// DFS
// tc: O(v + e), sc: O(v + e)
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();  // node -> List of neighbors
    boolean[] visited;

    public boolean dfs(int node, int parent) {
        visited[node] = true;

        for(int nei: graph.get(node)) {
            if(nei == parent) {
                continue;
            }
            if(visited[nei] || !dfs(nei, node)) {
                return false;
            }
        }

        return true;
    }

    public boolean validTree(int n, int[][] edges) {
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        if(!dfs(0, -1)) {
            return false;
        }

        for(boolean currVisited: visited) {
            if(!currVisited) {
                return false;
            }
        }

        return true;
    }
}




// BFS
// tc: O(v + e), sc: O(v + e)
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();  // node -> List of neighbors
    boolean[] visited;

    public boolean bfs(int node) {
        visited[node] = true;
        Deque<int[]> queue = new ArrayDeque<>();  // [curr, parent]
        queue.offer(new int[]{node, -1});

        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            int curr = item[0];
            int parent = item[1];

            for(int nei: graph.get(curr)) {
                if(nei == parent) {
                    continue;
                }

                if(visited[nei]) {
                    return false;
                }
                visited[nei] = true;
                queue.offer(new int[]{nei, curr});
            }
        }

        return true;
    }

    public boolean validTree(int n, int[][] edges) {
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        if(!bfs(0)) {
            return false;
        }

        for(boolean currVisited: visited) {
            if(!currVisited) {
                return false;
            }
        }

        return true;
    }
}





// Union-Find by Rank and Path Compression
// tc: O(v + e), sc: O(v + e)
class Solution {
    int[] parent;
    int[] rank;

    public int findParent(int node) {
        if(parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    public boolean union(int u, int v) {
        int rootU = findParent(u);
        int rootV = findParent(v);

        if(rootU == rootV) {
            return false;
        }
        else {
            if(rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            }
            else if(rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            }
            else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }

            return true;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) {
            return false;
        }

        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int[] edge: edges) {
            if(!union(edge[0], edge[1])) {
                return false;
            }
        }

        return true;
    }
}
