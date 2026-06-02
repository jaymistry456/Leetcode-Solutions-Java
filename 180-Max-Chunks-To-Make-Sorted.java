// https://leetcode.com/problems/max-chunks-to-make-sorted/


// given: an array of integers
// required: the largest no. chunks that can be created from the array, where sorted each individual chunk gives the sorted array

// constraints
// length of the array in [1, 10]
// each value in [0, n]
// all values are unique

// tc: O(n^2), sc: O(1)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int result = 0;

        for(int i = 0; i < arr.length; i++) {
            int currMax = Integer.MIN_VALUE;

            for(int j = 0; j <= i; j++) {
                currMax = Math.max(currMax, arr[j]);
            }

            if(currMax == i) {
                result++;
            }
        }

        return result;
    }
}



// tc: O(n), sc: O(1)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int result = 0;
        int currMax = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            currMax = Math.max(currMax, arr[i]);

            if(currMax == i) {
                result++;
            }
        }

        return result;
    }
}