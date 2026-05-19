// https://neetcode.io/problems/meeting-schedule-ii/


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

// given: intervals array of meetings
// required: how many rooms required to complete all the meetings parallely

// constraints
// length of the array in [0, 500]
// each start, end in [0, 1m]
// same end and start times are valid

// tc: O(nlogn), sc: O(n)
class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        List<int[]> events = new ArrayList<>();
        for(Interval interval: intervals) {
            events.add(new int[]{interval.start, 1});
            events.add(new int[]{interval.end, -1});
        }
        Collections.sort(events, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int result = 0;
        int curr = 0;
        for(int i = 0; i < events.size(); i++) {
            int[] event = events.get(i);
            curr += event[1];
            result = Math.max(result, curr);
        }

        return result;
    }
}
