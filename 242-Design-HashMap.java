// https://leetcode.com/problems/design-hashmap/


// given: a class called MyHashMap
// required: implements the class

// constraints
// key, value in [0, 1M]
// at most 10k calls will be made to put, get and remove methods

class ListNode {
    private int key;
    private int value;
    private ListNode next;

    public ListNode() {}

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public ListNode(int key, int value, ListNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

class MyHashMap {
    private List<ListNode> map;   // hashValue -> ListNode (starting with dummy)

    public MyHashMap() {
        map = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            map.add(new ListNode());
        }
    }

    public int hash(int key) {
        return key % map.size();
    }
    
    public void put(int key, int value) {
        int hashValue = hash(key);

        ListNode curr = map.get(hashValue);
        while(curr.getNext() != null) {
            if(curr.getNext().getKey() == key) {
                curr.getNext().setValue(value);
                return;
            }
            curr = curr.getNext();
        }
        curr.setNext(new ListNode(key, value));
    }
    
    public int get(int key) {
        int hashValue = hash(key);

        ListNode curr = map.get(hashValue);
        while(curr.getNext() != null) {
            if(curr.getNext().getKey() == key) {
                return curr.getNext().getValue();
            }
            curr = curr.getNext();
        }
        return -1;
    }
    
    public void remove(int key) {
        int hashValue = hash(key);

        ListNode curr = map.get(hashValue);
        while(curr.getNext() != null) {
            if(curr.getNext().getKey() == key) {
                curr.setNext(curr.getNext().getNext());
                return;
            }
            curr = curr.getNext();
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */