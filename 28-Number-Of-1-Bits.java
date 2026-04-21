// https://leetcode.com/problems/number-of-1-bits/


// given: an integer n
// required: the number of '1's in the binary representation of the integer

// constraints
// n in [1, 2^31 - 1]

// tc: O(logn), sc: O(1)
class Solution {
    public int hammingWeight(int n) {
        int result = 0;

        while(n != 0) {
            result += n % 2;
            n /= 2;
        }

        return result;
    }
}