// https://leetcode.com/problems/single-number/


// given: an array of integers
// required: return the element which occurs only once

// constraints
// length of tha array in [1, 30k]
// each value in [-30k, 30k]

// tc: O(n), sc: O(n)
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();   // num -> freq of num

        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int singleNumber(int[] nums) {
        int xor = 0;

        for(int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        return xor;
    }
}