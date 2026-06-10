// https://leetcode.com/problems/insert-delete-getrandom-o1/


// given: a RandomizedSet() class with 3 methods: insert which insert a val if val not already present in the set, remove which remove a val which it is present in the set and getRandom which returns a random element from the current set of elements and each element must have the same probability of being returned
// required: implement the class and its methods in O(1) average tc

// constraints
// val in [-2^31, 2^31 - 1]
// at most 200k calls will be made to the methods
// all operations are valid

class RandomizedSet {
    Map<Integer, Integer> map;   // num -> position of num in the list
    List<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;

        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }
    
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;

        // 1. Get the last element from list
        int lastElement = list.get(list.size() - 1);

        // 2. Get the index of val in the list
        int valIndex = map.get(val);

        // 3. Update last element's value in map with val's index
        map.put(lastElement, valIndex);

        // 4. Put the last element at val's index position in the list
        list.set(valIndex, lastElement);

        // 5. Remove val from map and list
        map.remove(val);
        list.remove(list.size() - 1);

        return true;
    }
    
    public int getRandom() {
        int randomIdx = new Random().nextInt(list.size());
        return list.get(randomIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */