// https://leetcode.com/problems/course-schedule/


// given: an integer numCourses representing the total number of courses and a 2D array prerequisites where each [x, y] means y is a prereq for taking x
// required: check whether all courses can be taken without any issues (there are no circular dependencies)

// constraints
// numCourses in [1, 2000]
// prerequisites length in [0, 5000]
// all pairs are unique in prerequisites

// tc: O(n^2), sc: O(n^2)
class Solution {
    Map<Integer, List<Integer>> map;   // node -> all its prereqs (dependencies)

    public boolean dfs(int currNode, Set<Integer> visited) {
        if(visited.contains(currNode)) {
            return false;
        }
        visited.add(currNode);

        for(int nei: map.get(currNode)) {
            if(!dfs(nei, visited)) {
                return false;
            }
        }
        visited.remove(currNode);

        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Build the graph
        map = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] prereq: prerequisites) {
            map.get(prereq[0]).add(prereq[1]);
        }

        // 2. Traverse each node to check whether none of the nodes have circular dependencies
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(i, new HashSet<>())) {
                return false;
            }
        }

        return true;
    }
}





// tc: O(n), sc: O(n^2)
class Solution {
    Map<Integer, List<Integer>> map;   // node -> all its prereqs (dependencies)
    int[] state;   // unvisited -> 0, visiting in the current path -> 1, visited -> 2

    public boolean dfs(int currNode) {
        if(state[currNode] == 0) {
            state[currNode] = 1;

            for(int nei: map.get(currNode)) {
                if(!dfs(nei)) {
                    return false;
                }
            }

            state[currNode] = 2;
        }
        else if(state[currNode] == 1) {   // cycle detected
            return false;
        }

        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Build the graph
        map = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] prereq: prerequisites) {
            map.get(prereq[0]).add(prereq[1]);
        }

        state = new int[numCourses];

        // 2. Traverse each node to check whether none of the nodes have circular dependencies
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(i)) {
                return false;
            }
        }

        return true;
    }
}