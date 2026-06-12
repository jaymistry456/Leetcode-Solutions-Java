// https://leetcode.com/problems/next-permutation/


// given: an array of numbers
// required: the next permutation of those numbers (is the array is the last permutation, then return the first permutation)

// contraints
// length of nums in [1, 100]
// each value in [0, 100]

// tc: O(n), sc: O(1)
class Solution {
    public void reverseElements(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1. Find the first element which is < the element to its right (pivot)
        int pivotIdx = -1;
        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] < nums[i + 1]) {
                pivotIdx = i;
                break;
            }
        }

        // 2. Find the first element is > the pivot element and swap both
        if(pivotIdx == -1) {   // The last permutation
            reverseElements(nums, 0, n - 1);
            return;
        }
        for(int i = n - 1; i >= 0; i--) {
            if(nums[i] > nums[pivotIdx]) {
                int temp = nums[i];
                nums[i] = nums[pivotIdx];
                nums[pivotIdx] = temp;
                break;
            }
        }

        // 3. Reverse the remaining elements after the pivot
        reverseElements(nums, pivotIdx + 1, n - 1);
    }
}