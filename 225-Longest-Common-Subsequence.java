// https://leetcode.com/problems/longest-common-subsequence/


// given: two strings text1 and text2
// required: return the length of the longest common subsequence between the two strings

// constraints
// length of both strings in [1, 1000]
// both strings only contains lowercase letters

// tc: O(2^(m+n)), sc: O(m+n)
class Solution {
    public int dfs(String s1, String s2, int i, int j) {
        if(i == s1.length() || j == s2.length()) return 0;

        // Characters match
        if(s1.charAt(i) == s2.charAt(j)) {
           return 1 + dfs(s1, s2, i + 1, j + 1);
        }
        // Characters don't match
        return Math.max(
            dfs(s1, s2, i + 1, j),
            dfs(s1, s2, i, j + 1)
        );
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return dfs(text1, text2, 0, 0);
    }
}




// Top-down
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;   // [i, j] -> longest common subsequence from [i, j] to the end of both strings

    public int dfs(String s1, String s2, int i, int j) {
        if(i == s1.length() || j == s2.length()) return 0;

        if(dp[i][j] != null) return dp[i][j];

        // Characters match
        if(s1.charAt(i) == s2.charAt(j)) {
           dp[i][j] = 1 + dfs(s1, s2, i + 1, j + 1);
        }
        // Characters don't match
        else {
            dp[i][j] = Math.max(
                dfs(s1, s2, i + 1, j),
                dfs(s1, s2, i, j + 1)
            );

        }

        return dp[i][j];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        dp = new Integer[text1.length()][text2.length()];

        return dfs(text1, text2, 0, 0);
    }
}




// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];   // [i, j] -> longest common subsequence from [i, j] to the end of both strings
        
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                // Characters match
                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
                // Characters don't match
                else {
                    dp[i][j] = Math.max(
                        dp[i + 1][j],
                        dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }
}