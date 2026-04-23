// https://leetcode.com/problems/longest-substring-without-repeating-characters/


// given: a string s
// required: return the length of the longest string without repeating characters

// constraints
// length of s in [0, 50k]
// s contains letter, digits, symbols and spaces

// tc: O(n^2), sc: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set;
        int result = 0;

        for(int i = 0; i < s.length(); i++) {
            set = new HashSet<>();

            for(int j = i; j < s.length(); j++) {
                if(set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                result = Math.max(result, j - i + 1);
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int result = 0;

        int left = 0;
        int right = 0;
        while(right < s.length()) {
            while(set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            
            set.add(s.charAt(right));
            result = Math.max(result, right - left + 1);

            right++;
        }

        return result;
    }
}