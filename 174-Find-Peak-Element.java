// https://leetcode.com/problems/find-peak-element/


// given: an array of integers
// required: find the peak element's index

// constraints
// length of the array in [1, 1000]
// each val in [-2^31, 2^31 + 1]
// no consecutive elements are equal

// tc: O(logn), sc: O(1)
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int result = -1;

        int start = 0;
        int end = n - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;

            int left = Integer.MIN_VALUE;
            if(mid - 1 >= 0) {
                left = Math.max(left, nums[mid - 1]);
            }
            int right = Integer.MIN_VALUE;
            if(mid + 1 < n) {
                right = Math.max(right,  nums[mid + 1]);
            }

            if(nums[mid] >= left && nums[mid] >= right) {
                return mid;
            } else if(nums[mid] <= left && nums[mid] <= right) {
                start = mid + 1;
            } else if(nums[mid] <= left &&  nums[mid] >= right) {
                end = mid - 1;
            } else if(nums[mid] >= left &&  nums[mid] <= right){
                start = mid + 1;
            }
        }

        return 0;
    }
}