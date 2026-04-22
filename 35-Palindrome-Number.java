// https://leetcode.com/problems/palindrome-number/


// given: an integer x
// required: check whether x is a palindrome

// constraints
// x in [-2^31, 2^31 - 1]

// tc: O(logn), sc: O(1)
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }

        int temp = x;
        int reverse = 0;
        while(temp != 0) {
            reverse = reverse * 10 + temp % 10;
            temp /= 10;
        }

        return reverse == x;
    }
}