// https://leetcode.com/problems/candy/


// given: an array of ratings
// required: minimum no. of candies you need to distribute to the children so that each child gets atleast 1 candy and children with higher ratings get more candies than their neighbors

// constraints
// length of ratings in [1, 20k]
// each rating value in [0, 20k]

// tc: O(n), sc: O(n)
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;

        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        // Left to right traversal
        for(int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        // Right to left traversal
        for(int i = n - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }

        int result = 0;
        for(int c: candy) {
            result += c;
        }

        return result;
    }
}