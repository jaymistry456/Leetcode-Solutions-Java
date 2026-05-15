// https://leetcode.com/problems/random-pick-with-weight/


// given: an array of weights
// required: implement a function which randomly picks an index and returns it with probability weights[i]/sum(weights) -> each index should have a probability of being picked relative to ratio of its own weight to the sum of all the weights

// constraints
// length of the array in [1, 10k]
// each weight in [1, 100k]
// pickIndex will be called atmost 10k times

// tc: O(sum(w)), sc: O(1)
class Solution {
    List<Integer> weights;

    public Solution(int[] w) {
        weights = new ArrayList<>();

        for(int i = 0; i < w.length; i++) {
            for(int j = 0; j < w[i]; j++) {
                weights.add(i);
            }
        }
    }
    
    public int pickIndex() {
        int idx = new Random().nextInt(weights.size());
        return weights.get(idx);
    }
}


// tc: O(logn) for pickIndex, sc: O(n)
class Solution {
    List<Integer> runningWeightSum;
    Random rand;

    public Solution(int[] w) {
        runningWeightSum = new ArrayList<>();
        rand = new Random();

        int currSum = 0;
        for(int i = 0; i < w.length; i++) {
            currSum += w[i];
            runningWeightSum.add(currSum);
        }
    }
    
    public int pickIndex() {
        int totalSum = runningWeightSum.get(runningWeightSum.size() - 1);
        int randomWeightSum = rand.nextInt(totalSum) + 1;
        
        int start = 0;
        int end = runningWeightSum.size() - 1;
        int result = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(runningWeightSum.get(mid) >= randomWeightSum) {
                result = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */