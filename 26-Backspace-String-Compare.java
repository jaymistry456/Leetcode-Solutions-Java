// https://leetcode.com/problems/backspace-string-compare/


// given: two strings s and t
// required: check whether the two strings are equal after removing the backspace characters

// constraints
// length of both the strings in [1, 200]
// s and t contain lowercase letters and '#' only

// tc: O(n), sc: O(n)
class Solution {
    public String removeBackspaces(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        int i = 0;
        while(i < str.length()) {
            if(str.charAt(i) == '#') {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {
                stack.push(str.charAt(i));
            }
            i++;
        }

        StringBuilder result = new StringBuilder("");
        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
    public boolean backspaceCompare(String s, String t) {
        String sResult = removeBackspaces(s);
        String tResult = removeBackspaces(t);

        return sResult.equals(tResult);
    }
}
