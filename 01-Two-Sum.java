// https://leetcode.com/problems/two-sum/

// given: an array of integers and an integer target
// required: return the indices of 2 integers whose sum is equal to target

// constraints
// length of the array in [2, 10k]
// each value, target in [-10^9, 10^9]
// only one answer exists

// tc: O(n^2), sc: O(1)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();   // num -> index

        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}