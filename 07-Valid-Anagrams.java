// https://leetcode.com/problems/valid-anagram/description/


class Solution {
    public boolean isAnagram(String s, String t) {
        // // hashmap
        // // TC: O(n), SC: O(1)
        // if(s.length() != t.length()) {
        //     return false;
        // }
        // Map<Character, Integer> map = new HashMap<>();
        // for(Character ch: s.toCharArray()) {
        //     map.put(ch, map.getOrDefault(ch, 0) + 1);
        // }
        // for(Character ch: t.toCharArray()) {
        //     if(!map.containsKey(ch) || map.get(ch) == 0) {
        //         return false;
        //     }
        //     map.put(ch, map.get(ch) - 1);
        // }
        // return true;


        // arrays
        // TC: O(n), SC: O(1)
        if(s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];
        for(int i = 0; i < 26; i++) {
            map[i] = 0;
        }
        for(Character ch: s.toCharArray()) {
            map[ch - 'a']++;
        }
        for(Character ch: t.toCharArray()) {
            if(map[ch - 'a'] == 0) {
                return false;
            }
            map[ch - 'a']--;
        }
        return true;
    }
}
