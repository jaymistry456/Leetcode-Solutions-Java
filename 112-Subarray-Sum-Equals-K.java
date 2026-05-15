// https://leetcode.com/problems/subarray-sum-equals-k/


// given: an array of integers and an integer k
// required: return the number of subarrays whose sum is k

// constraints
// length of the array in [1, 20k]
// each value in [-1000, 1000]
// k in [-10^7, 10^7]

// tc: O(n^2), sc: O(1)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;

        for(int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for(int j = i; j < nums.length; j++) {
                currSum += nums[i];
                if(currSum == k) {
                    result++;
                }
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();   // sum -> no. of subarrays with this sum
        map.put(0, 1);
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];

            result += map.getOrDefault(sum - k, 0);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}