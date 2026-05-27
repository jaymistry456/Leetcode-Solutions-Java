// https://leetcode.com/problems/koko-eating-bananas/


// given: an array of banana piles and an integer h
// required: return the minimum banana eating rate per pile, so that it takes atmost h hours to finish eating all of them given that at every hour, at most one pile can be eaten

// constraints
// length of the pile in [1, 10k]
// h in [pile length, 10^9]
// each pile value in [1, 10^9]

// We need to minimize the banana eating rate per hour

// tc: O(max(piles)*n), sc: O(1)
class Solution {
    public boolean isValid (int[] piles, int h, int rate) {
        int currH = 0;

        for (int p: piles) {
            currH += Math.ceil((double) p / rate);

            if (currH > h) {
                return false;
            }
        }

        return true;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (int p: piles) {
            max = Math.max(max, p);
        }

        for (int i = 1; i <= max; i++) {
            if (isValid(piles, h, i)) {
                return i;
            }
        }

        return -1;
    }
}





// tc: O(log(max(piles))*n), sc: O(1)
class Solution {
    public boolean isValid (int[] piles, int h, int rate) {
        int currH = 0;

        for (int p: piles) {
            currH += Math.ceil((double) p / rate);

            if (currH > h) {
                return false;
            }
        }

        return true;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (int p: piles) {
            max = Math.max(max, p);
        }

        int start = 1;
        int end = max;
        int result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (isValid(piles, h, mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }
}