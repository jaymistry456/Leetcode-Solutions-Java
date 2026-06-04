// https://leetcode.com/problems/minimum-operations-to-make-array-equal-to-target/


// given: two arrays nums and target
// required: minimum no. of operations to make nums equal to target

// constraints
// length of nums, target in [1, 100k]
// each value in [1, 100M]

// tc: O(n), sc: O(1)
class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        long result = 0;
        long prevDiff = 0;

        for(int i = 0; i < nums.length; i++) {
            long currDiff = target[i] - nums[i];

            if((prevDiff <= 0 && currDiff >= 0) || (prevDiff >= 0 && currDiff <= 0)) {   // Both diffs opposite sign
                result += Math.abs(currDiff);
            } else { // Both diffs same sign
                if(prevDiff < 0 && currDiff < prevDiff) {   // Both diffs negative
                    result += prevDiff - currDiff;
                } else if(prevDiff > 0 && currDiff > prevDiff) {   // Both diffs positive
                    result += currDiff - prevDiff;
                }
            }

            prevDiff = currDiff;
        }

        return result;
    }
}