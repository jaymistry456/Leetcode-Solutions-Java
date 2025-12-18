// https://leetcode.com/problems/best-time-to-buy-and-sell-stock


class Solution {
    public int maxProfit(int[] prices) {
        // // brute-force
        // // TC: O(n^2), SC: O(1)
        // int res = 0;
        // for(int i = 0; i < prices.length; i++) {
        //     for(int j = i + 1; j < prices.length; j++) {
        //         res = Math.max(res, prices[j] - prices[i]);
        //     }
        // }
        // return res;


        // optimal
        // TC: O(n), SC: O(1)
        int minBuy = prices[0];
        int res = 0;
        for(int i = 0; i < prices.length; i++) {
            res = Math.max(res, prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }
        return res;
    }
}