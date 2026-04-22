// https://leetcode.com/problems/missing-number/


// given: an array of integers containing n integers in [0, n]
// required: return the number which is missing from array

// constraints
// length of the array in [1, 10k]
// each value in [0, n]

// tc: O(n), sc: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        for(int i = 0; i <= nums.length; i++) {
            xor ^= i;
        }

        for(int num: nums) {
            xor ^= num;
        }

        return xor;
    }
}