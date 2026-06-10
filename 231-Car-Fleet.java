// https://leetcode.com/problems/car-fleet/


// given: an integer target, an integer array position which represents the starting position of each car in the array and an integer array speed which represents the speed of each car in the array
// required: return the no. of car fleets that arrive at the target position, assuming that the speed of the car fleet is limited by the car which has the lowest speed out of all of them

// constraints
// length of the array sin [1, 100k]
// target in [0, 1M]
// position of each car in [0, target]
// all position values are unique
// speed of each car in [0, 1M]

// tc: O(nlogn), sc: O(n)
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        int[][] array = new int[n][2];
        for(int i = 0; i < n; i++) {
            array[i][0] = position[i];
            array[i][1] = speed[i];
        }

        Arrays.sort(array, (a, b) -> Integer.compare(b[0], a[0]));

        int result = 0;
        double maxTime = 0;
        for(int i = 0; i < n; i++) {
            int currPosition = array[i][0];
            int currSpeed = array[i][1];
            double currTime = (double) (target - currPosition) / currSpeed;

            if(currTime > maxTime) {
                maxTime = currTime;
                result++;
            }
        }

        return result;
    }
}