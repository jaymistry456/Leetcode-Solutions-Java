// https://leetcode.com/problems/decode-string/


// given: a string s
// required: decode the string

// constraints
// length of s in [1, 30]
// s contains only lowercase letters, digits and square brackets
// all integers are in [1, 300]

// tc: O(n), sc: O(n)
class Solution {
    public String decodeString(String s) {
        Deque<String> stack = new ArrayDeque<>();
        String result = "";
        int currNum = 0;

        for(char ch: s.toCharArray()) {
            if(ch == '[') {
                stack.push(result);
                stack.push(String.valueOf(currNum));
                result = "";
                currNum = 0;
            } else if(ch == ']') {
                int prevNum = Integer.parseInt(stack.pop());
                String prevStr = stack.pop();
                result = prevStr + result.repeat(prevNum);
            } else if(ch >= '0' && ch <= '9') {
                currNum = currNum * 10 + (ch - '0');
            } else {
                result += ch;
            }
        }

        return result;
    }
}