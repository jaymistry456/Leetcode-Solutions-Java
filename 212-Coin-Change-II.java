// https://leetcode.com/problems/coin-change-ii/


// given: an integer amount and an array of coin denominations
// required: return the no. of combinations that make up that amount, otherwise return 0

// constraints
// length of coints in [1, 300]
// each coin denomination value in [1, 5000]
// all coins are unique
// amount in [0, 5000]

// tc: O(n^amount), sc: O(amount)
class Solution {
    public int dfs(int amount, int[] coins, int currSum, int i) {
        if(currSum == amount) return 1;

        if(currSum > amount || i == coins.length) return 0;

        return (
            dfs(amount, coins, currSum + coins[i], i) +
            dfs(amount, coins, currSum, i + 1)
        );
    }

    public int change(int amount, int[] coins) {
        return dfs(amount, coins, 0, 0);
    }
}



// HashMap
// tc: O(n*amount), sc: O(n*amount)
record Pair(int currSum, int i) {}

class Solution {
    Map<Pair, Integer> dp = new HashMap<>();   // [currSum, i] -> no. of combinations
    
    public int dfs(int amount, int[] coins, int currSum, int i) {
        if(currSum == amount) return 1;

        if(currSum > amount || i == coins.length) return 0;

        Pair pair = new Pair(currSum, i);
        if(dp.containsKey(pair)) return dp.get(pair);

        int result = (
            dfs(amount, coins, currSum + coins[i], i) +
            dfs(amount, coins, currSum, i + 1)
        );

        dp.put(pair, result);

        return result;
    }

    public int change(int amount, int[] coins) {
        return dfs(amount, coins, 0, 0);
    }
}





// Array
// tc: O(n*amount), sc: O(n*amount)
class Solution {
    Integer[][] dp;   // [currSum, i] -> no. of combinations
    
    public int dfs(int amount, int[] coins, int currSum, int i) {
        if(currSum == amount) return 1;

        if(currSum > amount || i == coins.length) return 0;

        if(dp[currSum][i] != null) return dp[currSum][i];

        int result = (
            dfs(amount, coins, currSum + coins[i], i) +
            dfs(amount, coins, currSum, i + 1)
        );

        dp[currSum][i] = result;

        return result;
    }

    public int change(int amount, int[] coins) {
        dp = new Integer[amount + 1][coins.length];
        return dfs(amount, coins, 0, 0);
    }
}