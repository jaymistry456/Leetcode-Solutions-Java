// https://leetcode.com/problems/implement-trie-prefix-tree/


// general-purpose
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;

        for(char ch: word.toCharArray()) {
            if(!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }

        curr.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;

        for(char ch: word.toCharArray()) {
            if(!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }

        return curr.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;

        for(char ch: prefix.toCharArray()) {
            if(!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }

        return true;
    }
}



// lowercase letters
class TrieNode {
    TrieNode[] children;
    boolean isWord;

    public TrieNode() {
        children = new TrieNode[26];
        isWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;

        for(char ch: word.toCharArray()) {
            if(curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }

        curr.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;

        for(char ch: word.toCharArray()) {
            if(curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }

        return curr.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;

        for(char ch: prefix.toCharArray()) {
            if(curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */