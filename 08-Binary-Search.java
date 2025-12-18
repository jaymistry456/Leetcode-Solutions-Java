// https://leetcode.com/problems/binary-search/description/


class Solution {
    public int recursiveBinarySearch(int start, int end, int[] nums, int target) {
        if(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] < target) {
                return recursiveBinarySearch(mid + 1, end, nums, target);
            }
            else if(nums[mid] > target) {
                return recursiveBinarySearch(start, mid - 1, nums, target);
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        // // iterative
        // // TC: O(logn), SC: O(1)
        // int start = 0;
        // int end = nums.length - 1;
        // while(start <= end) {
        //     int mid = start + (end - start) / 2;
        //     if(nums[mid] < target) {
        //         start = mid + 1;
        //     }
        //     else if(nums[mid] > target) {
        //         end = mid - 1;
        //     }
        //     else {
        //         return mid;
        //     }
        // }
        // return -1;


        // recursive
        // TC: O(logn), SC: O(logn)
        return recursiveBinarySearch(0, nums.length - 1, nums, target);
    }
}