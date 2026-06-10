// https://leetcode.com/problems/interleaving-string/


// given: three string s1, s2 and s3
// required: check s3 can be formed by interleaving s1 and s2

// constraints
// length of s1 and s2 in [0, 100]
// length of s3 in [0, 200]
// all strings contain lowercase letters only

// tc: O(2^(m+n)), sc: O(m+n)
class Solution {
    int m;
    int n;
    int k;

    public boolean dfs(String s1, String s2, String s3, int i, int j) {
        if(i == m && j == n) return true;

        // s1 matches
        if(i < m && s1.charAt(i) == s3.charAt(i + j) && dfs(s1, s2, s3, i + 1, j)) {
            return true;
        }
        // s2 matches
        if(j < n && s2.charAt(j) == s3.charAt(i + j) && dfs(s1, s2, s3, i, j + 1)) {
            return true;
        }
        return false;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        k = s3.length();

        if(m + n != k) return false;

        return dfs(s1, s2, s3, 0, 0);
    }
}






// HashMap
// tc: O(m*n), sc: O(m*n)
record Key(int i, int j) {}

class Solution {
    int m;
    int n;
    int k;
    Map<Key, Boolean> map = new HashMap<>();   // [i, j] -> true/false

    public boolean dfs(String s1, String s2, String s3, int i, int j) {
        if(i == m && j == n) return true;

        Key key = new Key(i, j);
        if(map.containsKey(key)) return map.get(key);
        
        // s1 matches
        if(i < m && s1.charAt(i) == s3.charAt(i + j) && dfs(s1, s2, s3, i + 1, j)) {
            map.put(key, true);
            return true;
        }
        // s2 matches
        if(j < n && s2.charAt(j) == s3.charAt(i + j) && dfs(s1, s2, s3, i, j + 1)) {
            map.put(key, true);
            return true;
        }

        map.put(key, false);
        return false;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        k = s3.length();

        if(m + n != k) return false;

        return dfs(s1, s2, s3, 0, 0);
    }
}





// Array
// tc: O(m*n), sc: O(m*n)
record Key(int i, int j) {}

class Solution {
    int m;
    int n;
    int k;
    Boolean[][] dp;   // [i, j] -> true/false

    public boolean dfs(String s1, String s2, String s3, int i, int j) {
        if(i == m && j == n) return true;

        if(dp[i][j] != null) return dp[i][j];

        // s1 matches
        if(i < m && s1.charAt(i) == s3.charAt(i + j) && dfs(s1, s2, s3, i + 1, j)) {
            dp[i][j] = true;
            return true;
        }
        // s2 matches
        if(j < n && s2.charAt(j) == s3.charAt(i + j) && dfs(s1, s2, s3, i, j + 1)) {
            dp[i][j] = true;
            return true;
        }

        dp[i][j] = false;
        return false;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        k = s3.length();

        dp = new Boolean[m + 1][n + 1];

        if(m + n != k) return false;

        return dfs(s1, s2, s3, 0, 0);
    }
}





// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int k = s3.length();

        if(m + n != k) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];   // [i, j] -> true/false

        for(int i = m; i >= 0; i--) {
            for(int j = n; j >= 0; j--) {
                if(i == m && j == n) {
                    dp[i][j] = true;
                }
                // s1 matches
                if(i < m && s1.charAt(i) == s3.charAt(i + j) && dp[i + 1][j]) {
                    dp[i][j] = true;
                }
                // s2 matches
                else if(j < n && s2.charAt(j) == s3.charAt(i + j) && dp[i][j + 1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[0][0];
    }
}