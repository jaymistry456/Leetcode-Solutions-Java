// https://leetcode.com/problems/triangle/


// given: a 2D jagged array
// required: return the minimum path sum from top to bottom by moving either to the bottom or to the right of bottom

// constraints
// length of the array in [1, 200]
// each row is 1 greater than the last row in the array
// each value in [-10k, 10k]
// we can do a simple dfs where we explo

// tc: O((m*n)^2), sc: O(m*n)
class Solution {
    public int dfs(List<List<Integer>> triangle, int i, int j) {
        if(i == triangle.size() || j == triangle.get(i).size()) return 0;

        return triangle.get(i).get(j) + Math.min(
            dfs(triangle, i + 1, j),
            dfs(triangle, i + 1, j + 1)
        );
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }
}




// HashMap
// tc: O(m*n), sc: O(m*n)
record Pair(int i, int j) {}

class Solution {
    Map<Pair, Integer> dp = new HashMap<>();   // [i, j] -> min path to bottom from [i, j]

    public int dfs(List<List<Integer>> triangle, int i, int j) {
        if(i == triangle.size() || j == triangle.get(i).size()) return 0;

        Pair pair = new Pair(i, j);
        if(dp.containsKey(pair)) return dp.get(pair);

        int result = triangle.get(i).get(j) + Math.min(
            dfs(triangle, i + 1, j),
            dfs(triangle, i + 1, j + 1)
        );
        dp.put(pair, result);

        return result;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }
}




// Array
// tc: O(m*n), sc: O(m*n)
class Solution {
    Integer[][] dp;   // [i, j] -> min path to bottom from [i, j]

    public int dfs(List<List<Integer>> triangle, int i, int j) {
        if(i == triangle.size() || j == triangle.get(i).size()) return 0;

        if(dp[i][j] != null) return dp[i][j];

        dp[i][j] = triangle.get(i).get(j) + Math.min(
            dfs(triangle, i + 1, j), 
            dfs(triangle, i + 1, j + 1)
        );

        return dp[i][j];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        dp = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }
}





// Bottom-up
// tc: O(m*n), sc: O(m*n)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];

        for(int i = triangle.size() - 1; i >= 0; i--) {
            for(int j = triangle.get(i).size() - 1; j >= 0; j--) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(
                    dp[i + 1][j],
                    dp[i + 1][j + 1]
                );
            }
        }

        return dp[0][0];
    }
}