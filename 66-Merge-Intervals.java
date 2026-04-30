// https://leetcode.com/problems/merge-intervals/


// given: a 2D array of intervals
// required: merge all overlapping intervals

// constraints
// length of intervals in [1, 10k]
// start, end in [0, 10k]
// [1,4] and [4,7] are considered overlapping

// tc: O(nlogn), sc: O(n)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        int i = 0;
        while(i < intervals.length) {
            int[] curr = intervals[i];
            while(i + 1 < intervals.length && intervals[i + 1][0] <= curr[1]) {
                curr[1] = Math.max(curr[1], intervals[i + 1][1]);
                i++;
            }
            result.add(curr);
            i++;
        }

        int[][] resultArray = new int[result.size()][2];
        for(int j = 0; j < result.size(); j++) {
            resultArray[j] = result.get(j);
        }

        return resultArray;
    }
}