// https://leetcode.com/problems/network-delay-time/


// given: a 2D array of directed edges with weights, an integer n representing nodes from 1 to n and an integer k from which a signal is started
// required: return the minimum time it takes for the signal to reach all the nodes in the graph

// constraints
// k, n in [1, 100]
// length of the 2D array in [1, 6000]
// weight of each directed edge in [0, 100]

// DFS
// tc: O(v*e), sc: O(v+e)
class Solution {
    Map<Integer, List<int[]>> graph = new HashMap<>();   // u -> List of [v, uToV]
    int result = Integer.MAX_VALUE;
    int[] minTimes;

    public void dfs(int u, int currTime) {
        for(int[] neighbor: graph.getOrDefault(u, new ArrayList<>())) {
            int v = neighbor[0];
            int uToV = neighbor[1];

            int newTime = currTime + uToV;
            if(newTime < minTimes[v]) {
                minTimes[v] = newTime;
                dfs(v, newTime);
            }   
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        for(int[] time: times) {
            int u = time[0];
            int v = time[1];
            int uToV = time[2];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, uToV});
        }

        minTimes = new int[n + 1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        minTimes[k] = 0;

        dfs(k, 0);

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            result = Math.max(result, minTimes[i]);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}





// BFS
// // tc: O(v*e), sc: O(v+e)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();   // u -> List of [v, uToV]
        for(int[] time: times) {
            int u = time[0];
            int v = time[1];
            int uToV = time[2];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, uToV});
        }

        int[] minTimes = new int[n + 1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        minTimes[k] = 0;

        Set<Integer> visited = new HashSet<>();
        visited.add(k);
        
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(k);

        while(!queue.isEmpty()) {
            int u = queue.poll();

            for(int[] neighbor: graph.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor[0];
                int uToV = neighbor[1];

                int newTime = minTimes[u] + uToV;
                if(newTime < minTimes[v]) {
                    minTimes[v] = newTime;
                    queue.offer(v);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            result = Math.max(result, minTimes[i]);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}






// Multi-phase BFS
// // tc: O(elogv), sc: O(v+e)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();   // u -> List of [v, uToV]
        for(int[] time: times) {
            int u = time[0];
            int v = time[1];
            int uToV = time[2];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, uToV});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );   // [v, kToV] sorted by kToV desc

        pq.offer(new int[]{k, 0});

        Set<Integer> visited = new HashSet<>();

        while(!pq.isEmpty()) {
            int[] item = pq.poll();
            int u = item[0];
            int kToU = item[1];
            
            if(visited.contains(u)) continue;
            visited.add(u);

            if(visited.size() == n) return kToU;

            for(int[] neighbor: graph.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor[0];
                int uToV = neighbor[1];

                pq.offer(new int[]{v, kToU + uToV});
            }
        }

        return -1;
    }
}