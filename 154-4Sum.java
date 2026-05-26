// https://leetcode.com/problems/4sum/


// given: an array of integer and an integer target
// required: return all unique quadruplets which sums to target

// constraints
// length of nums in [1, 200]
// each value in [-10^9, 10^9]
// target in [-10^9, 10^9]

// tc: O(n^4), sc: O(1)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < n; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue;

            for(int j = i + 1; j < n; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;

                for(int k = j + 1; k < n; k++) {
                    if(k > j + 1 && nums[k] == nums[k - 1]) continue;

                    for(int l = k + 1; l < n; l++) {
                        if(l > k + 1 && nums[l] == nums[l - 1]) continue;

                        long currSum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l];
                        if(currSum == (long) target) {
                            result.add(List.of(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }

        return result;
    }
}




// tc: O(n^3), sc: O(1)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < n; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue;

            for(int j = i + 1; j < n; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;
                while(left < right) {
                    long currSum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];

                    if(currSum < target) {
                        left++;
                    } else if(currSum > target) {
                        right--;
                    } else {
                        result.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        while(left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        right--;
                        while(left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }

        return result;
    }
}