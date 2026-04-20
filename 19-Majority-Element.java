// https://leetcode.com/problems/majority-element/


// given: an array of elements
// required: return the majority element which occurs more than n/2 times

// constraints
// length of the array in [1, 50k]
// each value in [-10^9, 10^9]
// majority element is guaranteed to exist

// tc: O(n), sc: O(n)
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();   // num -> freq of num

        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if(value >= nums.length / 2 + 1) {
                return key;
            }
        }

        return -1;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int majorityElement(int[] nums) {
        int currNum = Integer.MAX_VALUE;
        int currFreq = 0;

        for(int num: nums) {
            if(currNum == num) {
                currFreq++;
            }
            else {
                currFreq--;
                if(currFreq <= 0) {
                    currNum = num;
                    currFreq = 1;
                }
            }
        }

        return currNum;
    }
}
