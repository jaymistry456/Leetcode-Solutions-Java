// https://leetcode.com/problems/kth-largest-element-in-a-stream/


// given: a class which takes an integer k and an array of integers and a method add which adds a new integer to the array and returns the kth largest val from the array
// required: implement the class

// constraints
// length of the array in [0, 10k]
// k in [1, length of the array + 1]
// each value in nums in [-10k, 10k]
// atmost 10k calls will be made to add()

class KthLargest {
    private int k;
    private List<Integer> numsList;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.numsList = new ArrayList<>();
        for(int num: nums) {
            this.numsList.add(num);
        }
    }

    // tc: O(nlogn), sc: O(n)
    public int add(int val) {
        this.numsList.add(val);
        Collections.sort(numsList);
        return numsList.get(numsList.size() - this.k);
    }
}




class KthLargest {
    private int k;
    private PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>();   // Min-heap
        for(int num: nums) {
            this.pq.offer(num);
            if(this.pq.size() > this.k) pq.poll();
        }
    }

    // tc: O(logk), sc: O(k)
    public int add(int val) {
        pq.offer(val);
        if(pq.size() > k) pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */