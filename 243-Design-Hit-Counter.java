/*
You need to design a hit counter system that tracks the number of hits received within the past 5 minutes (300 seconds).

The system should support two main operations:

Recording hits: When a hit occurs at a specific timestamp (in seconds), the system should record it. Multiple hits can happen at the same timestamp.

Querying hit count: Given a timestamp, the system should return the total number of hits that occurred in the past 300 seconds from that timestamp. Specifically, it counts all hits in the time range [timestamp - 299, timestamp].

The HitCounter class needs three methods:

HitCounter(): Initializes the hit counter system
hit(timestamp): Records a hit at the given timestamp
getHits(timestamp): Returns the count of all hits in the past 300 seconds from the given timestamp
For example, if hits occurred at timestamps 1, 2, 3, and 301, calling getHits(301) would return 1 (only the hit at timestamp 301 is within the past 300 seconds), while getHits(303) would still return 1 since the hit at timestamp 1 is now more than 300 seconds old.
*/

// given: a HitCounter class with two methods hit and getHits()
// required: implement the class

// constraints
// Timestamps are provided in seconds
// Calls to the system happen in chronological order (timestamps are monotonically increasing)
// Multiple hits may arrive at the same timestamp


class HitCounter {
    private List<Integer> hits;
    
    // sc: O(n)
    public HitCounter() {
        hits = new ArrayList<>();    
    }
    
    // tc: O(1)
    public void hit(int timestamp) {
        hits.add(timestamp);
    }
    
    // tc: O(n)
    public int getHits(int timestamp) {
        int result = 0;
        int i = hits.size() - 1;
        
        while(i >= 0 && hits.get(i) >= timestamp - 299) {
            result++;
            i--;
        }
        
        return result;
    }
}




class HitCounter {
    private List<Integer> hits;
    
    // sc: O(n)
    public HitCounter() {
        hits = new ArrayList<>();    
    }
    
    // tc: O(1)
    public void hit(int timestamp) {
        hits.add(timestamp);
    }
    
    // tc: O(logn)
    public int getHits(int timestamp) {
        int start = 0;
        int end = hits.size() - 1;
        int result = -1;
        
        while(start <= end) {
            int mid = start + (end - start) / 2;
            
            if(hits.get(mid) >= timestamp - 299) {
                result = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        
        if(result == -1) return 0;
        
        return hits.size() - result;
    }
}




class HitCounter {
    private Deque<Integer> queue;
    
    // sc: O(k) where k is the no. of hits in the last 300 seconds
    public HitCounter() {
        queue = new ArrayDeque<>();    
    }
    
    public void removeExpiredTimestamps(int timestamp) {
        while(!queue.isEmpty() && queue.peekFirst() < timestamp - 299) {
            queue.pollFirst();
        }
    }
    
    // tc: O(1) amortized
    public void hit(int timestamp) {
        removeExpiredTimestamps(timestamp);
        queue.offerLast(timestamp);
    }
    
    // tc: O(1) amortized
    public int getHits(int timestamp) {
        removeExpiredTimestamps(timestamp);
        return queue.size();
    }
}




class HitCounter {
    private final int[] times;
    private final int[] counts;
    
    // sc: O(1)
    public HitCounter() {
        // Fixed window size of 300 seconds
        times = new int[300];   // i -> timestamp
        counts = new int[300];   // i -> count at times[i] timestamp
    }
    
    // tc: O(1)
    public void hit(int timestamp) {
        int idx = timestamp % 300;
        if(times[idx] != timestamp) {
            times[idx] = timestamp;
            counts[idx] = 1;
        }
        else {
            counts[idx]++;
        }
    }
    
    // tc: O(1)
    public int getHits(int timestamp) {
        int result = 0;
        for(int i = counts.size() - 1; i >= 0; i--) {
            if(times[i] >= timestamp - 299) {
                result += count[i];
            }
        }
        return result;
    }
}