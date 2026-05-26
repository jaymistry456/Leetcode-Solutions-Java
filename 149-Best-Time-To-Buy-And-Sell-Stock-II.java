// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/


// given: an array of prices
// required: max profit from buying and selling stocks

// constraints
// length of the array in [1, 30k]
// each value in [0, 10k]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] prices, int i, boolean canBuy) {
        if(i == prices.length) {
            return 0;
        }

        if(canBuy) {
            // Buy
            int buy = dfs(prices, i + 1, !canBuy) - prices[i];
            // Skip
            int skip = dfs(prices, i + 1, canBuy);
            return Math.max(buy, skip);
        }
        else {
            // Sell
            int sell = dfs(prices, i + 1, !canBuy) + prices[i];
            // Skip
            int skip = dfs(prices, i + 1, canBuy);
            return Math.max(sell, skip);
        }
    }

    public int maxProfit(int[] prices) {
        return dfs(prices, 0, true);
    }
}




// tc: O(n^2), sc: O(n)
class Solution {
    Integer[][] dp;   // [i][canBuy] -> max profit from this state to the end, canBuy is either 0 (CANNOT buy) or 1 (CAN buy)

    public int dfs(int[] prices, int i, int canBuy) {
        if(i == prices.length) {
            return 0;
        }

        if(dp[i][canBuy] != null) {
            return dp[i][canBuy];
        }

        if(canBuy == 1) {
            // Buy
            int buy = dfs(prices, i + 1, 0) - prices[i];
            // Skip
            int skip = dfs(prices, i + 1, 1);
            dp[i][canBuy] = Math.max(buy, skip);
        }
        else {
            // Sell
            int sell = dfs(prices, i + 1, 1) + prices[i];
            // Skip
            int skip = dfs(prices, i + 1, 0);
            dp[i][canBuy] = Math.max(sell, skip);
        }

        return dp[i][canBuy];
    }

    public int maxProfit(int[] prices) {
        dp = new Integer[prices.length][2];

        return dfs(prices, 0, 1);
    }
}




// We can optimise this even further as buying and selling have a particular pattern
// We want to buy a stock when the stock price is a valley point and sell it at the peak point
// We can therefore use greedy approach to pick a stock when the prices reaches a local valley and sell the stock when the prices reaches a local peak
// We can keep track of these variables and update them
// tc: O(n), sc: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int i = 0;
        int result = 0;

        while(i < prices.length) {
            // Buy
            int buy = prices[i];
            i++;
            while(i < prices.length && prices[i] <= prices[i - 1]) {
                buy = prices[i];
                i++;
            }

            // Sell
            if(i < prices.length) {
                int sell = prices[i];
                i++;
                while(i < prices.length && prices[i] >= prices[i - 1]) {
                    sell = prices[i];
                    i++;
                }

                result += sell - buy;
            }
        }

        return result;
    }
}