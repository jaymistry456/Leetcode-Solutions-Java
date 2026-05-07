// https://leetcode.com/problems/lru-cache/


// given: calls to build the LRU cache and calls to put and get operations in cache
// required: implement the cache in O(1) tc

// constraints
// capacity in [1, 3000]
// each key in [0, 10k]
// each value in [0, 100k]
// max no. of calls is 200k
class ListNode {
    private int key;
    private int val;
    private ListNode prev;
    private ListNode next;

    public ListNode() {
        this.key = 0;
        this.val = 0;
        this.prev = null;
        this.next = null;
    }

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }

    public ListNode(int key, int val, ListNode prev, ListNode next) {
        this.key = key;
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public int getVal() {
        return val;
    }

    public ListNode getPrev() {
        return prev;
    }

    public ListNode getNext() {
        return next;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

class LRUCache {
    private int capacity;
    private Map<Integer, ListNode> cache;
    private ListNode left;
    private ListNode right;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.left = new ListNode();
        this.right = new ListNode();
        this.left.setNext(right);
        this.right.setPrev(left);
    }

    public void removeNode(ListNode node) {
        ListNode prevNode = node.getPrev();
        ListNode nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
    }

    public void addNodeToRight(ListNode node) {
        ListNode currRightMost = right.getPrev();
        currRightMost.setNext(node);
        right.setPrev(node);
        node.setPrev(currRightMost);
        node.setNext(right);
    }
    
    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }

        ListNode currNode = cache.get(key);
        removeNode(currNode);
        addNodeToRight(currNode);
        return currNode.getVal();
    }
    
    public void put(int key, int value) {
        // case 1: if key already exists
        if(cache.containsKey(key)) {
            ListNode currNode = cache.get(key);
            removeNode(currNode);
            addNodeToRight(currNode);
            currNode.setVal(value);
        }
        // case 2: if key does NOT exist
        else {
            ListNode newNode = new ListNode(key, value);
            cache.put(key, newNode);
            addNodeToRight(newNode);
            if(cache.size() > capacity) {
                ListNode nodeToRemove = left.getNext();
                cache.remove(nodeToRemove.getKey());
                removeNode(nodeToRemove);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */