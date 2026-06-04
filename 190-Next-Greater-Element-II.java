// https://leetcode.com/problems/next-greater-element-ii/


// given: an array of integers
// required: next greater element for each value in nums

// constraints
// length of nums in [1, 10k]
// each value in [-10^9, 10^9]

// tc: O(n^2), sc: O(1)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];
        Arrays.fill(result, -1);

        for(int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            int end = i % n;

            while(j != end) {
                if(nums[j] > nums[i]) {
                    result[i] = nums[j];
                    break;
                }
                j++;
                j %= n;
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];
        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>();   // smallest element at the top
        for(int i = n * 2 - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            if(i < n && !stack.isEmpty()) {
                result[i] = stack.peek();
            }

            stack.push(nums[i % n]);
        }

        return result;
    }
}