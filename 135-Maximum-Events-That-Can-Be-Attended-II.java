// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/


// given: an array of events, each event being [start, end, value] and an integer k
// required: attend as many non overlapping events possible in such a way to maximize values

// constraints
// k in [1, events.length]
// each start, end value in [1, 10^9]
// each value in [1, 10^6]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[][] events, int k, int i) {
        if(i == events.length) {
            return 0;
        }

        if(k == 0) {
            return 0;
        }

        // Include
        int include = events[i][2];
        int nextIdx = -1;
        for(int j = i + 1; j < events.length; j++) {
            if(events[j][0] > events[i][1]) {
                nextIdx = j;
                break;
            }
        }
        if(nextIdx != -1) {
            include += dfs(events, k - 1, nextIdx);
        }

        // Exclude
        int exclude = dfs(events, k, i + 1);

        return Math.max(include, exclude);
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        return dfs(events, k, 0);
    }
}




// tc: O(k * n^2), sc: O(n*k)
class Solution {
    Integer[][] dp;   // i, k -> max values achieved from attending k events from i to end

    public int dfs(int[][] events, int k, int i) {
        if(i == events.length) {
            return 0;
        }

        if(k == 0) {
            return 0;
        }

        if(dp[i][k] != null) {
            return dp[i][k];
        }

        // Include
        int include = events[i][2];
        int nextIdx = -1;
        for(int j = i + 1; j < events.length; j++) {
            if(events[j][0] > events[i][1]) {
                nextIdx = j;
                break;
            }
        }
        if(nextIdx != -1) {
            include += dfs(events, k - 1, nextIdx);
        }

        // Exclude
        int exclude = dfs(events, k, i + 1);

        dp[i][k] = Math.max(include, exclude);

        return dp[i][k];
    }

    public int maxValue(int[][] events, int k) {
        dp = new Integer[events.length][k + 1];

        Arrays.sort(events, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        return dfs(events, k, 0);
    }
}



// tc: O(k * nlogn), sc: O(n*k)
class Solution {
    Integer[][] dp;   // i, k -> max values achieved from attending k events from i to end

    public int dfs(int[][] events, int k, int i) {
        if(i == events.length) {
            return 0;
        }

        if(k == 0) {
            return 0;
        }

        if(dp[i][k] != null) {
            return dp[i][k];
        }

        // Include
        int include = events[i][2];
        int nextIdx = -1;
        int start = i + 1;
        int end = events.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(events[mid][0] > events[i][1]) {
                nextIdx = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        if(nextIdx != -1) {
            include += dfs(events, k - 1, nextIdx);
        }

        // Exclude
        int exclude = dfs(events, k, i + 1);

        dp[i][k] = Math.max(include, exclude);

        return dp[i][k];
    }

    public int maxValue(int[][] events, int k) {
        dp = new Integer[events.length][k + 1];

        Arrays.sort(events, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        return dfs(events, k, 0);
    }
}