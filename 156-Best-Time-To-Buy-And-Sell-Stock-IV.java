// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/


// given: an array of prices
// required: max profit by purchasing and selling k stocks by holding only 1 stock at a time

// constraints
// k in [1, 100]
// length of the array in [1, 1000]
// each price value in [0, 1000]

// tc: O(4^n), sc: O(n)
class Solution {
    public int dfs(int k, int[] prices, int i, boolean canBuy) {
        if(i == prices.length || k == 0) {
            return 0;
        }

        if(canBuy) {
            int buy = dfs(k, prices, i + 1, false) - prices[i];
            int skip = dfs(k, prices, i + 1, true);
            return Math.max(buy, skip);
        }
        else {
            int sell = dfs(k - 1, prices, i + 1, true) + prices[i];
            int skip = dfs(k, prices, i + 1, false);
            return Math.max(sell, skip);
        }
    }

    public int maxProfit(int k, int[] prices) {
        return dfs(k, prices, 0, true);
    }
}




// tc: O(n*k), sc: O(n*k)
class Solution {
    Integer[][][] dp;

    public int dfs(int k, int[] prices, int i, int canBuy) {
        if(i == prices.length || k == 0) {
            return 0;
        }

        if(dp[i][k][canBuy] != null) {
            return dp[i][k][canBuy];
        }

        if(canBuy == 1) {
            int buy = dfs(k, prices, i + 1, 0) - prices[i];
            int skip = dfs(k, prices, i + 1, 1);
            dp[i][k][canBuy] = Math.max(buy, skip);
        }
        else {
            int sell = dfs(k - 1, prices, i + 1, 1) + prices[i];
            int skip = dfs(k, prices, i + 1, 0);
            dp[i][k][canBuy] = Math.max(sell, skip);
        }

        return dp[i][k][canBuy];
    }

    public int maxProfit(int k, int[] prices) {
        dp = new Integer[prices.length][k + 1][2];

        return dfs(k, prices, 0, 1);
    }
}