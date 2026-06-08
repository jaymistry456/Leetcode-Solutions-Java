// https://leetcode.com/problems/min-cost-to-connect-all-points/


// given: a 2D array of points [x, y]
// required: minimum manhatten distance to connect all the points

// constraints
// length of points in [1, 1000]
// each x, y in [-10^6, 10^6]
// all points are distinct

// tc: O(n^2 * log(n^2)), sc: O(n^2)
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

        if(rootU == rootV) {
            return false;   // Nodes in the same group, union NOT possible
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

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );   // [u, v, distUV] sorted by distUV asc

        for(int u = 0; u < n; u++) {
            for(int v = 0; v < n; v++) {
                if(u != v) {
                    int distUV = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    pq.offer(new int[]{u, v, distUV});
                }
            }
        }

        int components = n;
        int result = 0;
        while(components != 1) {
            int[] item = pq.poll();
            int u = item[0];
            int v = item[1];
            int distUV = item[2];

            if(union(u, v)) {
                components--;
                result += distUV;
            }
        }

        return result;
    }
}





// tc: O(n^2), sc: O(n)
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        boolean[] isConnected = new boolean[n];

        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        int result = 0;

        for(int i = 0; i < n; i++) {
            // 1. Get the closest node
            int curr = -1;
            int currDist = Integer.MAX_VALUE;

            for(int j = 0; j < n; j++) {
                if(!isConnected[j]) {
                    if(minDist[j] < currDist) {
                        curr = j;
                        currDist = minDist[j];
                    }
                }
            }
            isConnected[curr] = true;
            result += currDist;

            // 2. Recalculate all distances of remaining nodes from the closest node for next round
            for(int j = 0; j < n; j++) {
                if(!isConnected[j]) {
                    minDist[j] = Math.min(minDist[j],
                        Math.abs(points[curr][0] - points[j][0]) +
                        Math.abs(points[curr][1] - points[j][1])
                    );
                }
            }
        }
        
        return result;
    }
}