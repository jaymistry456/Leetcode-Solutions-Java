// https://leetcode.com/problems/single-threaded-cpu/


// given: a 2D array tasks where each task is [enqueTime, processingTime]
// required: return the order in which the tasks will be performed

// constraints
// n in [1, 100k]
// each enqueTime, processingTime in [1, 10^9]

// tc: O(nlogn + idleTime), sc: O(n)
class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if(a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });   // [enqueueTime, processingTime, idx]

        for(int i = 0; i < n; i++) {
            int[] task = tasks[i];
            int enqueueTime = task[0];
            int processingTime = task[1];

            pq.offer(new int[]{enqueueTime, processingTime, i});
        }

        PriorityQueue<int[]> pq2 = new PriorityQueue<>(
            (a, b) -> {
                if(a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );   // [processingTime, idx];

        int[] result = new int[n];
        int time = 0;
        int i = 0;
        while(i < n) {
            while(!pq.isEmpty() && pq.peek()[0] <= time) {
                int[] item = pq.poll();
                pq2.offer(new int[]{item[1], item[2]});
            }

            if(!pq2.isEmpty()) {
                int[] currItem = pq2.poll();
                time += currItem[0];
                result[i] = currItem[1];
                i++;
            } else {
                time++;
            }
        }

        return result;
    }
}




// tc: O(nlogn), sc: O(n)
class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if(a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });   // [enqueueTime, processingTime, idx]

        for(int i = 0; i < n; i++) {
            int[] task = tasks[i];
            int enqueueTime = task[0];
            int processingTime = task[1];

            pq.offer(new int[]{enqueueTime, processingTime, i});
        }

        PriorityQueue<int[]> pq2 = new PriorityQueue<>(
            (a, b) -> {
                if(a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );   // [processingTime, idx];

        int[] result = new int[n];
        int time = 0;
        int i = 0;
        while(i < n) {
            while(!pq.isEmpty() && pq.peek()[0] <= time) {
                int[] item = pq.poll();
                pq2.offer(new int[]{item[1], item[2]});
            }

            if(!pq2.isEmpty()) {
                int[] currItem = pq2.poll();
                time += currItem[0];
                result[i] = currItem[1];
                i++;
            } else {
                time = pq.peek()[0];
            }

        }

        return result;
    }
}