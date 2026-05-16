// https://leetcode.com/problems/powx-n/


// given: a double x and an integer n
// required: return x^n

// constraints
// x in [-100, 100]
// n in [-2^31, 2^31 - 1]
// x^n in [-10^4, 10^4]

// tc: O(n), sc: O(1)
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1;
        }

        if(n < 0) {
            x = 1 / x;
            n = -n;
        }

        double result = 1;

        for(int i = 0; i < n; i++) {
            result *= x;
        }

        return result;
    }
}




// tc: O(logn), sc: O(1)
class Solution {
    public double helper(double x, long n) {
        if(n == 0) {
            return 1;
        }

        double result = 1;
        if(n % 2 == 1) {
            result = x;
        }

        double temp = helper(x, n / 2);
        result *= temp * temp;

        return result;
    }

    public double myPow(double x, int n) {
        long N = n;
        if(N < 0) {
            x = 1 / x;
            N = -N;
        }

        return helper(x, N);
    }
}