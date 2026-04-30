// https://leetcode.com/problems/search-in-rotated-sorted-array/


// given: an array of integers and an integer target
// required: search the target in the sorted rotated array

// constraints
// length of nums in [1, 5000]
// each value, target in [-10k, 10k]
// each value is unique

// tc: O(logn), sc: O(1)
class Solution {
    public int binarySearch(int[] nums, int start, int end, int target) {
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int idx = 0;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            
            if(nums[mid] <= nums[n - 1]) {
                idx = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        if(idx != 0) {
            idx %= n;
        }
        if(target <= nums[n - 1]) {
            return binarySearch(nums, idx, n - 1, target);
        }
        return binarySearch(nums, 0, idx - 1, target);
    }
}