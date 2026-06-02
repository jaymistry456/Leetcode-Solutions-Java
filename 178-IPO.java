// https://leetcode.com/problems/ipo/


// given: an integer k which is the max no. of projects we can do, an integer w which is the initial capital we start with, and 2 arrays profits and capital representing profit and capital of each potential project that we can pick from
// required: return the maximized final capital after the doing the required projects

// constraints
// k in [1, 100k]
// w in [0, 10^9]
// length of the arrays in [1, 100k]
// each profit and capital value in [0, 10k]

// tc: O(k*n), sc: O(n)
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        boolean[] completed = new boolean[profits.length];

        while(k > 0) {
            int idx = -1;
            int maxProfit = Integer.MIN_VALUE;

            for(int i = 0; i < capital.length; i++) {
                if(!completed[i] && capital[i] <= w && profits[i] > maxProfit) {
                    maxProfit = profits[i];
                    idx = i;
                }
            }
            
            if(idx == -1) return w;

            completed[idx] = true;
            w += maxProfit;
            k--;
        }

        return w;
    }
}




// tc: O(nlogn), sc: O(n)
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );   // profits which are within the current capital sorted ind desc order

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> {
                if(a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(b[0], a[0]);
            }
        );   // [capital, profit] which are NOT within the current capital yet, sorted by capital asc, profit desc

        for(int i = 0; i < profits.length; i++) {
            if(capital[i] <= w) {
                maxHeap.offer(profits[i]);
            } else {
                minHeap.offer(new int[]{capital[i], profits[i]});
            }
        }

        while(k > 0) {
            while(!minHeap.isEmpty() && minHeap.peek()[0] <= w) {
                maxHeap.offer(minHeap.poll()[1]);
            }

            if(maxHeap.isEmpty()) return w;

            w += maxHeap.poll();
            k--;
        }

        return w;
    }
}