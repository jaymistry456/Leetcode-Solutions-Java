// https://leetcode.com/problems/palindrome-partitioning/


// given: a string s
// required: return the no. of possible palindrome possible by partitioning the string

// constraints
// length of s in [1, 16]
// s contains only lowercase letters

// tc: O(n * 2^n), sc: O(n)
class Solution {
    List<List<String>> result = new ArrayList<>();
    List<String> currResult = new ArrayList<>();

    public boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public void dfs(String s, int i) {
        if(i == s.length()) {
            result.add(new ArrayList<>(currResult));
            return;
        }

        for(int j = i; j < s.length(); j++) {
            if(isPalindrome(s, i, j)) {
                currResult.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                currResult.remove(currResult.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        dfs(s, 0);

        return result;
    }
}





// tc: O(n * 2^n), sc: O(n^2)
class Solution {
    boolean[][] dp;   // [i, j] -> whether s[i:j] is a palindrome or not
    List<List<String>> result = new ArrayList<>();
    List<String> currResult = new ArrayList<>();

    public boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public void dfs(String s, int i) {
        if(i == s.length()) {
            result.add(new ArrayList<>(currResult));
            return;
        }

        for(int j = i; j < s.length(); j++) {
            if(dp[i][j]) {
                currResult.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                currResult.remove(currResult.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                dp[i][j] = isPalindrome(s, i, j);
            }
        }

        dfs(s, 0);

        return result;
    }
}