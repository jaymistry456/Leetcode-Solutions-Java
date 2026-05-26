// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/


// given: an array of sorted integers
// required: update the array such that each element appears atmost twice

// constraints
// length of the array in [1, 30k]
// each value in [-10k, 10k]

// tc: O(n), sc: O(1)
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int pos = 0;
        int i = 0;

        while(i < n) {
            nums[pos] = nums[i];
            pos++;
            i++;
            if(i < n && nums[i] == nums[i - 1]) {
                nums[pos] = nums[i];
                pos++;
                i++;
                while(i < n && nums[i] == nums[i - 1]) {
                    i++;
                }
            }
        }

        return pos;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int pos = 0;
        int i = 0;
        int k = 2;

        while(i < n) {
            if(pos - k < 0 || nums[pos - k] != nums[i]) {
                nums[pos] = nums[i];
                pos++;
            }
            i++;
        }

        return pos;
    }
}