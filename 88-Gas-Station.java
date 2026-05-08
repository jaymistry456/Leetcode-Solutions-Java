// https://leetcode.com/problems/gas-station/


// given: an array of gas and cost
// required: return the starting index if one can travel once in clockwise direction using the gas and paying the corresponding cost, else return -1

// constraints
// length of gas == length of cost
// n in [1, 100k]
// each gas and cost value in [0, 10k]

// tc: O(n^2), sc: O(1)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        for(int i = 0; i < n; i++) {
            int j = i;
            int totalGas = 0;
            while(true) {
                totalGas += gas[j] - cost[j];
                if(totalGas < 0) {
                    break;
                }
                j++;
                j %= n;
                if(j == i) {
                    return i;
                }
            }
        }

        return -1;
    }
}




/*
To make this solution work in linear time, we will have to use greedy approach
we will have to satisfy 2 conditions:

1. the total of gas - total of cost has to be >= 0 for a solution to exist

2. we have to keep a running prefix sum of gas - cost at every index. if the prefix
sum falls below 0, that means we have a found a new deepest valley and the starting
point has to be greater than that valley

we guarantee that everytime we hit a diff of < 0, we have found a new valley 
because, at earlier valleys we already make the diff 0 (think of it as adding 
something positive to a negative to make it 0), so when we encounter a negative 
diff again, that means that prefix sum is even more negative than earlier prefix 
sums as we now have to a positive number to make it 0 in addition to all the 
positive numbers we add earlier to negative diffs

*/
// tc: O(n), sc: O(1)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGas = 0;
        int diff = 0;
        int result = 0;

        for(int i = 0; i < n; i++) {
            totalGas += gas[i] - cost[i];
            diff += gas[i] - cost[i];
            if(diff < 0) {
                diff = 0;
                result = i + 1;
            }
        }

        return totalGas < 0 ? -1 : result;
    }
}