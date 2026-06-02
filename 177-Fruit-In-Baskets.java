// https://leetcode.com/problems/fruit-into-baskets/


// given: an array of integers where each integer represents a type of a fruit
// required: maximum no. fruits we can pick given that we can pick only 2 types of fruits at once

// constraints
// length of fruits in [1, 100k]
// each value in [0, length of fruits - 1]

// tc: O(n^2), sc: O(1)
class Solution {
    public int totalFruit(int[] fruits) {
        int result = 0;

        for(int i = 0; i < fruits.length; i++) {
            Set<Integer> set = new HashSet<>();   // stores different fruit types

            for(int j = i; j < fruits.length; j++) {
                set.add(fruits[j]);
                if(set.size() > 2) break;

                result = Math.max(result, j - i + 1);
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int totalFruit(int[] fruits) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();   // fruit type -> freq

        int left = 0;
        int right = 0;

        while(right < fruits.length) {
            // 1. Process right pointer
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            
            // 2. Validate the condition
            while(map.size() > 2) {
                map.put(fruits[left], map.getOrDefault(fruits[left], 0) - 1);
                if(map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            // 3. Calcualte the result
            result = Math.max(result, right - left + 1);

            // 4. Advance the right pointer
            right++;
        }

        return result;
    }
}