// https://leetcode.com/problems/distinct-subsequences/


// given: two strings s and t
// required: return the no. of distinct subsequences of s which equals t

// constraints
// length of both strings in [1, 1000]
// s and t contain only English letters

// tc: O(2^(m+n)), sc: O(m+n)
class Solution {
    public int dfs(String s, String t, int i, int j) {
        if(j == t.length()) return 1;
        if(i == s.length()) return 0;
        
        // Characters match
        if(s.charAt(i) == t.charAt(j)) {
            return (
                dfs(s, t, i + 1, j + 1) + 
                dfs(s, t, i + 1, j)
            );
        }
        // Characters don't match
        return dfs(s, t, i + 1, j);
    }

    public int numDistinct(String s, String t) {
        return dfs(s, t, 0, 0);
    }
}




// Top-down
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;   // [i, j] -> no. of distinct subsequences from this state

    public int dfs(String s, String t, int i, int j) {
        if(j == t.length()) return 1;
        if(i == s.length()) return 0;

        if(dp[i][j] != null) return dp[i][j];
        
        // Characters match
        if(s.charAt(i) == t.charAt(j)) {
            dp[i][j] = (
                dfs(s, t, i + 1, j + 1) + 
                dfs(s, t, i + 1, j)
            );
            return dp[i][j];
        }
        // Characters don't match
        dp[i][j] = dfs(s, t, i + 1, j);
        return dp[i][j];
    }

    public int numDistinct(String s, String t) {
        dp = new Integer[s.length()][t.length()];

        return dfs(s, t, 0, 0);
    }
}




// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i = m; i >= 0; i--) {
            for(int j = n; j >= 0; j--) {
                if(j == n) {
                    dp[i][j] = 1;
                }
                else if(i == m) {
                    dp[i][j] = 0;
                }
                else if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                }
                else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }
}