// https://leetcode.com/problems/coin-change/


// given: an array of coins or different denominations and an integer amount
// required: return the fewest no. of coins that can make up the amount, else return -1

// constraints
// coins can be reused
// length of the array in [1, 12]
// each coin value in [1, 2^31 - 1]
// amount in [0, 10k]

// tc: O(n^t), sc: O(t)
class Solution {
    public int dfs(int[] coins, int currSum, int amount) {
        if(currSum == amount) {
            return 0;
        }
        
        int result = amount + 1;
        for(int coin: coins) {
            if(coin <= amount - currSum) {
                result = Math.min(result, 1 + dfs(coins, currSum + coin, amount));
            }
        }

        return result;
    }

    public int coinChange(int[] coins, int amount) {
        int result = dfs(coins, 0, amount);

        return result < amount + 1 ? result : -1;
    }
}




// Top-down (HashMap)
// tc: O(n*t), sc: O(t)
class Solution {
    Map<Integer, Integer> map;   // currSum -> min coins to reach amount from currSum

    public int dfs(int[] coins, int currSum, int amount) {
        if(currSum == amount) {
            return 0;
        }

        if(map.containsKey(currSum)) {
            return map.get(currSum);
        }
        
        int result = amount + 1;
        for(int coin: coins) {
            if(coin <= amount - currSum) {
                result = Math.min(result, 1 + dfs(coins, currSum + coin, amount));
            }
        }

        map.put(currSum, result);

        return result;
    }

    public int coinChange(int[] coins, int amount) {
        map = new HashMap<>();

        int result = dfs(coins, 0, amount);

        return result < amount + 1 ? result : -1;
    }
}



// Top-down (Array)
// tc: O(n*t), sc: O(t)
class Solution {
    Integer[] dp;   // currSum -> min coins to reach amount from currSum

    public int dfs(int[] coins, int currSum, int amount) {
        if(currSum == amount) {
            return 0;
        }

        if(dp[currSum] != null) {
            return dp[currSum];
        }
        
        int result = amount + 1;
        for(int coin: coins) {
            if(coin <= amount - currSum) {
                result = Math.min(result, 1 + dfs(coins, currSum + coin, amount));
            }
        }

        dp[currSum] = result;

        return result;
    }

    public int coinChange(int[] coins, int amount) {
        dp = new Integer[amount + 1];

        int result = dfs(coins, 0, amount);

        return result < amount + 1 ? result : -1;
    }
}




// Bottom-up
// tc: O(n*t), sc: O(t)
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++) {
            for(int coin: coins) {
                if(i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] < amount + 1 ? dp[amount] : -1;
    }
}