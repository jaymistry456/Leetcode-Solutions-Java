// https://leetcode.com/problems/online-stock-span/


// given: a class StockSpannner with a method next
// required: implement the class

// constraints
// price in [1, 100k]
// at most 10k calls will be made to next method

// tc: O(n^2), sc: O(n)
class StockSpanner {
    List<Integer> stocks;

    public StockSpanner() {
        stocks = new ArrayList<>();
    }
    
    public int next(int price) {
        stocks.add(price);

        int result = 0;
        for(int i = stocks.size() - 1; i >= 0; i--) {
            if(stocks.get(i) > price) {
                break;
            }
            result++;
        }

        return result;
    }
}



// tc: O(n), sc: O(n)
class StockSpanner {
    Deque<int[]> stack;

    public StockSpanner() {
        stack = new ArrayDeque<>();   // [price, span] with smallest prices at the top
    }
    
    public int next(int price) {
        int result = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price) {
            int[] item = stack.pop();
            result += item[1];
        }

        stack.push(new int[]{price, result});

        return result;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */