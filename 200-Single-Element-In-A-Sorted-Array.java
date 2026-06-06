// https://leetcode.com/problems/single-element-in-a-sorted-array/


// given: an array of integers
// required: return the element which appears exactly once in the array where every other element appears twice

// constraints
// length of nums in [1, 100k]
// each value in [0, 100k]
// a simple binary search should do it

// tc: O(n), sc: O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int xor = 0;

        for(int num: nums) {
            xor ^= num;
        }

        return xor;
    }
}




// tc: O(logn), sc: O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int result = -1;
        int start = 0;
        int end = n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(mid % 2 == 1) {
                mid--;
            }

            if(mid + 1 < n && nums[mid] == nums[mid + 1]) {
                start = mid + 2;
            } else {
                result = nums[mid];
                end = mid - 2;
            }
        }

        return result;
    }
}