// https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/


// given: 2 strings haystack and needle
// required: index of the first occurence of needle in haystack

// constraints
// length of both strings in [1, 10k]
// both strings contain only lowercase letters

// tc: O(m*n), sc: O(1)
class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        for(int i = 0; i < m - n + 1; i++) {
            boolean flag = true;
            for(int j = 0; j < n; j++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return i;
            }
        }

        return -1;
    }
}




// tc: O(m*n), sc: O(1)
class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        if(m < n) return -1;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for(int i = 0; i < n; i++) {
            freq1[haystack.charAt(i) - 'a']++;
            freq2[needle.charAt(i) - 'a']++;
        }
        if(Arrays.equals(freq1, freq2) && haystack.substring(0, n).equals(needle)) {
            return 0;
        }

        for(int i = n; i < m; i++) {
            char prev = haystack.charAt(i - n);
            char curr = haystack.charAt(i);
            freq1[prev - 'a']--;
            freq1[curr - 'a']++;
            if(Arrays.equals(freq1, freq2) && haystack.substring(i - n + 1, i + 1).equals(needle)) {
                return i - n + 1;
            }
        }
        
        return -1;
    }
}