// https://leetcode.com/problems/word-search-ii/


// given: an mxn board of letters and an array of words
// required: return all words which can be constructed on the board

// constraints
// m, n in [1, 12]
// each letter is lowercase
// length of the words array in [1, 30k]
// each word length in [1, 10]
// all words in the array are unique

// tc: O(m*n * w*l), sc: O(4*l)
class Solution {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean dfs(char[][] board, int r, int c, String word, int i) {
        if(board[r][c] != word.charAt(i)) {
            return false;
        }

        if(i == word.length() - 1) {
            return true;
        }

        char temp = board[r][c];
        board[r][c] = '#';

        for(int[] dir: directions) {
            int neiR = r + dir[0];
            int neiC = c + dir[1];

            if(neiR >= 0 && neiR < board.length &&
                neiC >= 0 && neiC < board[0].length &&
                board[neiR][neiC] != '#'
            ) {
                if(dfs(board, neiR, neiC, word, i + 1)) {
                    board[r][c] = temp;
                    return true;
                }
            }
        }

        board[r][c] = temp;

        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();

        for(String word: words) {
            for(int r = 0; r < board.length; r++) {
                for(int c = 0; c < board[0].length; c++) {
                    if(dfs(board, r, c, word, 0)) {
                        result.add(word);
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }
}




// HashMap Trie
// tc: O(m*n * t), sc: O(m*n) where t is the length of the trie
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
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
}

class Solution {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Set<String> result = new HashSet<>();

    public void dfs(char[][] board, int r, int c, TrieNode curr, String path) {
        char ch = board[r][c];
        if(!curr.children.containsKey(ch)) {
            return;
        }

        path += ch;
        curr = curr.children.get(ch);
        if(curr.isWord) {
            result.add(path);
        }
        
        char temp = board[r][c];
        board[r][c] = '#';
        for(int[] dir: directions) {
            int neiR = r + dir[0];
            int neiC = c + dir[1];

            if(neiR >= 0 && neiR < board.length &&
                neiC >= 0 && neiC < board[0].length &&
                board[neiR][neiC] != '#'
            ) {
                dfs(board, neiR, neiC, curr, path);
            }
        }
        board[r][c] = temp;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();

        for(String word: words) {
            trie.insert(word);
        }

        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, trie.root, "");
            }
        }

        return new ArrayList<>(result);
    }
}




// Array Trie
// tc: O(m*n * t), sc: O(m*n) where t is the length of the trie
class TrieNode {
    TrieNode[] children;
    boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.isWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
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
}

class Solution {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Set<String> result = new HashSet<>();

    public void dfs(char[][] board, int r, int c, TrieNode curr, String path) {
        char ch = board[r][c];
        if(curr.children[ch - 'a'] == null) {
            return;
        }

        path += ch;
        curr = curr.children[ch - 'a'];
        if(curr.isWord) {
            result.add(path);
        }
        
        char temp = board[r][c];
        board[r][c] = '#';
        for(int[] dir: directions) {
            int neiR = r + dir[0];
            int neiC = c + dir[1];

            if(neiR >= 0 && neiR < board.length &&
                neiC >= 0 && neiC < board[0].length &&
                board[neiR][neiC] != '#'
            ) {
                dfs(board, neiR, neiC, curr, path);
            }
        }
        board[r][c] = temp;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();

        for(String word: words) {
            trie.insert(word);
        }

        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, trie.root, "");
            }
        }

        return new ArrayList<>(result);
    }
}