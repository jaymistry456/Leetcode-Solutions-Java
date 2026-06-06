// https://leetcode.com/problems/reconstruct-itinerary/


// given: a 2D arraylist where each list has 2 cities u and v where there is a flight from u to v
// required: reconstruct itineray convering all the tickets and starting from JFK with smallest lexical order

// constraints
// length of tickets in [1, 300]

// We need to start from JFK from traverse through all cities
// If there are multiple flights from a given city, we need to traverse in lexical order of those destinations

// tc: O(eloge), sc: O(v+e)
class Solution {
    Map<String, PriorityQueue<String>> map = new HashMap<>(); // city -> pq of neighbors in asc order
    List<String> result = new ArrayList<>();

    public void dfs(String currCity) {
        if(map.containsKey(currCity)) {
            PriorityQueue<String> neighbors = map.get(currCity);
            while(!neighbors.isEmpty()) {
                String neighbor = neighbors.poll();
                dfs(neighbor);
            }
        }

        result.add(currCity);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        // 1. Construct the graph but instead of a list, we need to use a PQ of destinations sorted in lexical order
        for(List<String> currTicket: tickets) {
            String u = currTicket.get(0);
            String v = currTicket.get(1);
            map.putIfAbsent(u, new PriorityQueue<>());
            map.get(u).offer(v);
        }

        // 2. We start a dfs from JFK and traverse all its neighbors in lexical order from its PQ and put the cities in a result list
        dfs("JFK");

        Collections.reverse(result);
        return result;
    }
}
