// https://leetcode.com/problems/split-array-largest-sum/


// given: an array of integers and an integer k
// required: split array in k sub-arrays such that the largest sum among them is minimized

// constraints
// length of nums in [1, 1000]
// each value in [0, 10^6]
// k in [1, min(50, nums.length)]

// tc: O(n * (sum - max)), sc: O(1)
class Solution {
    public boolean isValid(int[] nums, int k, int maxSum) {
        int currSum = 0;
        int currK = 1;

        for(int num: nums) {
            currSum += num;

            if(currSum > maxSum) {
                currSum = num;
                currK++;
                if(currK > k) {
                    return false;
                }
            }
        }

        return true;
    }

    public int splitArray(int[] nums, int k) {
        int start = Integer.MIN_VALUE;
        int end = 0;
        for(int num: nums) {
            start = Math.max(start, num);
            end += num;
        }

        for(int maxSum = start; maxSum <= end; maxSum++) {
            if(isValid(nums, k, maxSum)) {
                return maxSum;
            }
        }

        return -1;
    }
}




// tc: O(n * log(sum - max)), sc: O(1)
class Solution {
    public boolean isValid(int[] nums, int k, int maxSum) {
        int currSum = 0;
        int currK = 1;

        for(int num: nums) {
            currSum += num;

            if(currSum > maxSum) {
                currSum = num;
                currK++;
                if(currK > k) {
                    return false;
                }
            }
        }

        return true;
    }

    public int splitArray(int[] nums, int k) {
        int start = Integer.MIN_VALUE;
        int end = 0;
        for(int num: nums) {
            start = Math.max(start, num);
            end += num;
        }

        int result = -1;
        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(isValid(nums, k, mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }
}