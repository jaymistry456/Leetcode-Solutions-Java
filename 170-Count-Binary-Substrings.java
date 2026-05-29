// https://leetcode.com/problems/count-binary-substrings/


// given: a string s
// required: no. of non-empty substrings containing equal no. of 0s and 1s where there are grouped together

// constraints
// length of s in [1, 100k]
// each charater is either 0 or 1

// tc: O(n^2), sc: O(1)
class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int result = 0;

        for(int i = 0; i < n; i++) {
            int j = i;
            char currChar = s.charAt(j);
            int countFirst = 0;
            while(j < n && s.charAt(j) == currChar) {
                countFirst++;
                j++;
            }

            if(j < n) {
                int countSecond = 0;
                currChar = s.charAt(j);
                while(j < n && s.charAt(j) == currChar) {
                    countSecond++;
                    j++;
                    
                    if(countFirst == countSecond) {
                        result++;
                        break;
                    }
                }
                
            }
        }

        return result;
    }
}





// tc: O(n), sc: O(1)
class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int result = 0;
        int prev = 0;
        int curr = 0;
        char currChar;

        int i = 0;
        while(i < n) {
            currChar = s.charAt(i);
            while(i < n && s.charAt(i) == currChar) {
                curr++;
                i++;
            }
            result += Math.min(prev, curr);
            prev = curr;
            curr = 0;
        }

        return result;
    }
}