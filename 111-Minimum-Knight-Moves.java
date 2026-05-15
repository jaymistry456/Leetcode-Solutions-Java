// https://www.hellointerview.com/learn/code/breadth-first-search/minimum-knight-moves


// given: two integers x and y representing position on a chess
// required: return the minimum number of moves required to reach (x, y) from (0, 0) for a Knight

// constraints
// chess board size is infinite

// tc: O(x*y), sc: O(x*y)
record Position (int row, int col) {}

public class Solution {
    public Integer minimumKnightMoves(Integer x, Integer y) {
        if(x == 0 && y == 0) {
            return 0;
        }

        int[][] directions = new int[][]{{1, 2}, {2, 1}, {-1, -2}, {-2, -1}, {1, -2}, {-1, 2}, {2, -1}, {-2, 1}};

        Deque<int[]> queue = new ArrayDeque<>();   // (i, j)
        queue.offer(new int[]{0, 0});
        Set<Position> set = new HashSet<>();
        set.add(new Position(0, 0));
        int result = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            result++;

            for(int i = 0; i < size; i++) {
                int[] item = queue.poll();
                int r = item[0];
                int c = item[1];

                for(int[] dir: directions) {
                    int neiR = r + dir[0];
                    int neiC = c + dir[1];

                    if(neiR == x && neiC == y) {
                        return result;
                    }

                    Position neiPosition = new Position(neiR, neiC);
                    if(neiR < -2 || neiR > x + 2 || neiC < -2 || neiC > y + 2 || set.contains(newPosition)) {
                        continue;
                    }


                    queue.offer(new int[]{neiR, neiC});
                    set.add(new Position(neiR, neiC));
                }
            }

        }

        return -1;
    }
}