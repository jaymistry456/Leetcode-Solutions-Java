// https://leetcode.com/problems/palindromic-substrings/


// given: a string s
// required: return the no. of palindromic substrings in s

// constraints
// length of s in [1, 1000]
// s contains only lowercase letters

// tc: O(n^3), sc: O(1)
class Solution {
    public boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public int countSubstrings(String s) {
        int n = s.length();

        int result = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(isPalindrome(s, i, j)) result++;
            }
        }

        return result;
    }
}




// tc: O(n^2), sc: O(1)
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();

        int result = 0;

        for(int i = 0; i < n; i++) {
            // Odd length
            int left = i;
            int right = i;
            while(left >= 0 && right < n) {
                if(s.charAt(left) != s.charAt(right)) break;
                left--;
                right++;
                result++;
            }

            // Even length
            left = i;
            right = i + 1;
            while(left >= 0 && right < n) {
                if(s.charAt(left) != s.charAt(right)) break;
                left--;
                right++;
                result++;
            }
        }

        return result;
    }
}