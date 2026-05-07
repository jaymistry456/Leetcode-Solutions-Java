// https://leetcode.com/problems/minimum-height-trees/

// given: an integer n and a 2D array of edges
// required: return the roots of all minimum height trees

// constraints
// n in [1, 20k]
// length of edges = n - 1
// each edge is distinct
// the input is guaranteed to be a tree

// DFS
// tc: O(n^2), sc: O(n)
class Solution {
    List<List<Integer>> graph = new ArrayList<>();   // node -> List of neighbors

    public int dfs(int curr, int parent) {
        int height = 0;

        for(int nei: graph.get(curr)) {
            if(nei != parent) {
                height = Math.max(height, dfs(nei, curr));
            }
        }

        return 1 + height;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 1. Build the graph
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        // 2. Traverse the graph with considering each node as the root,
        // calculate the minHeight possible and also have a map which stores
        // the max height possible for each node
        Map<Integer, Integer> map = new HashMap<>();   // node -> max height (node as root)
        int minHeight = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int currMaxHeight = dfs(i, -1);
            map.put(i, currMaxHeight);
            minHeight = Math.min(minHeight, currMaxHeight);
        }

        // 3. Get the min height nodes from the map and return the result
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if(value == minHeight) {
                result.add(key);
            }
        }

        return result;
    }
}




// BFS
// tc: O(n^2), sc: O(n)
class Solution {
    List<List<Integer>> graph = new ArrayList<>();   // node -> List of neighbors

    public int bfs(int node) {
        Deque<int[]> queue = new ArrayDeque<>();   // [node, parent]
        queue.offer(new int[]{node, -1});

        int height = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] item = queue.poll();
                int curr = item[0];
                int parent = item[1];

                for(int nei: graph.get(curr)) {
                    if(nei != parent) {
                        queue.offer(new int[]{nei, curr});
                    }
                }
            }

            height++;
        }

        return height;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 1. Build the graph
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        // 2. Traverse the graph with considering each node as the root,
        // calculate the minHeight possible and also have a map which stores
        // the max height possible for each node
        Map<Integer, Integer> map = new HashMap<>();   // node -> max height (node as root)
        int minHeight = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int currMaxHeight = bfs(i);
            map.put(i, currMaxHeight);
            minHeight = Math.min(minHeight, currMaxHeight);
        }

        // 3. Get the min height nodes from the map and return the result
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if(value == minHeight) {
                result.add(key);
            }
        }

        return result;
    }
}