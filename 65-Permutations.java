// https://leetcode.com/problems/permutations/


// given: an array of integers
// required: return all possible permutations of the numbers

// constraints
// length of nums in [1, 6]
// each value in [-10, 10]
// each value is unique

// tc: O(n!*n), sc: O(n)
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currResult = new ArrayList<>();
    boolean[] isAdded;

    public void dfs(int[] nums) {
        if(currResult.size() == nums.length) {
            result.add(new ArrayList<>(currResult));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(isAdded[i]) {
                continue;
            }

            currResult.add(nums[i]);
            isAdded[i] = true;
            dfs(nums);
            currResult.remove(currResult.size() - 1);
            isAdded[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        isAdded = new boolean[nums.length];

        dfs(nums);

        return result;
    }
}