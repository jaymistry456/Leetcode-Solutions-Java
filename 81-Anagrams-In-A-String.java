// https://leetcode.com/problems/find-all-anagrams-in-a-string/


// given: two strings s and p
// required: return an array of all start indices of anagrams of p in s

// constraints
// length of s, p in [1, 30k]
// s and p contain lowercase letters only

// tc: O(m*n), sc: O(1)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int m = s.length();
        int n = p.length();

        Map<Character, Integer> mapP = new HashMap<>();  // char -> freq of char in p
        for(char ch: p.toCharArray()) {
            mapP.put(ch, mapP.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < m - n + 1; i++) {
            Map<Character, Integer> mapS = new HashMap<>();  // char -> freq of char in s
            for(int j = i; j < i + n; j++) {
                mapS.put(s.charAt(j), mapS.getOrDefault(s.charAt(j), 0) + 1);
            }

            if(mapS.equals(mapP)) {
                result.add(i);
            }
        }

        return result;
    }
}




// HashMap
// tc: O(m+n), sc: O(1)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int m = s.length();
        int n = p.length();

        if(m < n) return result;

        Map<Character, Integer> mapP = new HashMap<>();  // char -> freq of char in p
        Map<Character, Integer> mapS = new HashMap<>();  // char -> freq of char in s
        for(int i = 0; i < n; i++) {
            mapP.put(p.charAt(i), mapP.getOrDefault(p.charAt(i), 0) + 1);
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i), 0) + 1);
        }

        if(mapS.equals(mapP)) {
            result.add(0);
        }

        for(int i = n; i < m; i++) {
            char prev = s.charAt(i - n);
            char curr = s.charAt(i);
            mapS.put(prev, mapS.get(prev) - 1);
            if(mapS.get(prev) == 0) {
                mapS.remove(prev);
            }
            mapS.put(curr, mapS.getOrDefault(curr, 0) + 1);

            if(mapS.equals(mapP)) {
                result.add(i - n + 1);
            }
        }

        return result;
    }
}





// Array
// tc: O(m+n), sc: O(1)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int m = s.length();
        int n = p.length();

        if(m < n) return result;

        int[] arrayS = new int[26];
        int[] arrayP = new int[26];
        for(int i = 0; i < n; i++) {
            arrayS[s.charAt(i) - 'a']++;
            arrayP[p.charAt(i) - 'a']++;
        }

        if(Arrays.equals(arrayS, arrayP)) {
            result.add(0);
        }

        for(int i = n; i < m; i++) {
            char prev = s.charAt(i - n);
            char curr = s.charAt(i);
            arrayS[prev - 'a']--;
            arrayS[curr - 'a']++;

            if(Arrays.equals(arrayS, arrayP)) {
                result.add(i - n + 1);
            }
        }

        return result;
    }
}