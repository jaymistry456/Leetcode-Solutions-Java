// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/


// given: a 2D array of points where each point [x, y] represents the width of a balloon
// required: the minimum no. of arrows that must be shot to burst all the balloons

// constraints
// length of the array in [1, 100k]
// each x, y in [-2^31, 2^31 - 1]

// Sort by start
// tc: O(nlogn), sc: O(1)
class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });   // Sort by start asc, end asc

        int result = 0;
        long prevEnd = Long.MIN_VALUE;
        for(int i = 0; i < points.length; i++) {
            int currStart = points[i][0];
            int currEnd = points[i][1];

            if((long) currStart <= prevEnd) {
                prevEnd = Math.min(prevEnd, (long) currEnd);
            } else {
                result++;
                prevEnd = (long) currEnd;
            }
        }

        return result;
    }
}




// Sort by end
// tc: O(nlogn), sc: O(1)
class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if(a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });   // Sort by end asc, start asc

        int result = 0;
        long prevEnd = Long.MIN_VALUE;
        for(int i = 0; i < points.length; i++) {
            int currStart = points[i][0];
            int currEnd = points[i][1];

            if((long) currStart > prevEnd) {
                result++;
                prevEnd = (long) currEnd;
            }
        }

        return result;
    }
}