// https://leetcode.com/problems/isomorphic-strings/


// given: 2 strings s and t
// required: check whether they are isomorphic

// constraints
// length of both strings in [1, 50k]
// contain valid ASCII characters

// tc: O(n), sc: O(n)
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character, Integer> map1 = new HashMap<>();   // s -> idx
        Map<Character, Integer> map2 = new HashMap<>();   // t -> idx

        for(int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if(map1.containsKey(charS) && map2.containsKey(charT)) {
                if(map1.get(charS) != map2.get(charT)) {
                    return false;
                }
            } else if (map1.containsKey(charS) || map2.containsKey(charT)) {
                return false;
            } else {
                map1.put(charS, i);
                map2.put(charT, i);
            }
        }

        return true;
    }
}