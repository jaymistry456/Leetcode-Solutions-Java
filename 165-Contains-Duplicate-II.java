// https://leetcode.com/problems/contains-duplicate-ii/


// given: an array of integers and an integer k
// required: check whether there exists 2 integers a and b at positions i and j where a == b and j - i <= k

// constraints
// length of nums in [1, 100k]
// each value in [-10^9, 10^9]
// k in [0, 100k]

// tc: O(n^2), sc: O(1)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < i + k + 1; j++) {
                if (j >= nums.length) break;

                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();   // num -> idx of num

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }
}