// https://leetcode.com/problems/binary-search/description/


// given: an array of integers and an integer target
// required: return the index of the target in the array

// constraints
// length of the array in [1, 10k]
// each value, target in [-10k, 10k]
// all values are unique
// array is sorted in ascending order

// recursive
// tc: O(logn), sc: O(logn)
class Solution {
    public int binarySearch(int[] nums, int target, int start, int end) {
        if(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] < target) {
                return binarySearch(nums, target, mid + 1, end);
            }
            else if(nums[mid] > target) {
                return binarySearch(nums, target, start, mid - 1);
            }
            else {
                return mid;
            }
        }

        return -1;
    }
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }
}




// iterative
// tc: O(logn), sc: O(1)
class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] < target) {
                start = mid + 1;
            }
            else if(nums[mid] > target) {
                end = mid - 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }
}