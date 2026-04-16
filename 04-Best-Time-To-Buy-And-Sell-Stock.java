// https://leetcode.com/problems/best-time-to-buy-and-sell-stock


// given: an array of integers
// required: return the max profit that can be achieved by buying and selling a stock

// constraints
// length of the array in [1, 100k]
// each value in [0, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;

        for(int i = 0; i < prices.length; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                result = Math.max(result, prices[j] - prices[i]);
            }
        }

        return result;
    }
}



// tc: O(n), sc: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;
        int minBuy = Integer.MAX_VALUE;

        for(int i = 0; i < prices.length; i++) {
            result = Math.max(result, prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }

        return result;
    }
}