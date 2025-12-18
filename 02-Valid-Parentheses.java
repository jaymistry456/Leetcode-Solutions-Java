// https://leetcode.com/problems/valid-parentheses


class Solution {
    public boolean isValid(String s) {
        // // brute-force
        // // TC: O(n^2), SC: O(n)
        // while(s.contains("()") || s.contains("{}") || s.contains("[]")) {
        //     if(s.contains("()")) {
        //         s = s.replace("()", "");
        //     }
        //     else if(s.contains("{}")) {
        //         s = s.replace("{}", "");
        //     }
        //     else {
        //         s = s.replace("[]", "");
        //     }
        // }
        // return s.length() == 0;


        // optimal
        // TC: O(n), SC: O(n)
        Map<Character, Character> map = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for(Character c: s.toCharArray()) {
            if(c == ')' || c == '}' || c == ']') {
                if(stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}