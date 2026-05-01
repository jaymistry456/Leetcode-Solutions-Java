// https://leetcode.com/problems/word-break/


// given: a string s and a list of words wordDict
// required: check whether s can be broken into a sequence of words in wordDict

// constraints
// length of s in [1, 300]
// length of wordDict in [1, 1000]
// each word's length in wordDict in [1, 20]
// everything in lowercase letters only
// all words in wordDict are unique

// tc: O(n*2^n), sc: O(n+w)
class Solution {
    Set<String> set;

    public boolean dfs(String s, int i) {
        if(i == s.length()) {
            return true;
        }

        for(int j = i; j < s.length(); j++) {
            if(set.contains(s.substring(i, j + 1)) && dfs(s, j + 1)) {
                return true;
            }
        }

        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);

        return dfs(s, 0);
    }
}



// tc: O(n^2), sc: O(n+w)
class Solution {
    Set<String> set;
    Boolean[] dp;   // i -> if [i, s.length() - 1] can be formed from wordDict

    public boolean dfs(String s, int i) {
        if(i == s.length()) {
            return true;
        }

        if(dp[i] != null) {
            return dp[i];
        }

        for(int j = i; j < s.length(); j++) {
            if(set.contains(s.substring(i, j + 1)) && dfs(s, j + 1)) {
                dp[i] = true;
                return true;
            }
        }

        dp[i] = false;
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        dp = new Boolean[s.length()];

        return dfs(s, 0);
    }
}