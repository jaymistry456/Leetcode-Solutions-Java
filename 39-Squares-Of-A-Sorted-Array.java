// https://leetcode.com/problems/squares-of-a-sorted-array/


// given: an array of integers
// required: return an array of the squares of each number in the given array in sorted order

// constraints
// length of the array in [1, 10k]
// each value in [-10k, 10k]

// tc: O(n), sc: O(1)
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int left = 0;
        int right = n - 1;
        int i = n - 1;
        while(left <= right) {
            if(Math.abs(nums[left]) >= Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            }
            else {
                result[i] = nums[right] * nums[right];
                right--;
            }
            i--;
        }

        return result;
    }
}