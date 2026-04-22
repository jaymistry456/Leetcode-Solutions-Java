// https://leetcode.com/problems/reverse-bits/


// given: a 32-bit integer n
// required: reverse the bit of the integer and return it

// constraints
// n in [0, 2^31 - 2]
// n is even

// tc: O(logn), sc: O(1)
class Solution {
    public int reverseBits(int n) {
        int result = 0;

        for(int i = 0; i < 32; i++) {
            result = result * 2 + n % 2;
            n /= 2;
        }

        return result;
    }
}