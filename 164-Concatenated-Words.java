// https://leetcode.com/problems/concatenated-words/


// given: an array of words
// required: return a list of all concatenated words which can be made using the words, meaning the concatenated word should be constructed from words and it should also be present in words itself

// constraints
// length of words in [1, 10k]
// length of each word in [1, 30] and contain lowercase letters only
// all words are unique

// tc: O(n * 2^l), sc: O(n * l)
class Solution {
    List<String> result = new ArrayList<>();
    Set<String> set = new HashSet<>();

    public boolean dfs(String word, int i) {
        if (i == word.length()) {
            return true;
        }

        for (int j = i; j < word.length(); j++) {
            String substr = word.substring(i, j + 1);
            
            if (set.contains(substr) && dfs(word, j + 1)) {
                return true;
            }
        }

        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        for (String word: words) {
            set.add(word);
        }

        for (String word: words) {
            set.remove(word);

            if (dfs(word, 0)) {
                result.add(word);
            }

            set.add(word);
        }

        return result;
    }
}





// tc: O(n * l^2), sc: O(n * l)
class Solution {
    List<String> result = new ArrayList<>();
    Set<String> set = new HashSet<>();

    public boolean dfs(String word, int i, Boolean[] dp) {
        if (i == word.length()) {
            return true;
        }

        if (dp[i] != null) {
            return dp[i];
        }

        for (int j = i; j < word.length(); j++) {
            String substr = word.substring(i, j + 1);
            
            if (set.contains(substr) && dfs(word, j + 1, dp)) {
                dp[i] = true;
                return true;
            }
        }

        dp[i] = false;
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        for (String word: words) {
            set.add(word);
        }

        for (String word: words) {
            Boolean[] dp = new Boolean[word.length()];

            set.remove(word);

            if (dfs(word, 0, dp)) {
                result.add(word);
            }

            set.add(word);
        }

        return result;
    }
}