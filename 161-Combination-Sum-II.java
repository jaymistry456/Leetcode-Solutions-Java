// https://leetcode.com/problems/combination-sum-ii/


// given: an array of candidates and an integer target
// required: find all unique combinations (no duplicate combinations allowed) in candidates where the sum equals target using each number in candidates only once

// constraints
// length of candidates in [1, 100]
// each candidate value in [1, 50]
// target in [1, 30]
// candidates can contain duplicate numbers

// tc: O(2^target), sc: O(target)
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currResult = new ArrayList<>();

    public void dfs(int[] candidates, int target, int i, int currSum) {
        if (currSum == target) {   // Valid
            result.add(new ArrayList<>(currResult));
            return;
        }

        if (currSum > target || i == candidates.length) {   // Invalid
            return;
        }

        // Include
        currResult.add(candidates[i]);
        dfs(candidates, target, i + 1, currSum + candidates[i]);
        currResult.remove(currResult.size() - 1);

        // Skip
        i++;
        while (i < candidates.length && candidates[i] == candidates[i - 1]) {
            i++;
        }
        dfs(candidates, target, i, currSum);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        dfs(candidates, target, 0, 0);

        return result;
    }
}