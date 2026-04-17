// https://leetcode.com/problems/ransom-note/


// given: two strings ransomNote and magazine
// required: check whether ransomNote can be constructed from magazine letters

// constraints
// string lengths in [1, 100k]
// both strings contain lowercase letters only

// HashMap
// tc: O(m+n), sc: O(1)
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();   // char -> freq of char
        for(char ch: magazine.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(char ch: ransomNote.toCharArray()) {
            if(!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }
            map.put(ch, map.get(ch) - 1);
        }

        return true;
    }
}



// Array
// tc: O(m+n), sc: O(1)
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] array = new int[26];
        for(char ch: magazine.toCharArray()) {
            array[ch - 'a']++;
        }

        for(char ch: ransomNote.toCharArray()) {
            if(array[ch - 'a'] == 0) {
                return false;
            }
            array[ch - 'a']--;
        }

        return true;
    }
}