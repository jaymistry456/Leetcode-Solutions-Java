// https://leetcode.com/problems/find-k-closest-elements/


// given: an array of integers, an integer k and an integer x
// required: return the k closest integers to x in the array in sorted order, if the difference is equal, return the smaller number

// constraints
// k in [1, length of the array]
// length of the array in [1, 10k]
// array is sorted
// each value, x in [-10k, 10k]

// tc: O(logn + klogk), sc: O(1)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = -1;
        int start = 0;
        int end = arr.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] <= x) {
                idx = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        int left = idx;
        int right = idx + 1;
        while(result.size() < k) {
            if(left >= 0 && right < arr.length) {

                if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    result.add(arr[left]);
                    left--;
                }
                else {
                    result.add(arr[right]);
                    right++;
                }
            } else if (left >= 0) {
                result.add(arr[left]);
                left--;
            } else if (right < arr.length) {
                result.add(arr[right]);
                right++;
            }
        }
        Collections.sort(result);

        return result;
    }
}




// tc: O(logn + klogk), sc: O(1)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = -1;
        int start = 0;
        int end = arr.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] <= x) {
                idx = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        int left = idx;
        int right = idx + 1;
        while(right - left - 1 < k) {
            if(left >= 0 && right < arr.length) {

                if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    left--;
                }
                else {
                    right++;
                }
            } else if (left >= 0) {
                left--;
            } else if (right < arr.length) {
                right++;
            }
        }
        
        for(int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}