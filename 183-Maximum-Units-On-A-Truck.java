// https://leetcode.com/problems/maximum-units-on-a-truck/


// given: a 2D array boxTypes, where each value is [noOfBoxes, noOfUnitsPerBox] and an integer truckSize
// required: max. no. units that can be put on the truck assuming we can only put truckSize no. of boxes

// constraints
// length of the array in [1, 1000]
// noOfBoxes, noOfUnitsPerBox in [1, 1000]
// truckSize in [1, 1M]

// We want to maximize the boxes with the most no of units (sort boxes in desc order by units)
// tc: O(nlogn), sc: O(1)
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> {
            if(a[1] != b[1]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });   // Sort by units desc, boxes desc

        int result = 0;
        for(int[] boxType: boxTypes) {
            int currBoxes = boxType[0];
            int currUnits = boxType[1];

            int boxesToPut = Math.min(truckSize, currBoxes);
            truckSize -= boxesToPut;
            result += boxesToPut * currUnits;

            if(truckSize == 0) return result;
        }

        return result;
    }
}