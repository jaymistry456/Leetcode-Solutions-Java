// https://leetcode.com/problems/rotate-array


// given: an array of integers and an integer k
// required: rotate the array by k steps and return the array

// constraints
// length of the array in [1, 100k]
// each value in [-2^31, 2^31 - 1]
// k in [0, 100k]

// tc: O(k*n), sc: O(1)
class Solution {
    public void rotateByOnce(int[] nums) {
        int last = nums[nums.length - 1];
        for(int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = last;
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;

        while(k > 0) {
            rotateByOnce(nums);
            k--;
        }
    }
}



// tc: O(n), sc: O(1)
class Solution {
    public void reverseArray(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        reverseArray(nums, 0, n - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, n - 1);
    }
}