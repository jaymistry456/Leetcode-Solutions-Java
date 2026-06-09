// https://neetcode.io/problems/count-connected-components/


// given: an integer n representing the no. of nodes in the graph and a 2D array of edges where each [u, v] represents an undirected edge between u and v
// required: return the no. of connected components in the graph

// constraints
// n in [1, 2000]
// length of edges in [1, 5000]
// u, v in [0, n]
// u != v

// DFS
// tc: O(n), sc: O(n)
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();   // node -> List of neighbors
    Set<Integer> visited = new HashSet<>();

    public void dfs(int node) {
        visited.add(node);

        for(int neighbor: graph.getOrDefault(node, new ArrayList<>())) {
            if(!visited.contains(neighbor)) dfs(neighbor);
        }
    }

    public int countComponents(int n, int[][] edges) {
        // 1. Build the graph
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 2. Traverse each node if not already traversed (visited set)
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                dfs(i);
                result++;
            }
        }

        return result;
    }
}





// BFS
// tc: O(n), sc: O(n)
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();   // node -> List of neighbors
    Set<Integer> visited = new HashSet<>();

    public void bfs(int node) {
        visited.add(node);

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(node);

        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            for(int neighbor: graph.getOrDefault(currNode, new ArrayList<>())) {
                if(!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        // 1. Build the graph
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 2. Traverse each node if not already traversed (visited set)
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                bfs(i);
                result++;
            }
        }

        return result;
    }
}






// Union-Find
// tc: O(n), sc: O(n)
class Solution {
    int[] parent;
    int[] rank;

    public int findParent(int u) {
        if(parent[u] != u) {
            parent[u] = findParent(parent[u]);
        }
        return parent[u];
    }

    public boolean union(int u, int v) {
        int rootU = findParent(u);
        int rootV = findParent(v);

        if(rootU == rootV) {   // Union not possible as both nodes are already in the same group
            return false;
        } else {
            if(rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if(rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }

            return true;
        }
    }

    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;

        int result = n;
        for(int[] edge: edges) {
            if(union(edge[0], edge[1])) result--;
        }

        return result;
    }
}
