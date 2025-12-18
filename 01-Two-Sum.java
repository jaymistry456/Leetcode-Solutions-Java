// https://leetcode.com/problems/two-sum/description/


class Solution {
    public int[] twoSum(int[] nums, int target) {
        // // brute-force
        // // TC: O(n^2), SC: O(1)
        // for(int i = 0; i < nums.length; i++) {
        //     for(int j = i + 1; j < nums.length; j++) {
        //         if(nums[i] + nums[j] == target) {
        //             return new int[]{i, j};
        //         }
        //     }
        // }   
        // return new int[]{-1, -1};


        // optimal
        // // TC: O(n), SC: O(n)
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // num -> index
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}