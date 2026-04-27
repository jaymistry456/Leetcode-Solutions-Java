// https://leetcode.com/problems/clone-graph/


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// given: a connected undirected graph
// required: return a deep copy of the graph

// constraints
// number of nodes in the graph in [0, 100]
// each value in [1, 100]
// each node value is unique
// there are no repeated edges or self-loops in the graph
// the graph is connected and all nodes can be visited starting from the given node

// DFS
// tc: O(n), sc: O(n)
class Solution {
    Map<Node, Node> map = new HashMap<>();   // node -> copy of node

    public void dfs(Node node) {
        map.put(node, new Node(node.val));

        for(Node nei: node.neighbors) {
            if(!map.containsKey(nei)) {
                dfs(nei);
            }
        }
    }

    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }

        dfs(node);

        for(Map.Entry<Node, Node> entry: map.entrySet()) {
            Node key = entry.getKey();
            Node val = entry.getValue();

            for(Node nei: key.neighbors) {
                val.neighbors.add(map.get(nei));
            }
        }

        return map.get(node);
    }
}




// BFS
// tc: O(n), sc: O(n)
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        
        Map<Node, Node> map = new HashMap<>();   // node -> copy of node

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            map.put(curr, new Node(curr.val));

            for(Node nei: curr.neighbors) {
                if(map.containsKey(nei)) {
                    continue;
                }
                queue.offer(nei);
            }
        }

        for(Map.Entry<Node, Node> entry: map.entrySet()) {
            Node key = entry.getKey();
            Node val = entry.getValue();

            for(Node nei: key.neighbors) {
                val.neighbors.add(map.get(nei));
            }
        }

        return map.get(node);
    }
}