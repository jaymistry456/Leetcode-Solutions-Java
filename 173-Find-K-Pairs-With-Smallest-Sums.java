// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/


// given: 2 integer arrays and an integer k
// required: return k pairs by selecting one element from the first array and one element from the second array such that the sums are from lowest to highest (non-decreasing order)

// constraints
// length of both arrays in [1, 100k]
// each value in [-10^9, 10^9]
// both arrays are sorted in non-decreasing order
// k in [1, 10k]
// k <= n1 * n2 where n1 and n2 are the length of the 2 arrays

// tc: O(m*n * log(m*n)), sc: O(m*n)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));   // [sum, n1, n2] sorted by sum in asc order

        for(int n1: nums1) {
            for(int n2: nums2) {
                pq.offer(new int[]{n1 + n2, n1, n2});
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            int[] item = pq.poll();
            result.add(List.of(item[1], item[2]));
        }

        return result;
    }
}





// tc: O(m*n * logk), sc: O(k)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));   // [sum, n1, n2] sorted by sum in desc order

        for(int n1: nums1) {
            for(int n2: nums2) {
                pq.offer(new int[]{n1 + n2, n1, n2});
                if(pq.size() > k) {
                    pq.poll();
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            int[] item = pq.poll();
            result.add(List.of(item[1], item[2]));
        }

        return result;
    }
}




// tc: O(klogk), sc: O(k)
record Pair(int i, int j) {}

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));   // [sum, i, j] sorted by sum in asc order

        pq.offer(new int[]{nums1[0] + nums2[0], 0, 0});

        Set<Pair> set = new HashSet<>();
        set.add(new Pair(0, 0));

        List<List<Integer>> result = new ArrayList<>();
        while(k > 0) {
            int[] item = pq.poll();
            int i = item[1];
            int j = item[2];
            result.add(List.of(nums1[i], nums2[j]));

            if(i + 1 < nums1.length && !set.contains(new Pair(i + 1, j))) {
                pq.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                set.add(new Pair(i + 1, j));
            }

            if(j + 1 < nums2.length && !set.contains(new Pair(i, j + 1))) {
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                set.add(new Pair(i, j + 1));
            }

            k--;
        }

        return result;
    }
}