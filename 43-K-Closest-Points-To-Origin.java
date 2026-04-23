// https://leetcode.com/problems/k-closest-points-to-origin/


// given: a 2D array of points and an integer k
// required: return the k closest points to the origin

// constraints
// length of the array in [1, 10k]
// each value in [-10k, 10k]

// tc: O(nlogn), sc: O(n)
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        List<List<Integer>> arrayList = new ArrayList<>();
        for(int[] point: points) {
            int distance = point[0] * point[0] + point[1] * point[1];
            arrayList.add(List.of(distance, point[0], point[1]));
        }

        Collections.sort(arrayList, (a, b) -> Integer.compare(a.get(0), b.get(0)));

        int[][] result = new int[k][2];
        for(int i = 0; i < k; i++) {
            result[i][0] = arrayList.get(i).get(1);
            result[i][1] = arrayList.get(i).get(2);
        }

        return result;
    }
}




// tc: O(nlogk), sc: O(k)
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[0], a[0])
        );   // [distance, i, j] sorted by distance in descending order for popping the max element

        for(int[] point: points) {
            int x = point[0];
            int y = point[1];
            int distance = x * x + y * y;
            
            pq.offer(new int[]{distance, x, y});
            if(pq.size() > k) {
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
        while(!pq.isEmpty()) {
            int[] item = pq.poll();
            result[k - 1] = new int[]{item[1], item[2]};
            k--;
        }

        return result;
    }
}