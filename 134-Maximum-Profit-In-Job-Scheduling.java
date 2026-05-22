// https://leetcode.com/problems/maximum-profit-in-job-scheduling/


// given: startTimes and endTimes for jobs and a profit array for those jobs
// required: maximize profit by choosing non overlapping jobs

// constraints
// array lengths in [1, 50k]
// each startTime, endTime in [1, 10^9]
// each profit in [1, 10k]

// tc: O(2^n), sc: O(n)
class Solution {
    int[][] jobs;   // i -> [startTime, endTime, profit]

    public int dfs(int i) {
        if(i == jobs.length) {
            return 0;
        }

        // Include
        int include = jobs[i][2];
        int nextIdx = -1;
        for(int j = i + 1; j < jobs.length; j++) {
            if(jobs[j][0] >= jobs[i][1]) {
                nextIdx = j;
                break;
            }
        }
        if(nextIdx != -1) {
            include += dfs(nextIdx);
        }

        // Exclude
        int exclude = dfs(i + 1);

        return Math.max(include, exclude);
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        jobs = new int[n][3];
        for(int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        return dfs(0);
    }
}




// tc: O(n^2), sc: O(n)
class Solution {
    int[][] jobs;   // i -> [startTime, endTime, profit]
    Integer[] dp;   // i -> max profit from i to the end

    public int dfs(int i) {
        if(i == jobs.length) {
            return 0;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        // Include
        int include = jobs[i][2];
        int nextIdx = -1;
        for(int j = i + 1; j < jobs.length; j++) {
            if(jobs[j][0] >= jobs[i][1]) {
                nextIdx = j;
                break;
            }
        }
        if(nextIdx != -1) {
            include += dfs(nextIdx);
        }

        // Exclude
        int exclude = dfs(i + 1);

        dp[i] = Math.max(include, exclude);

        return dp[i];
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        jobs = new int[n][3];
        for(int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        dp = new Integer[n];

        return dfs(0);
    }
}



// tc: O(nlogn), sc: O(n)
class Solution {
    int[][] jobs;   // i -> [startTime, endTime, profit]
    Integer[] dp;   // i -> max profit from i to the end

    public int dfs(int i) {
        if(i == jobs.length) {
            return 0;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        // Include
        int include = jobs[i][2];
        int nextIdx = -1;
        int start = i + 1;
        int end = jobs.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(jobs[mid][0] >= jobs[i][1]) {
                nextIdx = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        if(nextIdx != -1) {
            include += dfs(nextIdx);
        }

        // Exclude
        int exclude = dfs(i + 1);

        dp[i] = Math.max(include, exclude);

        return dp[i];
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        jobs = new int[n][3];
        for(int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        dp = new Integer[n];

        return dfs(0);
    }
}