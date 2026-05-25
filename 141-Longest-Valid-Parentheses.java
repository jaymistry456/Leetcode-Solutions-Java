// https://leetcode.com/problems/longest-valid-parentheses/


// given: a string s containing parentheses
// required: return the length of the longest well-formed parentheses in the string

// constraints
// length of s in [0, 30k]

// tc: O(n^2), sc: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        int result = 0;

        for(int i = 0; i < s.length(); i++) {
            Deque<Character> stack = new ArrayDeque<>();

            for(int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                if(ch == '(') {
                    stack.push('(');
                }
                else {
                    if(stack.isEmpty()) {
                        break;
                    }
                    stack.pop();
                    if(stack.isEmpty()) {
                        result = Math.max(result, j - i + 1);
                    }
                }
            }
        }

        return result;
    }
}



// tc: O(n), sc: O(n)
class Solution {
    public int longestValidParentheses(String s) {
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }
            else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                }
                result = Math.max(result, i - stack.peek());
            }
        }

        return result;
    }
}