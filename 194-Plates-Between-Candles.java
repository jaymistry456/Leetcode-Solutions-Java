// https://leetcode.com/problems/plates-between-candles/


// given: a string s and a 2D array queries where each [x, y] represents the range of queries
// required: for each query, return the number of plates between candles

// constraints
// length of s in [3, 100k]
// s consists of only '*' (plate) and '|' (candle)
// length of queries in [1, 100k]

// tc: O(n*q), sc: O(1)
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int q = queries.length;

        int[] result = new int[q];
        
        for(int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            int currResult = 0;
            while(start <= end && s.charAt(start) != '|') {
                start++;
            }
            while(start <= end && s.charAt(end) != '|') {
                end--;
            }

            for(int j = start; j <= end; j++) {
                if(s.charAt(j) == '*') {
                    currResult++;
                }
            }

            result[i] = currResult;
        }

        return result;
    }
}




// tc: O(n+q), sc: O(n)
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int q = queries.length;

        int[] plates = new int[n];
        int curr = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '*') {
                curr++;
            }
            plates[i] = curr;
        }

        int[] nearestToTheLeft = new int[n];
        curr = -1;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '|') {
                curr = i;
            }
            nearestToTheLeft[i] = curr;
        }

        int[] nearestToTheRight = new int[n];
        curr = -1;
        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '|') {
                curr = i;
            }
            nearestToTheRight[i] = curr;
        }

        int[] result = new int[q];
        
        for(int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            int idx1 = nearestToTheRight[start];
            int idx2 = nearestToTheLeft[end];

            if(idx1 != -1 && idx2 != -1 && idx1 < idx2) {
                result[i] = plates[idx2] - plates[idx1];
            }

        }

        return result;
    }
}