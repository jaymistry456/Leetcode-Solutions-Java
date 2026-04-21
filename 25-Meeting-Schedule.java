// https://neetcode.io/problems/meeting-schedule


/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

// given: an array of meetings
// required: check whether there are any conflicts among the meetings

// constraints
// length of the array in [0, 500]
// start, end in [0, 1000000]

// tc: O(n^2), sc: O(1)
class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        for(int i = 0; i < intervals.size(); i++) {
            for(int j = 0; j < intervals.size(); j++) {
                if(i == j) {
                    continue;
                }

                if(intervals.get(i).start >= intervals.get(j).end || intervals.get(i).end <= intervals.get(j).start) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }
}



// tc: O(nlogn), sc: O(1)
class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.isEmpty()) {
            return true;
        }

        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        int prevEnd = intervals.get(0).end;
        for(int i = 1; i < intervals.size(); i++) {
            if(intervals.get(i).start < prevEnd) {
                return false;
            }

            prevEnd = intervals.get(i).end;
        }

        return true;
    }
}
