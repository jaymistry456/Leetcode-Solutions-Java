// https://leetcode.com/problems/permutation-in-string/


// given: two strings s1 and s2
// required: check whether s2 contains a permutation of s1

// constraints
// length of both s1, s2 in [1, 10k]
// both strings contains lowercase letters only

// tc: O(m*n), sc: O(1)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if(n < m) return false;

        int[] freq1 = new int[26];
        for(char ch: s1.toCharArray()) {
            freq1[ch - 'a']++;
        }

        for(int i = 0; i < n - m + 1; i++) {
            int[] freq2 = new int[26];

            for(int j = i; j < i + m; j++) {
                char ch = s2.charAt(j);
                freq2[ch - 'a']++;
            }

            if(Arrays.equals(freq1, freq2)) return true;
        }

        return false;
    }
}




// tc: O(m+n), sc: O(1)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if(n < m) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for(int i = 0; i < m; i++) {
            freq1[s1.charAt(i) - 'a']++;
            freq2[s2.charAt(i) - 'a']++;
        }

        if(Arrays.equals(freq1, freq2)) return true;

        for(int i = m; i < n; i++) {
            char prev = s2.charAt(i - m);
            char curr = s2.charAt(i);
            freq2[prev - 'a']--;
            freq2[curr - 'a']++;

            if(Arrays.equals(freq1, freq2)) return true;
        }

        return false;
    }
}