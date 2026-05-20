// https://leetcode.com/problems/cheapest-flights-within-k-stops/

// given: an integer n representing the no. of cities, an array of flights, where each element is itself an array of [from, to, cost of from to to], a source city, a destination city and an integer k
// required: return the cheapest flight from source to destination with at most k stops, -1 if no such flight exists

// constraints
// n in [2, 100]
// length of flights in [0, n*(n - 1)/2]
// price of each flight in [1, 10k]
// src, dest and k in [0, n]
// src != dest

// DFS
// tc: O(n^(k + 1)), sc: O(n^2) -> starting from n choices, the recursion can go till k + 1
class Solution {
    Map<Integer, List<int[]>> map = new HashMap<>();   // node -> List of (neighbors, cost from node to neighbor)
    int result = Integer.MAX_VALUE;

    public void dfs(int curr, int dst, int currCost, int stopsLeft) {
        if(curr == dst) {
            result = Math.min(result, currCost);   // valid path
            return;
        }

        if(stopsLeft == 0) {
            return;   // invalid path
        }

        if(currCost >= result) {
            return;   // pruning costlier paths
        }

        for(int[] neighbor: map.get(curr)) {
            int nei = neighbor[0];
            int currToNei = neighbor[1];
            int srcToNei = currCost + currToNei;
            dfs(nei, dst, srcToNei, stopsLeft - 1);
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int[] flight: flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];
            map.get(u).add(new int[]{v, cost});
        }

        dfs(src, dst, 0, k + 1);

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}




// BFS
// tc: O(n^(k + 1)), sc: O(n^(k + 1))
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int[] flight: flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];
            map.get(u).add(new int[]{v, cost});
        }

        Deque<int[]> queue = new ArrayDeque<>();   // [currNode, currCost, stopsLeft]
        queue.offer(new int[]{src, 0, k + 1});

        int result = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            int[] item = queue.poll();
            int curr = item[0];
            int srcToCurr = item[1];
            int stopsLeft = item[2];

            if(curr == dst) {
                result = Math.min(result, srcToCurr);
                continue;
            }

            if(stopsLeft == 0) {
                continue;
            }

            if(srcToCurr >= result) {
                continue;
            }

            for(int[] neighbor: map.get(curr)) {
                int nei = neighbor[0];
                int currToNei = neighbor[1];
                int srcToNei = srcToCurr + currToNei;

                queue.offer(new int[]{nei, srcToNei, stopsLeft - 1});
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}





// PriorityQueue
// // tc: O(n^(k + 1)*log(n^(k + 1))), sc: O(n^(k + 1))
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int[] flight: flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];
            map.get(u).add(new int[]{v, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );   // [currNode, currCost, stopsLeft]

        pq.offer(new int[]{src, 0, k + 1});
        int result = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            int[] item = pq.poll();
            int curr = item[0];
            int srcToCurr = item[1];
            int stopsLeft = item[2];

            if(curr == dst) {
                return srcToCurr;
            }

            if(stopsLeft == 0) {
                continue;
            }

            for(int[] neighbor: map.get(curr)) {
                int nei = neighbor[0];
                int currToNei = neighbor[1];
                int srcToNei = srcToCurr + currToNei;

                pq.offer(new int[]{nei, srcToNei, stopsLeft - 1});
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}




// Optimal
// Can we think about this in terms of BFS? at each hop, we want to update the cost 
// of every single city (node) -> we want to answer that at hop == 2, what is the 
// min cost to reach a given city, then at hop 3, what is the mincost to reach that 
// city and so on until K + 1 hops (k stops)
// At the end of the iteration of iteration, we are guaranteed that we have calculated the min cost to reach every given city from src within k stops.
// Assuming that a graph is fully connected n*(n - 1)/2 edges, we explore every single flight in every single iteration (k + 1 iterations)
// tc: O((k + 1)*(n^2)), sc: O(n)
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        for(int i = 0; i < k + 1; i++) {
            int[] temp = minCost.clone();

            for(int[] flight: flights) {

                int curr = flight[0];
                int nei = flight[1];
                int srcToCurr = minCost[curr];
                int currToNei = flight[2];

                if(srcToCurr == Integer.MAX_VALUE) {
                    continue;
                }

                temp[nei] = Math.min(temp[nei], srcToCurr + currToNei);
            }

            minCost = temp;
        }

        int result = minCost[dst];

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}