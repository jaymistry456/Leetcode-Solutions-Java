// https://leetcode.com/problems/reverse-integer/


// given: an integer x
// required: return the reversed integer of x

// constraints
// x in [-2^31, 2^31 - 1]

// tc: O(logn), sc: O(1)
class Solution {
    public int reverse(int x) {
        int sign = 1;
        if(x < 0) {
            sign = -1;
            x = -x;
        }

        int result = 0;
        int MAX_INTEGER = Integer.MAX_VALUE;
        while(x != 0) {
            int remainder = x % 10;
            if((MAX_INTEGER - remainder) / 10 < result) {
                return 0;
            }
            result = result * 10 + remainder;
            x /= 10;
        }

        return sign * result;
    }
}