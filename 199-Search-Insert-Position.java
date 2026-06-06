// https://leetcode.com/problems/search-insert-position/


// given: an array of integers sorted in ascending order and an integer target
// required: return index if the target is found or where it would be if not found

// constraints
// length of nums in [1, 10k]
// each value, target in [-10k, 10k]
// all values are distinct

// tc: O(logn), sc: O(1)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int result = nums.length;
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(nums[mid] < target) {
                start = mid + 1;
            } else {
                result = mid;
                end = mid - 1;
            }
        }

        return result;
    }
}