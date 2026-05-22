// given: calls tocreate the MedianFinder class with methods which add num and find median
// required: implement the class and its methods in such a way that tc is reduced

// constraints
// each value in [-100k, 100k]
// median will exist
// at most 50k calls will be made to addNum and findMedian

class MedianFinder {
    PriorityQueue<Integer> minHeap;   // For storing the larger half
    PriorityQueue<Integer> maxHeap;   // For storing the smaller half

    public MedianFinder() {
        minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    }

    private void balanceHeaps() {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();

        if(minSize - maxSize > 1) {
            int item = minHeap.poll();
            maxHeap.offer(item);
        }
        else if(maxSize - minSize > 1) {
            int item = maxHeap.poll();
            minHeap.offer(item);
        }
    }
    
    // tc: O(logn) for each call
    public void addNum(int num) {
        if(!minHeap.isEmpty() && num >= minHeap.peek()) {
            minHeap.offer(num);
        }
        else {
            maxHeap.offer(num);
        }
        balanceHeaps();
    }
    
    // tc: O(1) for each call
    public double findMedian() {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();

        if(minSize > maxSize) {
            return minHeap.peek();
        }
        else if (maxSize > minSize) {
            return maxHeap.peek();
        }
        else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}




// For fixed range of numbers
class MedianFinder {
    private int[] freq;
    private int totalCount;
    private int OFFSET;

    public MedianFinder() {
        freq = new int[200001];   // num -> count of num
        totalCount = 0;
        OFFSET = 100000;   // For negative numbers
    }

    // tc: O(1) for each call
    public void addNum(int num) {
        freq[num + OFFSET]++;
        totalCount++;
    }
    
    // tc: O(f) for each call where f is the freq array length
    public double findMedian() {
        if(totalCount % 2 == 1) {
            int target = totalCount / 2;
            int curr = 0;
            for(int i = 0; i < freq.length; i++) {
                curr += freq[i];
                if(target < curr) {
                    return i - OFFSET;
                }
            }
        }
        else {
            int target1 = totalCount / 2 - 1;
            int target2 = totalCount / 2;
            int firstMid = Integer.MIN_VALUE;
            int secondMid = Integer.MIN_VALUE;
            int curr = 0;
            for(int i = 0; i < freq.length; i++) {
                curr += freq[i];
                if(firstMid == Integer.MIN_VALUE && target1 < curr) {
                    firstMid = i - OFFSET;
                }
                if(target2 < curr) {
                    secondMid = i - OFFSET;
                    break;
                }
            }

            return (firstMid + secondMid) / 2.0;
        }

        return -1;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */