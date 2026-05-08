// https://leetcode.com/problems/group-anagrams/


// given: an array of strings
// required: groups the anagrams together

// constraints
// length of the array in [1, 10k]
// each string's length in [0, 100]
// all strings contain lowercase letters only

// tc: O(n*k), sc: O(n*k)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();   // freqPattern separated by # -> strs matching the pattern

        for(int i = 0; i < strs.length; i++) {
            int[] freq = new int[26];
            for(char ch: strs[i].toCharArray()) {
                freq[ch - 'a']++;
            }

            StringBuilder freqPattern = new StringBuilder("");
            for(int currFreq: freq) {
                freqPattern.append(currFreq);
                freqPattern.append("#");
            }
            String pattern = freqPattern.toString();

            if(!map.containsKey(pattern)) {
                map.put(pattern, new ArrayList<>());
            }
            map.get(pattern).add(strs[i]);
        }

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }
}