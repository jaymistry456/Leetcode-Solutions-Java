// https://leetcode.com/problems/3sum/


// given: an array of integers
// required: return all the unique triplets whose sum is 0 from the array

// constraints
// length of the array in [3, 3000]
// each value in [-100k, 100k]

// tc: O(n^3), sc: O(1)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>(List.of(nums[i], nums[j], nums[k]));
                        Collections.sort(triplet);
                        set.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }
}




// tc: O(n^2), sc: O(1)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        Set<List<Integer>> set = new HashSet<>();

        for(int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1;
            int k = nums.length - 1;
            while(j < k) {
                int currSum = nums[i] + nums[j] + nums[k];
                if(currSum == 0) {
                    List<Integer> triplet = new ArrayList<>(List.of(nums[i], nums[j], nums[k]));
                    Collections.sort(triplet);
                    set.add(triplet);
                    j++;
                    while(j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    k--;
                    while(j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
                else if(currSum < 0) {
                    j++;
                }
                else {
                    k--;
                }
            }
        }

        return new ArrayList<>(set);
    }
}