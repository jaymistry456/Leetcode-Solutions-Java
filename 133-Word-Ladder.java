// given: beginWord, endWord and a dictionary of words
// required: the no. of steps required to reach endWord from beginWord by changing a single letter at a time using the dictionary

// constraints
// length of the dictionary in [1, 5000]
// all are in lowercase letter
// beginWord != endWord
// all words in wordList are unique

// DFS
// tc: O(n^2 * m), sc: O(n)
class Solution {
    Set<String> visited = new HashSet<>();
    int result = Integer.MAX_VALUE;

    public boolean isDiffOne(String first, String second) {
        int diff = 0;
        for(int i = 0; i < first.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public void dfs(String currWord, String endWord, List<String> wordList, int currSteps) {
        if(currWord.equals(endWord)) {
            result = Math.min(result, currSteps);
            return;
        }

        if(currSteps >= result) {
            return;
        }

        for(String nextWord: wordList) {
            if(!visited.contains(nextWord) && isDiffOne(currWord, nextWord)) {
                visited.add(nextWord);
                dfs(nextWord, endWord, wordList, currSteps + 1);
                visited.remove(nextWord);
            }
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        dfs(beginWord, endWord, wordList, 1);

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}




// BFS
// tc: O(n^2 * m), sc: O(n^2 * m)
class Solution {
    public boolean isDiffOne(String first, String second) {
        int diff = 0;
        for(int i = 0; i < first.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        int result = 1;
        
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                String currWord = queue.poll();
                if(currWord.equals(endWord)) {
                    return result;
                }

                for(String nextWord: wordList) {
                    if(!visited.contains(nextWord) && isDiffOne(currWord, nextWord)) {
                        visited.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }

            result++;
        }

        return 0;
    }
}



// Can we improve this even further?
// We are only given words of lowercase letters, what if we generate all the next possible words of a given word from the dictionary beforehand
// This way we won't have to call isDiffOne() again and again
// We can keep a hashmap of patterns -> words matching that pattern
// prefix + '*' + suffix -> list of words matching this pattern
// tc: O(n * m^2), sc: O(n * m^2)
class Solution {
    public boolean isDiffOne(String first, String second) {
        int diff = 0;
        for(int i = 0; i < first.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();   // patterns -> List of words matching this pattern
        for(String word: wordList) {
            for(int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                map.putIfAbsent(pattern, new ArrayList<>());
                map.get(pattern).add(word);
            }
        }

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        int result = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                String currWord = queue.poll();

                if(currWord.equals(endWord)) {
                    return result;
                }

                for(int j = 0; j < currWord.length(); j++) {
                    String pattern = currWord.substring(0, j) + "*" + currWord.substring(j + 1, currWord.length());

                    if(!map.containsKey(pattern)) {
                        continue;
                    }

                    for(String neighbor: map.get(pattern)) {
                        if(!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }

            result++;
        }

        return 0;
    }
}