// https://leetcode.com/problems/trapping-rain-water/


// given: an array of heights representing bars of different heights
// required: return the total amount of water which can be trapped between the bars

// constraints
// length of the array in [1, 20k]
// each height value in [0, 100k]

// tc: O(n), sc: O(n)
class Solution {
    public int trap(int[] height) {
        int n = height.length;

        // 1. Calculate max height from left to right
        int[] leftToRight = new int[n];
        int curr = 0;
        for(int i = 0; i < n; i++) {
            curr = Math.max(curr, height[i]);
            leftToRight[i] = curr;
        }

        // 2. Traverse from right while keeping a running max height
        int result = 0;
        int rightMax = 0;
        for(int i = n - 1; i >= 0; i--) {
            int leftMax = 0;
            if(i - 1 >= 0) {
                leftMax = leftToRight[i - 1];
            }
            
            int water = Math.min(leftMax, rightMax) - height[i];
            if(water > 0) {
                result += water;
            }

            rightMax = Math.max(rightMax, height[i]);
        }

        return result;
    }
}