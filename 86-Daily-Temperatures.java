// https://leetcode.com/problems/daily-temperatures/


// given: an array of temperatures
// required: return an array such that each index represents the days until a higher temperature can be achieved

// constraints
// length of temperatures in [1, 100k]
// each temperature value in [30, 100]

// tc: O(n^2), sc: O(1)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();   // temperature indices in desc order

        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if(!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        return result;
    }
}