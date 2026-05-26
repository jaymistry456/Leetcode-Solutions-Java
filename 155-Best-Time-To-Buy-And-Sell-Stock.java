// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/


// given: an array of prices
// required: max profit by purchasing and selling 2 stocks by holding only 1 stock at a time

// constraints
// length of the array in [1, 100k]
// each price value in [0, 100k]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] prices, int i, int k, boolean canBuy) {
        if(i == prices.length || k == 0) {
            return 0;
        }

        if(canBuy) {
            int buy = dfs(prices, i + 1, k, false) - prices[i];
            int skip = dfs(prices, i + 1, k, true);
            return Math.max(buy, skip);
        } else {
            int sell = dfs(prices, i + 1, k - 1, true) + prices[i];
            int skip = dfs(prices, i + 1, k, false);
            return Math.max(sell, skip);
        }
    }
    
    public int maxProfit(int[] prices) {
        return dfs(prices, 0, 2, true);
    }
}



// tc: O(n^2), sc: O(n*k)
class Solution {
    Integer[][][] dp;   // [i, k, canBuy] -> max profit from this state to the end

    public int dfs(int[] prices, int i, int k, int canBuy) {
        if(i == prices.length || k == 0) {
            return 0;
        }

        if(dp[i][k][canBuy] != null) {
            return dp[i][k][canBuy];
        }

        if(canBuy == 1) {
            int buy = dfs(prices, i + 1, k, 0) - prices[i];
            int skip = dfs(prices, i + 1, k, 1);
            dp[i][k][canBuy] = Math.max(buy, skip);
        } else {
            int sell = dfs(prices, i + 1, k - 1, 1) + prices[i];
            int skip = dfs(prices, i + 1, k, 0);
            dp[i][k][canBuy] = Math.max(sell, skip);
        }

        return dp[i][k][canBuy];
    }
    
    public int maxProfit(int[] prices) {
        dp = new Integer[prices.length][3][2];

        return dfs(prices, 0, 2, 1);
    }
}