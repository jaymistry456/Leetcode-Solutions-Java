/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
*/

// given: a list representing the schedule of each employee sorted in asc order for each employee
// required: return the list of intervals which represent free time occuring in every employee's schedule

// constraints
// schedule and schedule[i] are of length [1, 50]
// start, end time are in [0, 10^8]

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

// tc: O(nlogn), sc: O(n)
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // 1. Merge all intervals in every schedule into a common schedule
        List<Interval> flattenedSchedule = new ArrayList<>();
        for(List<Interval> currSchedule: schedule) {
            for(Interval currInterval: currSchedule) {
                flattenedSchedule.add(currInterval);
            }
        }
        
        // 2. Sort the flattened schedule in asc order of start times
        Collections.sort(flattenedSchedule, (a, b) -> Integer.compare(a.start, b.start));
        
        // 3. Calculate the free time from the flattened schedule
        List<Interval> result = new ArrayList<>();
        int prevEnd = flattenedSchedule.get(0).end;
        for(int i = 1; i < flattenedSchedule.size(); i++) {
            Interval curr = flattenedSchedule.get(i);
            if(prevEnd < curr.start) {
                result.add(new Interval(prevEnd, curr.start));
            }
            prevEnd = Math.max(prevEnd, curr.end);
        }
        
        return result;
    }
}