// https://leetcode.com/problems/reorganize-string/


// given: a string s
// required: rearrange the characters in the string so that no 2 adjacent characters are the same

// constraints
// length of s in [1, 500]
// s contains only lowercase characters

// tc: O(nlogn), sc: O(n)
record PQItem(char ch, int freq) {}
record QueueItem(char ch, int freq, int holdUntil) {}

class Solution {
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<PQItem> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.freq(), a.freq())
        );
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            pq.offer(new PQItem(entry.getKey(), entry.getValue()));
        }

        Deque<QueueItem> queue = new ArrayDeque<>();
        
        int turn = 0;
        StringBuilder result = new StringBuilder("");

        while(!pq.isEmpty() || !queue.isEmpty()) {
            while(!queue.isEmpty() && queue.peek().holdUntil() <= turn) {
                QueueItem item = queue.poll();
                pq.offer(new PQItem(item.ch(), item.freq()));
            }

            if(pq.isEmpty()) {
                return "";
            }

            PQItem item = pq.poll();
            result.append(item.ch());
            if(item.freq() > 1) {
                queue.offer(new QueueItem(item.ch(), item.freq() - 1, turn + 2));
            }

            turn++;
        }

        return result.toString();
    }
}




// tc: O(nlogn), sc: O(n)
class Solution {
    public String reorganizeString(String s) {
        int[] freq = new int[26];
        for(char ch: s.toCharArray()) {
            freq[ch - 'a']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[1], a[1])
        );   // [charIdx, freq]
        for(int i = 0; i < freq.length; i++) {
            if(freq[i] != 0) {
                pq.offer(new int[]{i, freq[i]});
            }
        }

        Deque<int[]> queue = new ArrayDeque<>();
        
        int turn = 0;
        StringBuilder result = new StringBuilder("");

        while(!pq.isEmpty() || !queue.isEmpty()) {
            while(!queue.isEmpty() && queue.peek()[2] <= turn) {
                int[] item = queue.poll();
                pq.offer(new int[]{item[0], item[1]});
            }

            if(pq.isEmpty()) {
                return "";
            }

            int[] item = pq.poll();
            result.append((char)(item[0] + 'a'));
            if(item[1] > 1) {
                queue.offer(new int[]{item[0], item[1] - 1, turn + 2});
            }

            turn++;
        }

        return result.toString();
    }
}