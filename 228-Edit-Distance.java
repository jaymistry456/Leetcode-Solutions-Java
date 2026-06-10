// https://leetcode.com/problems/edit-distance/


// given: two strings word1 and word2
// required: return the minimum no. of operations required to convert word1 to word2 where each operation can be either of insert, delete or replace

// constraints
// length of both words in [0, 500]
// both words contains lowercase letters only

// tc: O(2^(m+n)), sc: O(m+n)
class Solution {
    int m;
    int n;

    public int dfs(String s1, String s2, int i, int j) {
        if(i == m) return n - j;
        if(j == n) return m - i;

        // Characters match
        if(s1.charAt(i) == s2.charAt(j)) {
            return dfs(s1, s2, i + 1, j + 1);
        }
        // Characters don't match
        int insert = 1 + dfs(s1, s2, i, j + 1);
        int replace = 1 + dfs(s1, s2, i + 1, j + 1);
        int delete = 1 + dfs(s1, s2, i + 1, j);
        
        return Math.min(
            insert,
            Math.min(
                replace,
                delete
            )
        );
    }

    public int minDistance(String word1, String word2) {
        m = word1.length();
        n = word2.length();

        return dfs(word1, word2, 0, 0);
    }
}




// Top-down
// tc: O(m*n), sc: O(m*n)
class Solution {
    int m;
    int n;
    Integer[][] dp;   // [i, j] -> no. of operations to convert word1 to word2 from [i, j] state

    public int dfs(String s1, String s2, int i, int j) {
        if(i == m) return n - j;
        if(j == n) return m - i;

        if(dp[i][j] != null) return dp[i][j];

        // Characters match
        if(s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = dfs(s1, s2, i + 1, j + 1);
            return dp[i][j];
        }
        // Characters don't match
        int insert = 1 + dfs(s1, s2, i, j + 1);
        int replace = 1 + dfs(s1, s2, i + 1, j + 1);
        int delete = 1 + dfs(s1, s2, i + 1, j);
        
        dp[i][j] = Math.min(
            insert,
            Math.min(
                replace,
                delete
            )
        );
        return dp[i][j];
    }

    public int minDistance(String word1, String word2) {
        m = word1.length();
        n = word2.length();

        dp = new Integer[m][n];

        return dfs(word1, word2, 0, 0);
    }
}




// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1]; // [i, j] -> no. of operations to convert word1 to word2 from [i, j] state

        for(int i = m; i >= 0; i--) {
            for(int j = n; j >= 0; j--) {
                if(i == m) {
                    dp[i][j] = n - j;
                }
                else if(j == n) {
                    dp[i][j] = m - i;
                }
                else if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(
                        dp[i + 1][j],
                        Math.min(
                            dp[i + 1][j + 1],
                            dp[i][j + 1]
                        )
                    );
                }
            }
        }

        return dp[0][0];
    }
}