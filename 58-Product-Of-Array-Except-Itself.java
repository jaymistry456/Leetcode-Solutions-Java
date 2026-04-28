// https://leetcode.com/problems/product-of-array-except-self/


// given: an array of integers
// required: return an array answer such that answer[i] is equal to the product of all the elements of nums execpt nums[i]

// constraints
// length of nums in [2, 100k]
// each value in nums in [-30, 30]

// tc: O(n), sc: O(1)
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] answer = new int[n];

        int curr = 1;
        for(int i = 0; i < n; i++) {
            curr *= nums[i];
            answer[i] = curr;
        }

        int rightProduct = 1;
        for(int i = n - 1; i >= 0; i--) {
            int leftProduct = 1;
            if(i - 1 >= 0) {
                leftProduct = answer[i - 1];
            }

            if(i + 1 < n) {
                rightProduct *= nums[i + 1];
            }

            answer[i] = leftProduct * rightProduct;
        }

        return answer;
    }
}