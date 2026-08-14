package daily.easy;

import java.util.HashMap;
import java.util.Map;

public class MaxLength3090 {
    public static void main(String[] args) {
        String s = "bcbbbcba";
        System.out.println(maximumLengthSubstring(s));
    }

//    sliding window; time: O(n), space: O(1)
    public static int maximumLengthSubstring(String s) {
        int start = 0, maxLength = Integer.MIN_VALUE, n = s.length();
        int[] count = new int[26];
        for (int end = 0; end < n; end++) {
            int c = s.charAt(end) - 'a';
            count[c]++;
            while (count[c] > 2) {
                int c2 = s.charAt(start) - 'a';
                count[c2]--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

//    sliding window: hashmap; time: O(n), space: O(n)
    public static int maximumLengthSubstring1(String s) {
        int start = 0, maxLength = Integer.MIN_VALUE, n = s.length();
        Map<Character, Integer> freq = new HashMap<>();
        for (int end = 0; end < n; end++) {
            char c = s.charAt(end);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            while (freq.get(c) > 2) {
                freq.put(s.charAt(start), freq.get(s.charAt(start)) - 1);
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}

/*
Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.
Example 1:
Input: s = "bcbbbcba"
Output: 4
Explanation:
The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".
Example 2:
Input: s = "aaaa"
Output: 2
Explanation:
The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".

Constraints:
2 <= s.length <= 100
s consists only of lowercase English letters.
 */
