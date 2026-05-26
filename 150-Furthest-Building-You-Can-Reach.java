// https://leetcode.com/problems/furthest-building-you-can-reach/


// given: an array of heights, an integer bricks and an interger ladders
// required: the furthest building that can be reached using bricks and ladders incase the next building height is greater than the current building height

// a simple approach is to use recursion to simulate this
// at each point if the next building height is greater than the current building, we can either use bricks (if there is enough of them) or a ladder and branch out to different branches
// tc: O(2^n), sc: O(n)
class Solution {
    public int dfs(int[] heights, int i, int bricks, int ladders) {
        if(i == heights.length - 1) {
            return i;
        }

        int diff = heights[i + 1] - heights[i];

        if(diff > 0) {
            if(bricks >= diff && ladders > 0) {
                return Math.max(
                    dfs(heights, i + 1, bricks - diff, ladders), 
                    dfs(heights, i + 1, bricks, ladders - 1)
                );
            } else if(bricks >= diff) {
                return dfs(heights, i + 1, bricks - diff, ladders);
            } else if(ladders > 0) {
                return dfs(heights, i + 1, bricks, ladders - 1);
            }

            return i;
        }

        return dfs(heights, i + 1, bricks, ladders);
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return dfs(heights, 0, bricks, ladders);
    }
}




// ladders are more important than bricks because a ladder can cover any height
// can we keep a priority queue (min heap) of the height differences that were 
// covered by previous use of the ladders and if any of the previous uses of ladders 
// were smaller than the current jump required, we swap out the ladder with bricks 
// (if they are enough of them) for the previous jump and use ladder for the current jump
// tc: O(nlogl), sc: O(l)
class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a, b)
        );   // Storing min-heap of height differences for using ladders

        for(int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];

            if(diff > 0) {
                if(ladders > 0) {
                    pq.offer(diff);
                    ladders--;
                }
                else {
                    if(!pq.isEmpty()) {
                        if(pq.peek() < diff) {
                            bricks -= pq.poll();
                            pq.offer(diff);
                        }
                        else {
                            bricks -= diff;
                        }
                    }
                    else {
                        bricks -= diff;
                    }

                    if(bricks < 0) {
                        return i;
                    }
                }
            }
        }

        return heights.length - 1;
    }
}