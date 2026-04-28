// https://leetcode.com/problems/min-stack/


// given: a MinStack class
// required: implement the MinStack class where push, pop and getMin take O(1) time

// constraints
// each val in [-2^31, 2^31 - 1]
// all operations are valid
// at most 30k calls will be made to the operations

class MinStack {
    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty()) {
            minStack.push(val);
        }
        else {
            int minVal = Math.min(minStack.peek(), val);
            minStack.push(minVal);
        }
    }
    
    public void pop() {
        minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */