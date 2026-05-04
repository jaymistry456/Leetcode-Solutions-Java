// https://leetcode.com/problems/letter-combinations-of-a-phone-number/


// given: a string of digits
// required: return the different combinations that can be formed from the digits in the string

// constraints
// length of digits in [1, 4]
// each digit is in range ['2', '9']

// tc: O(m^n), sc: O(n)
class Solution {
    Map<Character, List<Character>> map;
    List<Character> currResult;
    List<String> result;

    public Solution() {
        map = new HashMap<>();
        map.put('2', new ArrayList<>(List.of('a', 'b', 'c')));
        map.put('3', new ArrayList<>(List.of('d', 'e', 'f')));
        map.put('4', new ArrayList<>(List.of('g', 'h', 'i')));
        map.put('5', new ArrayList<>(List.of('j', 'k', 'l')));
        map.put('6', new ArrayList<>(List.of('m', 'n', 'o')));
        map.put('7', new ArrayList<>(List.of('p', 'q', 'r', 's')));
        map.put('8', new ArrayList<>(List.of('t', 'u', 'v')));
        map.put('9', new ArrayList<>(List.of('w', 'x', 'y', 'z')));

        currResult = new ArrayList<>();
        result = new ArrayList<>();
    }

    public void dfs(int i, String digits) {
        if(i == digits.length()) {
            StringBuilder builder = new StringBuilder("");
            for(char ch: currResult) {
                builder.append(ch);
            }
            result.add(builder.toString());
            return;
        }

        for(char ch: map.get(digits.charAt(i))) {
            currResult.add(ch);
            dfs(i + 1, digits);
            currResult.remove(currResult.size() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        dfs(0, digits);

        return result;
    }
}