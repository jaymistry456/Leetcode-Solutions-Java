// https://leetcode.com/problems/the-kth-factor-of-n/


// given: an integer n and an integer k
// required: return the kth factor of n in ascending order or -1 if n has < k factors

// constraints
// k, n in [1, 1000]

// tc: O(n), sc: O(1)
class Solution {
    public int kthFactor(int n, int k) {
        int counter = 0;

        for(int i = 1; i <= n; i++) {
            if(n % i == 0) {
                counter++;
                if(counter == k) return i;
            }
        }

        return -1;
    }
}



// tc: O(sqrt(n)), sc: O(k)
class Solution {
    public int kthFactor(int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );

        for(int i = 1; i <= (int) Math.sqrt(n); i++) {
            if(n % i == 0) {
                pq.offer(i);
                if(pq.size() > k) pq.poll();

                int j = n / i;
                if(j != i && n % j == 0) {
                    pq.offer(j);
                    if(pq.size() > k) pq.poll();
                }
            }
        }

        if(pq.size() < k) return -1;

        return pq.poll();
    }
}




// tc: O(sqrt(n)), sc: O(1)
class Solution {
    public int kthFactor(int n, int k) {
        int counter = 0;

        for(int i = 1; i <= (int) Math.sqrt(n); i++) {
            if(n % i == 0) {
                counter++;
                if(counter == k) return i;
            }
        }

        for(int i = (int) Math.sqrt(n); i >= 1; i--) {
            int j = n / i;
            if(j != i && n % j == 0) {
                counter++;
                if(counter == k) return j;
            }
        }

        return -1;
    }
}