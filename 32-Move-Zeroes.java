// https://leetcode.com/problems/move-zeroes/


// given: an array of integers
// required: moves zeros to the end of the array while maintaining the relative ordering of all the other elements

// constraints
// length of the array in [1, 10k]
// each value in [-2^31, 2^31]

// tc: O(n), sc: O(1)
class Solution {
    public void moveZeroes(int[] nums) {
        int pos = 0;
        int i = 0;

        while(i < nums.length) {
            if(nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
            i++;
        }

        while(pos < nums.length) {
            nums[pos] = 0;
            pos++;
        }
    }
}