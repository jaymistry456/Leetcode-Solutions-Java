// https://leetcode.com/problems/longest-consecutive-sequence/


// given: an array of integers
// required: return the length of the longest consecutive sequence of elements

// constraints
// length of the array in [0, 100k]
// each value in [-10^9, 10^9]

// tc: O(n), sc: O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            set.add(num);
        }

        int result = 0;
        for(int num: set) {
            if(set.contains(num - 1)) {
                continue;
            }

            int length = 1;
            while(set.contains(num + length)) {
                length++;
            }
            result = Math.max(result, length);
        }
    
        return result;
    }
}