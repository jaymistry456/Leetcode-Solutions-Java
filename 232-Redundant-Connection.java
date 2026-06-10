// https://leetcode.com/problems/redundant-connection/


// given: a 2D array of edges, where each [u, v] indicates an undirected edge between u and v (each node is from 1 to n)
// required: return the edge which when removed, turns the graph into a tree (a graph with no cycles and n - 1 edges)

// constraints
// n in [3, 1000]
// all edges are unique
// the graph is connected

// DFS (won't give the correct solution as we have to find the last edge of the edges which form cycle)
// tc: O(n), sc: O(n)
class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();   // node -> List of neighbors
    Set<Integer> visited = new HashSet<>();
    int[] result;

    public void dfs(int curr, int parent) {
        if(visited.contains(curr)) {
            result = new int[]{parent, curr};
            return;
        }

        visited.add(curr);

        for(int neighbor: map.getOrDefault(curr, new ArrayList<>())) {
            if(neighbor != parent) {
                dfs(neighbor, curr);
            }
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());

            map.get(u).add(v);
            map.get(v).add(u);
        }

        dfs(1, -1);

        return result;
    }
}





// BFS (won't give the correct solution as we have to find the last edge of the edges which form cycle)
// tc: O(n), sc: O(n)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();   // node -> List of neighbors
        
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());

            map.get(u).add(v);
            map.get(v).add(u);
        }

        Deque<int[]> queue = new ArrayDeque<>();   // [curr, parent]
        queue.offer(new int[]{1, -1});

        Set<Integer> visited = new HashSet<>();
        visited.add(1);

        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            int curr = item[0];
            int parent = item[1];

            for(int neighbor: map.getOrDefault(curr, new ArrayList<>())) {
                if(neighbor != parent) {
                    if(visited.contains(neighbor)) {
                        return new int[]{curr, neighbor};
                    }

                    queue.offer(new int[]{neighbor, curr});
                    visited.add(neighbor);
                }
            }
        }

        return new int[]{-1, -1};
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

        if(rootU == rootV) {   // Nodes already in the same group, cycle detected
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

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 1; i < n; i++) {
            parent[i] = i;
        }
        
        int[] result = new int[]{-1, -1};

        for(int[] edge: edges) {
            if(!union(edge[0], edge[1])) {
                result = new int[]{edge[0], edge[1]};
            }
        }

        return result;
    }
}