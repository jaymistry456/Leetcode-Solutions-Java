// https://leetcode.com/problems/design-browser-history/


// given: a class BrowserHistory with 3 methods: (1) visit: visits the url and clears up all forward history, (2) back: moves steps back in history (if there are steps possible) and return that url, (3) forward: move steps forward in history (if there are steps possible) and return that url
// required: implement the class and its methods

// constraints
// length of homepage in [1, 20]
// length of url in [1, 20]
// steps in [1, 100]
// homepage and url only contain lowercase letters and '.'
// at most 5000 calls will be made to the methods

class BrowserHistory {
    List<String> history;
    int curr;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        curr = 0;
    }
    
    public void visit(String url) {
        history = history.subList(0, curr + 1);
        history.add(url);
        curr++;
    }
    
    public String back(int steps) {
        curr = Math.max(0, curr - steps);
        return history.get(curr);
    }
    
    public String forward(int steps) {
        curr = Math.min(history.size() - 1, curr + steps);
        return history.get(curr);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */