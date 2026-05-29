// https://leetcode.com/problems/contiguous-array/


// given: an array of integers
// required: max length of a subarry which contains equal number of 0s and 1s

// constraints
// length of the array in [1, 100k]
// each value is either 0 or 1

// tc: O(n^2), sc: O(1)
class Solution {
    public int findMaxLength(int[] nums) {
        int result = 0;

        for(int i = 0; i < nums.length; i++) {
            int currDiff = 0;
            for(int j = i; j < nums.length; j++) {
                if(nums[j] == 0) {
                    currDiff--;
                } else {
                    currDiff++;
                }
                if(currDiff == 0) {
                    result = Math.max(result, j - i + 1);
                }
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public int findMaxLength(int[] nums) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();   // diff -> first idx where it occurs
        map.put(0, -1);

        int diff = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                diff--;
            } else {
                diff++;
            }

            if(map.containsKey(diff)) {
                result = Math.max(result, i - map.get(diff));
            }

            map.putIfAbsent(diff, i);
        }

        return result;
    }
}