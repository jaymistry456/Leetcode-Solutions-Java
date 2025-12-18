// https://leetcode.com/problems/valid-palindrome/description/


class Solution {
    public boolean isAlphaNumeric(Character ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }

    public boolean isPalindrome(String s) {
        // // brute-force
        // // TC: O(n^2), SC: O(n)
        // String temp = "";
        // for(Character ch: s.toCharArray()) {
        //     if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
        //         temp = temp + Character.toLowerCase(ch);
        //     }
        // }
        // int n = temp.length();
        // for(int i = 0; i < n / 2; i++) {
        //     if(temp.charAt(i) != temp.charAt(n - 1 - i)) {
        //         return false;
        //     }
        // }
        // return true;


        // optimal
        // TC: O(n), SC: O(1)
        int l = 0;
        int r = s.length() - 1;
        while(l < r) {
            while(l < r && !isAlphaNumeric(s.charAt(l))) {
                l++;
            }
            while(l < r && !isAlphaNumeric(s.charAt(r))) {
                r--;
            }
            if(l < r && Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}