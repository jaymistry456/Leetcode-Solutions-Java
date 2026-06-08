// https://leetcode.com/problems/zero-array-transformation-ii/


// given: an array of integers and a 2D array of queries where each query is [l, r, val]
// required: return the minimum possible value of k (non-negative) such that after processing the k queries, nums becomes a Zero array, otherwise return -1

// constraints
// length of nums in [1, 100k]
// each value in nums in [0, 500k]
// length of queries in [1, 100k]
// l <= r in each query
// query val in [1, 5]

// tc: O(n*q), sc: O(1)
class Solution {
    public boolean isArrayZero(int[] nums) {
        for(int num: nums) {
            if(num != 0) return false;
        }
        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        if(isArrayZero(nums)) return 0;

        for(int i = 0; i < q; i++) {
            int[] query = queries[i];
            int l = query[0];
            int r = query[1];
            int val = query[2];

            for(int j = l; j <= r; j++) {
                int valToDecrement = Math.min(val, nums[j]);
                nums[j] -= valToDecrement;
            }
            if(isArrayZero(nums)) return i + 1;
        }

        return -1;
    }
}




// tc: O((n + q)*logq), sc: O(n)
class Solution {
    public boolean isValid(int[] nums, int[][] queries, int maxQueries) {
        int n = nums.length;
        
        int[] decrement = new int[n + 1];
        for(int i = 0; i < maxQueries; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            decrement[l] += val;
            decrement[r + 1] -= val;
        }

        int curr = 0;
        for(int i = 0; i < n; i++) {
            curr += decrement[i];
            if(curr < nums[i]) return false;
        }

        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int q = queries.length;

        boolean areAllZero = true;
        for(int num: nums) {
            if(num != 0) {
                areAllZero = false;
                break;
            }
        }
        if(areAllZero) return 0;
        
        int start = 1;
        int end = q;
        int result = -1;
        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(isValid(nums, queries, mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }
}