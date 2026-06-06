// https://leetcode.com/problems/remove-outermost-parentheses/


// given: a string s
// required: remove all the outer parentheses

// constraints
// length of s in [1, 100k]
// s is valid

// tc: O(n), sc: O(1)
class Solution {
    public String removeOuterParentheses(String s) {
        int n = s.length();

        StringBuilder result = new StringBuilder("");
        int i = 0;

        while(i < n) {
            int curr = 0;
            if(s.charAt(i) == '(') {
                i++;   // Skipping the outer '('

                while(curr > 0 || s.charAt(i) != ')') {
                    result.append(s.charAt(i));

                    if(s.charAt(i) == '(') {
                        curr++;
                    } else {
                        curr--;
                    }

                    i++;
                }

                i++;   // Skipping the outer ')'
            }
        }

        return result.toString();
    }
}