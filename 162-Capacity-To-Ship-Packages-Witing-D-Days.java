// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/


// given: an array of weights and an integer days
// required: return the minimum weight capacity of the ship which can ship all the packages in the array within days

// constraints
// days, length of weights in [1, 50k]
// each weight value in [1, 500]

// We need to minimize the maximum weight capacity of the ship

// tc: O((sum - min) * n), sc: O(1)
class Solution {
    public boolean isValid(int[] weights, int days, int capacity) {
        int currDays = 1;

        int currSum = 0;
        for (int w: weights) {
            currSum += w;
            if (currSum > capacity) {
                currSum = w;
                currDays++;

                if (currDays > days) {
                    return false;
                }
            }
        }

        return true;
    }

    public int shipWithinDays(int[] weights, int days) {
        int start = Integer.MIN_VALUE;
        int end = 0;

        for (int w: weights) {
            start = Math.max(start, w);
            end += w;
        }

        for (int i = start; i <= end; i++) {
            if (isValid(weights, days, i)) {
                return i;
            }
        }

        return -1;
    }
}




// tc: O(log(sum - min) * n), sc: O(1)
class Solution {
    public boolean isValid(int[] weights, int days, int capacity) {
        int currDays = 1;

        int currSum = 0;
        for (int w: weights) {
            currSum += w;
            if (currSum > capacity) {
                currSum = w;
                currDays++;

                if (currDays > days) {
                    return false;
                }
            }
        }

        return true;
    }

    public int shipWithinDays(int[] weights, int days) {
        int start = Integer.MIN_VALUE;
        int end = 0;

        for (int w: weights) {
            start = Math.max(start, w);
            end += w;
        }

        int result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (isValid(weights, days, mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }
}