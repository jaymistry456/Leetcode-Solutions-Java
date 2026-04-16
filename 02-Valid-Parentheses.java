// https://leetcode.com/problems/valid-parentheses


// given: a string s
// required: check whether the string is valid parentheses

// constraints
// length of the string in [1, 10k]
// string contains only parentheses (){}[]

// tc: O(n^2), sc: O(n)
class Solution {
    public boolean isValid(String s) {
        while(s.contains("()") || s.contains("{}") || s.contains("[]")) {
            if(s.contains("()")) {
                s = s.replace("()", "");
            }
            else if(s.contains("{}")) {
                s = s.replace("{}", "");
            }
            else {
                s = s.replace("[]", "");
            }
        }

        return s.length() == 0;
    }
}



// tc: O(n), sc: O(n)
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();   // close -> open
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for(char ch: s.toCharArray()) {
            if(ch == ')' || ch == '}' || ch == ']') {
                if(stack.isEmpty() || map.get(ch) != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
            else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}