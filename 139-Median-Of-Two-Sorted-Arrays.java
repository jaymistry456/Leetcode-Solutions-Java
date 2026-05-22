// https://leetcode.com/problems/median-of-two-sorted-arrays/


// given: 2 sorted arrays
// required: find the median

// constraints
// m, n in [0, 1000]
// each value in [-10^6, 10^6]

// tc: O(mlogm + nlogn), sc: O(m + n)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> mergedList = new ArrayList<>();
        for(int num: nums1) {
            mergedList.add(num);
        }
        for(int num: nums2) {
            mergedList.add(num);
        }

        Collections.sort(mergedList);
        
        int size = mergedList.size();
        if(size % 2 == 1) {
            return mergedList.get(size / 2);
        }
        return (mergedList.get((size - 1) / 2) + mergedList.get(size / 2)) /  2.0;
    }
}




// Two-pointers
// tc: O(m + n), sc: O(1)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;

        int i = 0;
        int j = 0;
        int k = 0;
        int prev = -1;
        int curr = -1;
        while(k <= total / 2) {
            prev = curr;
            if(i < m && j < n) {
                if(nums1[i] <= nums2[j]) {
                    curr = nums1[i];
                    i++;
                }
                else {
                    curr = nums2[j];
                    j++;
                }
            }
            else if(i < m) {
                curr = nums1[i];
                i++;
            }
            else {
                curr = nums2[j];
                j++;
            }
            k++;
        }

        if(total % 2 == 1) {
            return curr;
        }
        return (prev + curr) / 2.0;
    }
}




// Binary Search
// tc: O(log(min(m, n))), sc: O(1)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;

        if(m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = m;
        while(start <= end) {
            int i = start + (end - start) / 2;
            int j = total / 2 - i;

            int l1 = i - 1 >= 0 ? nums1[i - 1] : Integer.MIN_VALUE;
            int r1 = i < m ? nums1[i] : Integer.MAX_VALUE;

            int l2 = j - 1 >= 0 ? nums2[j - 1] : Integer.MIN_VALUE;
            int r2 = j < n ? nums2[j] : Integer.MAX_VALUE;

            if(l1 <= r2 && l2 <= r1) {
                if(total % 2 == 1) {
                    return Math.min(r1, r2);
                }
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
            else if(l1 > r2) {
                end = i - 1;
            }
            else {
                start = i + 1;
            }
        }

        return -1;
    }
}