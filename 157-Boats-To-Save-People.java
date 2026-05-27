// https://leetcode.com/problems/boats-to-save-people/


// given: an array of people where each value is the weight of the person and an integer limit
// required: the minimum no. of boats needed to every person given that each boat's weight capacity is limit and it can carry atmost 2 people at a time

// constraints
// length of the people array in [1, 50k]
// each weight and limit in [1, 30k]

// tc: O(nlogn), sc: O(1)
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int result = 0;

        while(left <= right) {
            if(people[left] + people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            result++;
        }

        return result;
    }
}