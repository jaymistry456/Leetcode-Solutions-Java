// https://neetcode.io/problems/string-encode-and-decode/


// given: 2 methods encode which accepts a list of strings and return an encoded string and decode which accepts a string and decodes it back to the original list of strings
// required: implement both the methods

// constraints
// length of strs list in [0, 100]
// each string length in [0, 200]

// tc: O(n*t), sc: O(n*t)
class Solution {

    public String encode(List<String> strs) {
        StringBuilder result = new StringBuilder("");
        for(String str: strs) {
            result.append(str.length()).append("#").append(str);
        }

        return result.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while(i < str.length()) {
            int j = i;
            while(str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            result.add(str.substring(i, i + length));
            i += length;
        }

        return result;
    }
}
