// https://leetcode.com/problems/target-sum/


// given: an array of integers nums and an integer target
// required: return the no. of different expressions that can be built from the integer array which eveluate to target assuming only '+' and '-' operations are allowed between any 2 numbers in the array

// constraints
// length of the array in [1, 20]
// each value in [0, 1000]
// target in [-1000, 1000]
// sum of nums in [0, 1000]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] nums, int target, int currSum, int i) {
        if(i == nums.length) {
            if(currSum == target) return 1;
            return 0;
        }

        return (
            dfs(nums, target, currSum + nums[i], i + 1) +
            dfs(nums, target, currSum - nums[i], i + 1)
        );
    }

    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }
}




// tc: O(n^2), sc: O(n*target)
record Key(int currSum, int i) {}

class Solution {
    Map<Key, Integer> dp = new HashMap<>();   // [currSum, i] -> no. of combinations reaching target

    public int dfs(int[] nums, int target, int currSum, int i) {
        if(i == nums.length) {
            if(currSum == target) return 1;
            return 0;
        }

        Key key = new Key(currSum, i);
        if(dp.containsKey(key)) return dp.get(key);

        int result = (
            dfs(nums, target, currSum + nums[i], i + 1) +
            dfs(nums, target, currSum - nums[i], i + 1)
        );
        dp.put(key, result);

        return result;
    }

    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }
}