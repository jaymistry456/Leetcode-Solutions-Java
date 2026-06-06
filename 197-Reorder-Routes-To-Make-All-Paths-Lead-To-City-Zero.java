// https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/


// given: an integer n representing the no. of cities and a 2D array connection where each [a, b] means there is a directed edge from a to b
// required: reorient the directed edges in such a way that it is possible to reach city 0 from every other city and return the no. of such reorientations

// constraints
// n in [2, 50k]
// length of connections = n - 1
// a != b

// To reach city 0 from any city, we either need a directed edge from that city to city 0 or we need to be in a path to city 0
// When neither of those 2 conditions are not met, we need to add an edge in reverse direction
// Also, we can not traverse from every other city to city 0, because we wouldn't know which edge to reverse, instead we could traverse in reverse direction from city 0 and try to reach every other city

// DFS
// tc: O(v + e), sc: O(v + e)
class Solution {
    Map<Integer, List<int[]>> map = new HashMap<>(); // u -> [v, cost] cost == 1 for actual edge and 0 for reverse edge as we are traversing in reverse
    Set<Integer> visited = new HashSet<>();

    public int dfs(int u) {
        visited.add(u);

        int result = 0;
        for(int[] edge: map.get(u)) {
            int nei = edge[0];
            int neiCost = edge[1];

            if(!visited.contains(nei)) {
                result += neiCost + dfs(nei);
            }
        }

        return result;
    }

    public int minReorder(int n, int[][] connections) {
        for(int[] currConn: connections) {
            int u = currConn[0];
            int v = currConn[1];

            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());

            map.get(u).add(new int[]{v, 1});
            map.get(v).add(new int[]{u, 0});
        }

        return dfs(0);
    }
}





// BFS
// tc: O(v + e), sc: O(v + e)
class Solution {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<int[]>> map = new HashMap<>(); // u -> [v, cost] cost == 1 for actual edge and 0 for reverse edge as we are traversing in reverse

        for(int[] currConn: connections) {
            int u = currConn[0];
            int v = currConn[1];

            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());

            map.get(u).add(new int[]{v, 1});
            map.get(v).add(new int[]{u, 0});
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        int result = 0;

        while(!queue.isEmpty()) {
            int u = queue.poll();

            for(int[] edge: map.get(u)) {
                int nei = edge[0];
                int neiCost = edge[1];

                if(visited.contains(nei)) continue;

                result += neiCost;
                queue.offer(nei);
                visited.add(nei);
            }
        }

        return result;
    }
}