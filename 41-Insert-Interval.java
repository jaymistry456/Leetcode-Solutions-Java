// https://leetcode.com/problems/insert-interval/


// given: a 2D array of intervals sorted in ascending order by start times and an interval newInterval
// required: insert the newInterval in intervals (merge overlapping intervals if necessary)

// constraints
// length of intervals in [0, 10k]
// start, end in [0, 100k]

// tc: O(n), sc: O(1)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        int n = intervals.length;

        List<int[]> result = new ArrayList<>();

        while(i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        while(i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        while(i < n) {
            result.add(intervals[i]);
            i++;
        }

        int[][] resArray = new int[result.size()][2];
        for(int j = 0; j < result.size(); j++) {
            resArray[j] = result.get(j);
        }

        return resArray;
    }
}