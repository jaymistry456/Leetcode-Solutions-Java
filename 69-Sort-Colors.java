// https://leetcode.com/problems/sort-colors/


// given: an array of numbers
// required: sort the numbers in ascending order without using sort() function

// constraints
// length of the array in [1, 300]
// each number is 0, 1 or 2

// tc: O(n), sc: O(1)
class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int mid = 0;
        int right = nums.length - 1;

        while(mid <= right) {
            if(nums[mid] == 0) {
                int temp = nums[left];
                nums[left] = nums[mid];
                nums[mid] = temp;
                left++;
                mid++;
            }
            else if(nums[mid] == 1) {
                mid++;
            }
            else {
                int temp = nums[mid];
                nums[mid] = nums[right];
                nums[right] = temp;
                right--;
            }
        }
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public void sortColors(int[] nums) {
        // Traverse in reverse and move all the 2s as far to the right as possible
        int pos = nums.length - 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] == 2) {
                int temp = nums[pos];
                nums[pos] = nums[i];
                nums[i] = temp;
                pos--;
            }
        }

        // Traverse from the front and move all the 0s as far to the left as possible
        pos = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                int temp = nums[pos];
                nums[pos] = nums[i];
                nums[i] = temp;
                pos++;
            }
        }
    }
}