// https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/


// given: a target array
// required: minimum no. of operations to form a target array from initial

// constraints
// length of target in [1, 100k]
// each value in [1, 100k]

// tc: O(n), sc: O(1)
class Solution {
    public int minNumberOperations(int[] target) {
        int result = target[0];

        for(int i = 1; i < target.length; i++) {
            if(target[i] > target[i - 1]) {
                result += target[i] - target[i - 1];
            }
        }

        return result;
    }
}