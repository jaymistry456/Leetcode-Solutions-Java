// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/


// given: an array of prices where each value represents the price of the stock on that day
// required: return the max profit that can be achieved by buying multiple stocks with cooldown in between

// constraints
// length of prices in [1, 5000]
// each value in [0, 1000]

// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] prices, int i, boolean canBuy) {
        if(i >= prices.length) return 0;

        if(canBuy) {
            int cooldown = dfs(prices, i + 1, true);
            int buy = dfs(prices, i + 1, false) - prices[i];
            return Math.max(cooldown, buy);
        }
        else {
            int cooldown = dfs(prices, i + 1, false);
            int sell = dfs(prices, i + 2, true) + prices[i];
            return Math.max(cooldown, sell);
        }
    }

    public int maxProfit(int[] prices) {
        return dfs(prices, 0, true);
    }
}




// Top-down
// tc: O(n), sc: O(n)
class Solution {
    Integer[][] dp;   // [i, canBuy] -> profit

    public int dfs(int[] prices, int i, int canBuy) {
        if(i >= prices.length) return 0;

        if(dp[i][canBuy] != null) return dp[i][canBuy];

        if(canBuy == 1) {
            int cooldown = dfs(prices, i + 1, 1);
            int buy = dfs(prices, i + 1, 0) - prices[i];
            dp[i][canBuy] = Math.max(cooldown, buy);
        }
        else {
            int cooldown = dfs(prices, i + 1, 0);
            int sell = dfs(prices, i + 2, 1) + prices[i];
            dp[i][canBuy] = Math.max(cooldown, sell);
        }

        return dp[i][canBuy];
    }

    public int maxProfit(int[] prices) {
        dp = new Integer[prices.length][2];

        return dfs(prices, 0, 1);
    }
}




// Bottom-up
// tc: O(n), sc: O(n)
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[][] dp = new int[n + 2][2];   // [i, canBuy] -> profit
        
        for(int i = n - 1; i >= 0; i--) {
            // canBuy == 1
            dp[i][1] = Math.max(
                dp[i + 1][1],
                dp[i + 1][0] - prices[i]
            );
            // canBuy == 0
            dp[i][0] = Math.max(
                dp[i + 1][0],
                dp[i + 2][1] + prices[i]
            );
        }

        return dp[0][1];
    }
}