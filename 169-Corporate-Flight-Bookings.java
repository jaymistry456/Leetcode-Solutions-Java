// https://leetcode.com/problems/corporate-flight-bookings/


// given: an array of flight bookings ([first flight, last flight, seats]) and an integer n representing the no. of flights from 1 to n
// required: an array where each index represents the total no. of seats reserved for flight i

// constraints
// length of the array in [1, 20k]
// first, last in [1, n]
// reserced seats in [1, 10k]

// tc: O(n*f), sc: O(1)
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];

        for(int[] booking: bookings) {
            int start = booking[0];
            int end = booking[1];
            int seats = booking[2];

            for(int i = start - 1; i < end; i++) {
                result[i] += seats;
            }
        }

        return result;
    }
}



// tc: O(n + f), sc: O(1)
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n + 1];

        for(int[] booking: bookings) {
            int start = booking[0];
            int end = booking[1];
            int seats = booking[2];

            result[start - 1] += seats;
            result[end] -= seats;
        }

        int currSum = 0;
        for(int i = 0; i < result.length; i++) {
            currSum += result[i];
            result[i] = currSum;
        }

        return Arrays.copyOfRange(result, 0, result.length - 1);
    }
}