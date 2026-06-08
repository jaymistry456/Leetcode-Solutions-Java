// https://leetcode.com/problems/word-pattern/


// given: a string patterna and a s string
// required: check whether string s matches pattern

// constraints
// length of pattern in [1, 300]
// length of s in [1, 3000]
// s contains lowercase letters and space only
// pattern contains lowercase letters only

// tc: O(n), sc: O(n)
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] array = s.split(" ");
        
        if(array.length != pattern.length()) return false;

        Map<Character, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for(int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String str = array[i];

            if(map1.containsKey(ch) && map2.containsKey(str)) {
                if(!map1.get(ch).equals(map2.get(str))) return false;
            } else if(map1.containsKey(ch) || map2.containsKey(str)) {
                return false;
            } else {
                map1.put(ch, i);
                map2.put(str, i);
            }
        }

        return true;
    }
}