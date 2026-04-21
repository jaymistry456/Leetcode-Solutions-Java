// https://leetcode.com/problems/longest-common-prefix/


// given: an array of strings
// required: find the longest common prefix

// constraints
// length of the array in [1, 200]
// each string of length [0, 200]
// each string contains only lowercase letters

// tc: O(n*t), sc: O(1)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder("");

        String origin = strs[0];
        int idx = 0;
        while(idx < origin.length()) {
            for(int i = 1; i < strs.length; i++) {
                String curr = strs[i];
                if(
                    idx >= curr.length() || 
                    origin.charAt(idx) != curr.charAt(idx)
                ) {
                    return result.toString();
                }
            }
            result.append(origin.charAt(idx));
            idx++;
        }

        return result.toString();
    }
}