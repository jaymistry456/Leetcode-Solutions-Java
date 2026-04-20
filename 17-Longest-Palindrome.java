// https://leetcode.com/problems/longest-palindrome/


// given: a string s
// required: length of the longest palindrome that can be built using the letters of the s

// constraints
// length of s in [1, 2000]
// s contains lowercase and uppercase letters only
// tc: O(n), sc: O(1)
class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();   // char -> freq of char
        
        for(char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int result = 0;
        boolean isOddPresent = false;
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            char ch = entry.getKey();
            int freq = entry.getValue();

            boolean isCurrValOdd = freq % 2 == 1;
            
            if(!isOddPresent && isCurrValOdd) {
                isOddPresent = true;
            }

            result += isCurrValOdd ? freq - 1 : freq;
        }

        if(isOddPresent) {
            result++;
        }

        return result;
    }
}