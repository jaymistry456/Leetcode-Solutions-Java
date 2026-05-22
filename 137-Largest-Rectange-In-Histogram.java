// https://leetcode.com/problems/largest-rectangle-in-histogram/


// given: an array of heights
// required: the largest rectangle that can be formed in the heights

// constraints
// length of heights in [1, 100k]
// each value in [0, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int result = 0;

        for(int i = 0; i < heights.length; i++) {
            int currMin = heights[i];
            for(int j = i; j < heights.length; j++) {
                currMin = Math.min(currMin, heights[j]);
                result = Math.max(result, (j - i + 1) * currMin);
            }
        }

        return result;
    }
}



// tc: O(n), sc: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int result = 0;

        Deque<int[]> stack = new ArrayDeque<>();   // [idx, minHeight]
        for(int i = 0; i < heights.length; i++) {
            int startIdx = i;
            while(!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] item = stack.pop();
                int prevIdx = item[0];
                int prevMinHeight = item[1];
                result = Math.max(result, prevMinHeight * (i - prevIdx));
                startIdx = prevIdx;
            }

            stack.push(new int[]{startIdx, heights[i]});
        }

        while(!stack.isEmpty()) {
            int[] item = stack.pop();
            int prevIdx = item[0];
            int prevMinHeight = item[1];
            result = Math.max(result, prevMinHeight * (heights.length - prevIdx));
        }

        return result;
    }
}