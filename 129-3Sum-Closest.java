// https://leetcode.com/problems/3sum-closest/


// given: an array of integers and an integer target
// required: return the sum of three distinct integers which is closest to target

// constraints
// length of nums in [3, 500]
// each value in [-1000, 1000]
// target in [-10k, 10k]
// each input has exactly one solution

// tc: O(n^3), sc: O(1)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        long result = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    int currSum = nums[i] + nums[j] + nums[k];
                    
                    if(Math.abs(currSum - target) < Math.abs(result - target)) {
                        result = currSum;
                    }
                }
            }
        }

        return (int) result;
    }
}





// tc: O(n^2), sc: O(1)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        long result = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while(left < right) {
                int currSum = nums[i] + nums[left] + nums[right];
                if(Math.abs(currSum - target) < Math.abs(result - target)) {
                    result = currSum;
                }

                if(currSum < target) {
                    left++;
                }
                else if(currSum > target) {
                    right--;
                }
                else {
                    return currSum;
                }
            }
        }
        
        return (int) result;
    }
}