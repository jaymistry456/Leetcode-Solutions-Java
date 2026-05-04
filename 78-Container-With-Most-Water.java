// https://leetcode.com/problems/container-with-most-water/


// given: an array of heights
// required: return the max amount of water that a container (formed by two lines) can contain

// constraints
// length of heights in [2, 100k]
// each height in [0, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public int maxArea(int[] height) {
        int result = 0;

        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j++) {
                result = Math.max(result, (j - i) * Math.min(height[i], height[j]));
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int maxArea(int[] height) {
        int result = 0;

        int left = 0;
        int right = height.length - 1;

        while(left < right) {
            result = Math.max(result, (right - left) * Math.min(height[left], height[right]));

            if(height[left] <= height[right]) {
                left++;
            }
            else {
                right--;
            }
        }

        return result;
    }
}