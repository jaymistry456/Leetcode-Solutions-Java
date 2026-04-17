// https://leetcode.com/problems/first-bad-version/


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

// given: an integer n
// required: return the first bad version of the products

// tc: O(n), sc: O(1)
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        for(int i = 1; i <= n; i++) {
            if(isBadVersion(i)) {
                return i;
            }
        }

        return -1;
    }
}



// tc: O(logn), sc: O(1)
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int result = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(isBadVersion(mid)) {
                result = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }
}