// https://leetcode.com/problems/course-schedule-ii/


// given: an integer numCourses representing the number of courses and a 2D array prerequisites where each [x, y] mean x is dependent on y (y is a prerequisite for taking x)
// required: return the ordering of courses if it is possible, else return an empty array

// constraints
// numCourses in [1, 2000]
// length of prerequisites in [0, numCourses * (numCourses - 1)]
// all pairs are distinct

// tc: O(v + e), sc: O(v + e)
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();   // node -> List of prereqs
    int[] visited;   // 0 -> unvisited, 1 -> visiting, 2 -> visited
    List<Integer> result = new ArrayList<>();

    public boolean dfs(int node) {
        if(visited[node] == 0) {
            visited[node] = 1;
            for(int nei: graph.get(node)) {
                if(!dfs(nei)) {
                    return false;
                }
            }
            result.add(node);
            visited[node] = 2;
        }
        else if(visited[node] == 1) {
            return false;
        }
        return true;
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for(int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge: prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }

        visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(i)) {
                return new int[]{};
            }
        }

        int[] resultArray = new int[numCourses];
        for(int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}