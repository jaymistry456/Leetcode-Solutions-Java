// https://leetcode.com/problems/task-scheduler/


// given: a character array of tasks and an integer n where n represents the gap between tasks
// required: return the minimum no of CPU intervals required to complete all tasks

// constraints
// length of tasks in [1, 10k]
// each tasks[i] is an uppercase letter
// n in [0, 100]

// tc: O(time), sc: O(1)
record QueueItem(char task, int freq, int nextTime) {}
record PQItem(char task, int freq) {}

class Solution {
    public int leastInterval(char[] tasks, int n) {
        // 1. Calculate the freq of each task
        Map<Character, Integer> map = new HashMap<>();   // task -> freq of task
        for(char ch: tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // 2. Put the freq in a PriorityQueue (maxHeap) and have a queue which stores the tasks which are waiting
        PriorityQueue<PQItem> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.freq(), a.freq())
        );   // [task, freq] sorted by freq in decreasing order
        Deque<QueueItem> queue = new ArrayDeque<>();   // [task, freq, time]
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            pq.offer(new PQItem(entry.getKey(), entry.getValue()));
        }

        // 3. Simulate
        int time = 0;
        while(!pq.isEmpty() || !queue.isEmpty()) {
            while(!queue.isEmpty() && queue.peek().nextTime() <= time) {
                QueueItem item = queue.poll();
                pq.offer(new PQItem(item.task(), item.freq()));
            }

            if(!pq.isEmpty()) {
                PQItem item = pq.poll();
                char task = item.task();
                int freq = item.freq();
                freq--;
                if(freq != 0) {
                    queue.offer(new QueueItem(task, freq, time + n + 1));
                }
            }

            time++;
        }

        return time;
    }
}




// Simpler way
// tc: O(time), sc: O(1)
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // 1. Calculate the freq of each task
        int[] freqArray = new int[26];
        for(char ch: tasks) {
            freqArray[ch - 'A']++;
        }

        // 2. Put the freq in a PriorityQueue (maxHeap) and have a queue which stores the tasks which are waiting
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );   // freq sorted decreasing order
        Deque<int[]> queue = new ArrayDeque<>();   // [freq, time]
        for(int currFreq: freqArray) {
            if(currFreq != 0) {
                pq.offer(currFreq);
            }
        }

        // 3. Simulate
        int time = 0;
        while(!pq.isEmpty() || !queue.isEmpty()) {
            while(!queue.isEmpty() && queue.peek()[1] <= time) {
                pq.offer(queue.poll()[0]);
            }

            if(!pq.isEmpty()) {
                int currFreq = pq.poll();
                currFreq--;
                if(currFreq != 0) {
                    queue.offer(new int[]{currFreq, time + n + 1});
                }
            }

            time++;
        }

        return time;
    }
}