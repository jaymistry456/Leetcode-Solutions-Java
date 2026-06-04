// https://leetcode.com/problems/next-greater-element-i/


// given: two arrays nums1 and nums2
// required: find the next greater element of each no. in nums1 to the right of that no. in nums2

// constraints
// length of nums1, nums2 in [1, 1000]
// each value in [0, 10k]
// each value in nums1 is also present in nums2
// all values in nums1 and nums2 are unique

// tc: O(m*n), sc: O(n)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        Map<Integer, Integer> map = new HashMap<>();   // i -> j
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(nums2[j] == nums1[i]) {
                    map.put(i, j);
                    break;
                }
            }
        }

        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, -1);
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums2[j] > nums2[i]) {
                    nextGreater[i] = nums2[j];
                    break;
                }
            }
        }

        int[] result = new int[m];
        for(int i = 0; i < m; i++) {
            result[i] = nextGreater[map.get(i)];
        }

        return result;
    }
}




// tc: O(m*n), sc: O(1)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] result = new int[m];
        Arrays.fill(result, -1);

        for(int i = 0; i < m; i++) {
            int j = 0;
            while(j < n) {
                if(nums2[j] == nums1[i]) {
                    break;
                }
                j++;
            }

            while(j < n) {
                if(nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
                j++;
            }
        }

        return result;
    }
}



// tc: O(m + n), sc: O(n)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        Map<Integer, Integer> map = new HashMap<>();   // element -> next greater element
        Deque<Integer> stack = new ArrayDeque<>();   // smallest element at the top
        
        for(int j = nums2.length - 1; j >= 0; j--) {
            while(!stack.isEmpty() && stack.peek() <= nums2[j]) {
                stack.pop();
            }

            if(!stack.isEmpty()) {
                map.put(nums2[j], stack.peek());
            } else {
                map.put(nums2[j], -1);
            }

            stack.push(nums2[j]);
        }

        int[] result = new int[m];
        for(int i = 0; i < m; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}