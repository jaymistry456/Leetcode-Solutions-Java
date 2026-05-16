// https://leetcode.com/problems/decode-ways/


// given: a string of digits
// required: no. of ways to decode a message by converting each digit to a letter where '1' converts to 'A' and '26' converts to 'Z'

// constraints
// length of s in [1, 100]
// s only contains digits

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(String s, int i) {
        if(i == s.length()) {
            return 1;
        }

        if(s.charAt(i) == '0') {
            return 0;
        }
        
        int result = 0;
        result = dfs(s, i + 1);
        if(i + 1 < s.length()) {
            int num = Integer.parseInt(s.substring(i, i + 2));
            if(num <= 26) {
                result += dfs(s, i + 2);
            }
        }

        return result;
    }

    public int numDecodings(String s) {
        return dfs(s, 0);
    }
}





// tc: O(n), sc: O(n)
class Solution {
    Integer[] dp;   // i -> no. of ways to decode from i to the end of the string

    public int dfs(String s, int i) {
        if(i == s.length()) {
            return 1;
        }

        if(s.charAt(i) == '0') {
            return 0;
        }

        if(dp[i] != null) {
            return dp[i];
        }
        
        int result = 0;
        result = dfs(s, i + 1);
        if(i + 1 < s.length()) {
            int num = Integer.parseInt(s.substring(i, i + 2));
            if(num <= 26) {
                result += dfs(s, i + 2);
            }
        }

        dp[i] = result;

        return result;
    }

    public int numDecodings(String s) {
        dp = new Integer[s.length()];

        return dfs(s, 0);
    }
}