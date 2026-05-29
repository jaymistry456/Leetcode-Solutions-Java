// https://leetcode.com/problems/minimum-genetic-mutation/


// given: startGene, endGene and bank of genetic mutations
// required: number of steps required to go from startGene to endGene if it is possible, else -1

// constraints
// length of gene bank in [0, 10]
// each gene length in == 8
// all characters are uppercase letters only


// DFS
// // tc: O(n^2 * m), sc: O(n)
class Solution {
    Set<String> visited = new HashSet<>();
    int result = Integer.MAX_VALUE;

    public boolean isDiffOne(String first, String second) {
        int diff = 0;
        for(int i = 0; i < first.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }

        return diff == 1;
    }

    public void dfs(String currGene, String endGene, String[] bank, int currSteps) {
        if(currGene.equals(endGene)) {
            result = Math.min(result, currSteps);
            return;
        }

        if(currSteps >= result) {
            return;
        }

        for(String nextGene: bank) {
            if(!visited.contains(nextGene) && isDiffOne(currGene, nextGene)) {
                visited.add(nextGene);
                dfs(nextGene, endGene, bank, currSteps + 1);
                visited.remove(nextGene);
            }
        }
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        dfs(startGene, endGene, bank, 0);

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}




// BFS
// tc: O(n^2 * m), sc: O(n^2 * m)
class Solution {
    public boolean isDiffOne(String first, String second) {
        int diff = 0;
        for(int i = 0; i < first.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }

        return diff == 1;
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(startGene);
        visited.add(startGene);
        int currSteps = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                String currGene = queue.poll();

                if(currGene.equals(endGene)) {
                    return currSteps;
                }

                for(String nextGene: bank) {
                    if(!visited.contains(nextGene) && isDiffOne(currGene, nextGene)) {
                        visited.add(nextGene);
                        queue.offer(nextGene);
                    }
                }
            }

            currSteps++;
        }

        return -1;
    }
}





// BFS
// // tc: O(n * m^2), sc: O(n * m^2)
class Solution {
    public boolean isDiffOne(String first, String second) {
        int diff = 0;
        for(int i = 0; i < first.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }

        return diff == 1;
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Map<String, List<String>> map = new HashMap<>();   // gene pattern -> all genes conforming to this pattern
        for(String gene: bank) {
            for(int i = 0; i < gene.length(); i++) {
                String pattern = gene.substring(0, i) + '#' + gene.substring(i + 1, gene.length());
                map.putIfAbsent(pattern, new ArrayList<>());
                map.get(pattern).add(gene);
            }
        }

        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(startGene);
        visited.add(startGene);
        int currSteps = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                String currGene = queue.poll();

                if(currGene.equals(endGene)) {
                    return currSteps;
                }

                for(int j = 0; j < currGene.length(); j++) {
                    String pattern = currGene.substring(0, j) + '#' + currGene.substring(j + 1, currGene.length());
                
                    for(String nextGene: map.getOrDefault(pattern, new ArrayList<>())) {
                        if(!visited.contains(nextGene)) {
                            visited.add(nextGene);
                            queue.offer(nextGene);
                        }
                    }
                }
            }

            currSteps++;
        }

        return -1;
    }
}