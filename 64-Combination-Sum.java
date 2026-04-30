// https://leetcode.com/problems/combination-sum/


// given: an array of integers and an integer target
// required: return a list of all unique combinations of candidates which sum to target

// constraints
// length of candidates in [1, 30]
// each value in [2, 40]
// all values are unique
// target in [1, 40]
// each value can be chosen unlimited times

// tc: O(n^t), sc: O(t)
class Solution {
    List<List<Integer>> result;
    List<Integer> currResult;

    public void dfs(int[] candidates, int target, int i, int currSum) {
        if(currSum == target) {
            result.add(new ArrayList<>(currResult));
            return;
        }

        if(currSum > target || i == candidates.length) {
            return;
        }

        currResult.add(candidates[i]);
        dfs(candidates, target, i, currSum + candidates[i]);
        currResult.remove(currResult.size() - 1);

        dfs(candidates, target, i + 1, currSum);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        currResult = new ArrayList<>();

        dfs(candidates, target, 0, 0);

        return result;
    }
}