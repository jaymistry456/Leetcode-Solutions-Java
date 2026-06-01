// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


// given: a sorted array and an integer target
// required: first and last positions of target in the array if they exits, otherwise [-1, -1]

// constraints
// length of nums in [0, 100k]
// each value, target in [-10^9, 10^9]

// tc: O(logn), sc: O(1)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        // First position
        int first = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                first = mid;
                end = mid - 1;
            } else if(nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // Last position
        int last = -1;
        start = 0;
        end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                last = mid;
                start = mid + 1;
            } else if(nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return new int[]{first, last};
    }
}