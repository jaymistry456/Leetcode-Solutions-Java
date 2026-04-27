// https://leetcode.com/problems/evaluate-reverse-polish-notation/


// given: a string of tokens
// required: evaluate the expression and return the result

// constraints
// length of tokens in [1, 10k]
// each token is either an operator ("+", "-", "*", "/") or an integer in range [-200, 200]

// tc: O(n), sc: O(n)
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(String token: tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {

                int num2 = stack.pop();
                int num1 = stack.pop();
                if(token.equals("+")) {
                    stack.push(num1 + num2);
                }
                else if(token.equals("-")) {
                    stack.push(num1 - num2);
                }
                else if(token.equals("*")) {
                    stack.push(num1 * num2);
                }
                else {
                    stack.push(num1 / num2);
                }
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}