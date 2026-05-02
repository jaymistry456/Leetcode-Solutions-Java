// https://leetcode.com/problems/subsets/


// given: an array of integers
// required: return all possible subsets (power set)

// constraints
// length of nums in [1, 10]
// each value in [-10, 10]
// all numbers are unique

// tc: O(n!), sc: O(n!)
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currResult = new ArrayList<>();

    public void dfs(int[] nums, int i) {
        if(i == nums.length) {
            result.add(new ArrayList<>(currResult));
            return;
        }

        // include
        currResult.add(nums[i]);
        dfs(nums, i + 1);
        currResult.remove(currResult.size() - 1);

        // exclude
        dfs(nums, i + 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);

        return result;
    }
}