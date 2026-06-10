// https://leetcode.com/problems/maximum-frequency-stack/


// given: a FreqStack data structure with push and pop operations, but there is a catch, pop return the most frequent element from the stack (not the top of the stack)
// required: implement the data structure

// constraints
// each val in [0, 10^9]
// at most 20k calls will be made to push and pop
// all operations are valid

class FreqStack {
    Map<Integer, Integer> freq;   // num -> freq of num
    Map<Integer, Deque<Integer>> group;   // freq -> Stack of nums
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        // 1. Increase the freq of val
        freq.put(val, freq.getOrDefault(val, 0) + 1);

        // 2. Add val to its new freq group (without removing the old group)
        int currFreq = freq.get(val);
        group.putIfAbsent(currFreq, new ArrayDeque<>());
        group.get(currFreq).push(val);

        // 3. Update maxFreq
        maxFreq = Math.max(maxFreq, currFreq);
    }
    
    public int pop() {
        // 1. Get the maxFreq stack
        Deque<Integer> stack = group.get(maxFreq);

        // 2. Pop the top element from the stack and store it in result
        int result = stack.pop();

        // 3. If the resulting stack is empty, update maxFreq
        if(stack.isEmpty()) maxFreq--;

        // 4. Update the freq of resulting element in freq map
        freq.put(result, freq.get(result) - 1);

        return result;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */