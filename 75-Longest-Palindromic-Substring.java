// https://leetcode.com/problems/longest-palindromic-substring/


// given: a string s
// required: return the longest palindromic substring of s

// constraints
// length of s in [1, 1000]
// s contains only digits and letters

// tc: O(n^3), sc: O(n)
class Solution {
    public boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public String longestPalindrome(String s) {
        int currLongest = 0;
        String result = "";

        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                if(isPalindrome(s, i, j) && j - i + 1 > currLongest) {
                    currLongest = j - i + 1;
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }
}




// tc: O(n^2), sc: O(1)
class Solution {
    public String longestPalindrome(String s) {
        int currLongest = 0;
        int start = -1;
        int end = -1;

        for(int i = 0; i < s.length(); i++) {
            // odd lengths
            int left = i;
            int right = i;
            while(
                left >= 0 
                && right < s.length() 
                && s.charAt(left) == s.charAt(right)
            ) {
                if(right - left + 1 > currLongest) {
                    currLongest = right - left + 1;
                    start = left;
                    end = right;
                }
                left--;
                right++;
            }

            // even lengths
            left = i;
            right = i + 1;
            while(
                left >= 0
                && right < s.length()
                && s.charAt(left) == s.charAt(right)
            ) {
                if(right - left + 1 > currLongest) {
                    currLongest = right - left + 1;
                    start = left;
                    end = right;
                }
                left--;
                right++;
            }
        }

        return s.substring(start, end + 1);
    }
}