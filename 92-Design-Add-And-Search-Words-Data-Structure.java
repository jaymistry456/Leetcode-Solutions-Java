// https://leetcode.com/problems/design-add-and-search-words-data-structure/


// given: a WordDictionary class with 2 methods addWord() and searchWord()
// required: implement the class and its 2 methods, addWord() adds a word to the dictionary and searchWord() search word in the dictionary and return true/false, the word passed in searchWord() can contain '.' which can match any character

// constraints
// length of the word in [1, 25]
// words can contain only lowercase letters
// atmost 10k calls will be made to both the methods

// tc: O(n*l) for search() and O(l) for add(), sc: O(n*l)
class WordDictionary {
    List<String> dictionary;

    public WordDictionary() {
        dictionary = new ArrayList<>();    
    }
    
    public void addWord(String word) {
        dictionary.add(word);
    }
    
    public boolean search(String word) {
        for(String dictWord: dictionary) {
            if(dictWord.length() != word.length()) {
                continue;
            }

            int i = 0;
            while(i < dictWord.length()) {
                if(word.charAt(i) != '.' && dictWord.charAt(i) != word.charAt(i)) {
                    break;
                }
                i++;
            }
            if(i == dictWord.length()) {
                return true;
            }
        }

        return false;
    }
}



// tc: O(l) for addWord and O(26^l) for search, sc: O(n*l)
class TrieNode {
    private TrieNode[] children;
    private boolean word;

    public TrieNode() {
        children = new TrieNode[26];   // ch's index -> TrieNode
        word = false;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public boolean isWord() {
        return word;
    }

    public void setWord(boolean word) {
        this.word = word;
    }
}

class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode curr = root;

        for(char ch: word.toCharArray()) {
            if(curr.getChildren()[ch - 'a'] == null) {
                curr.getChildren()[ch - 'a'] = new TrieNode();
            }
            curr = curr.getChildren()[ch - 'a'];
        }
        curr.setWord(true);
    }
    
    private boolean dfs(String word, int index, TrieNode curr) {
        for(int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(ch == '.') {
                for(TrieNode child: curr.getChildren()) {
                    if(child != null && dfs(word, i + 1, child)) {
                        return true;
                    }
                }
                return false;
            }
            else {
                if(curr.getChildren()[ch - 'a'] == null) {
                    return false;
                }
                curr = curr.getChildren()[ch - 'a'];
            }
        }

        return curr.isWord();
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }
}


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */