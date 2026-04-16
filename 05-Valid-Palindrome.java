// https://leetcode.com/problems/valid-palindrome/description/


// given: a string s
// required: remove all non-alphanumeric chars and convert each character to lowercase and check whether the string is a palindrome

// constraints
// length of the string s in [1, 200k]

// tc: O(n), sc: O(1)
class Solution {
    public boolean isAlphaNumeric(char ch) {
        return (
            (ch >= 'a' && ch <= 'z') ||
            (ch >= 'A' && ch <= 'Z') ||
            (ch >= '0' && ch <= '9')
        );
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while(left < right) {
            while(left < right && !isAlphaNumeric(s.charAt(left))) {
                left++;
            }
            while(left < right && !isAlphaNumeric(s.charAt(right))) {
                right--;
            }
            if(left < right) {
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
            }
            left++;
            right--;
        }

        return true;
    }
}