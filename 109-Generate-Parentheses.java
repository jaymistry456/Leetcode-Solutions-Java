// https://leetcode.com/problems/generate-parentheses/


// given: an integer n
// required: generate all parentheses

// constraints
// n in [1, 8]

// tc: O(2^n * 2^n * n), sc: O(n)
class Solution {
    List<String> result = new ArrayList<>();
    List<String> currResult = new ArrayList<>();

    public void dfs(int n, int open, int closed) {
        if (closed == n) {
           String currString = String.join("", currResult);
           result.add(currString);
           return;
        }

        if (open < n) {
            currResult.add("(");
            dfs(n, open + 1, closed);
            currResult.remove(currResult.size() - 1);
        }

        if (closed < open) {
            currResult.add(")");
            dfs(n, open, closed + 1);
            currResult.remove(currResult.size() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        dfs(n, 0, 0);

        return result;
    }
}