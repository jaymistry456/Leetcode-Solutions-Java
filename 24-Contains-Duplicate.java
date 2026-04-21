// https://leetcode.com/problems/contains-duplicate/


// given: an array of integers
// required: check whether there is a value which appears twice

// constraints
// length of the array in [1, 100k]
// each value in [-10^9, 10^9]

// tc: O(n^2), sc: O(1)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] == nums[i]) {
                    return true;
                }
            }
        }

        return false;
    }
}



// tc: O(n), sc: O(n)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
        }

        return false;
    }
}