// https://leetcode.com/problems/top-k-frequent-words/


// given: an array of strings and an integer k
// required: return the k most frequent words in the array and in lexicographical order

// constraints
// length of words in [1, 500]
// each word length in [1, 10]
// each word is made up of lowercase letters
// k in [1, no. of unique words]

// tc: O(nlogn), sc: O(n)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();   // word -> freq
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> strings = new ArrayList<>(map.keySet());

        List<String> result = strings.stream()
                                        .sorted((a, b) -> {
                                            if(map.get(a) != map.get(b)) {
                                                return Integer.compare(map.get(b), map.get(a));
                                            }
                                            return a.compareTo(b);
                                        })
                                        .limit(k)
                                        .collect(Collectors.toList());
        
        return result;
    }
}




// tc: O(nlogk), sc: O(n)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();   // word -> freq
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                int freqA = map.get(a);
                int freqB = map.get(b);
                if(freqA != freqB) {
                    return Integer.compare(freqA, freqB);
                }
                return b.compareTo(a);
            }
        );

        for(String word: map.keySet()) {
            pq.offer(word);
            if(pq.size() > k) {
                pq.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(pq.poll());
        }
        Collections.reverse(result);

        return result;
    }
}