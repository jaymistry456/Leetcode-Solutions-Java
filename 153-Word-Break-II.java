// https://leetcode.com/problems/word-break-ii/


// given: a string s and an array of words
// required: using s and the array of words, return a list of all possible sentences which can constructed from letters of s using the array of words where each word can be reused

// constraints
// length of s in [1, 20]
// length of the array in [1, 1000]
// each letter is lowercase

// tc: O(2^n*n^2), sc: O(2^n*n)
class Solution {
    Set<String> set = new HashSet<>();
    List<String> result = new ArrayList<>();
    List<String> currResult = new ArrayList<>();

    public void dfs(String s, int i) {
        if(i == s.length()) {
            result.add(String.join(" ", currResult));
            return;
        }

        for(int j = i; j < s.length(); j++) {
            String substr = s.substring(i, j + 1);

            if(set.contains(substr)) {
                currResult.add(substr);
                dfs(s, j + 1);
                currResult.remove(currResult.size() - 1);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        for(String word: wordDict) {
            set.add(word);
        }

        dfs(s, 0);

        return result;
    }
}