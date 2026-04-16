// https://leetcode.com/problems/valid-anagram/description/


// given: two strings s and t
// required: check whether s is an anagram of t

// constraints
// length of both strings in [1, 50k]
// s and t contain lowercase letters only

// HashMap
// tc: O(n), sc: O(1)
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();   // char -> freq of char
        for(char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(char ch: t.toCharArray()) {
            if(!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }
            map.put(ch, map.get(ch) - 1);
        }

        return true;
    }
}




// Array
// tc: O(n), sc: O(1)
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] array = new int[26];
        for(char ch: s.toCharArray()) {
            array[ch - 'a']++;
        }
        for(char ch: t.toCharArray()) {
            if(array[ch - 'a'] == 0) {
                return false;
            }
            array[ch - 'a']--;
        }

        return true;
    }
}