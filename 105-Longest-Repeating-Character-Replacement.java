// https://leetcode.com/problems/longest-repeating-character-replacement/


// given: a string s and an integer k
// required: return the length of the longest substring containing the same letter after performing atmost k operations to convert any character to the given character

// constraints
// length of s in [1, 100k]
// s contains only uppercase letters
// k in [0, s.length]

// tc: O(n^2), sc: O(1)
class Solution {
    public int characterReplacement(String s, int k) {
        int result = 0;
        
        for(int i = 0; i < s.length(); i++) {
            int[] freq = new int[26];
            int maxFreq = 0;

            for(int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                freq[ch - 'A']++;
                maxFreq = Math.max(maxFreq, freq[ch - 'A']);

                if(j - i + 1 - maxFreq > k) {
                    break;
                }
                result = Math.max(result, j - i + 1);
            }
        }

        return result;
    }
}




// tc: O(n), sc: O(1)
class Solution {
    public int getMaxFreq(int[] freq) {
        int maxFreq = 0;
        for(int currFreq: freq) {
            maxFreq = Math.max(maxFreq, currFreq);
        }
        
        return maxFreq;
    }

    public int characterReplacement(String s, int k) {
        int result = 0;
        
        int left = 0;
        int right = 0;
        int[] freq = new int[26];

        while(right < s.length()) {
            freq[s.charAt(right) - 'A']++;

            while(right - left + 1 - getMaxFreq(freq) > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }
}