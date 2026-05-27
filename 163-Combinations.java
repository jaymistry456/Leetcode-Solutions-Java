// https://leetcode.com/problems/combinations/


// given: an integer n and an integer k
// required: return all possible combinations of k numbers from [1, n]

// constraints
// n in [1, 20]
// k in [1, n]

// tc: O(k*(n over k)), sc: O(k)
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> currResult = new ArrayList<>();

    public void dfs(int n, int k, int i) {
        if (currResult.size() == k) {
            result.add(new ArrayList<>(currResult));
            return;
        }

        if (i > n) {
            return;
        }

        // Include
        currResult.add(i);
        dfs(n, k, i + 1);
        currResult.remove(currResult.size() - 1);

        // Skip
        dfs(n, k, i + 1);
    }

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1);

        return result;
    }
}