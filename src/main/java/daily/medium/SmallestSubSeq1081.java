package daily.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmallestSubSeq1081 {
    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(smallestSubsequence(s));
    }


//    greedy; time: O(n), space: O(1)
    public static String smallestSubsequence(String s) {
        int n = s.length();
        int[] freq = new int[26];
        boolean[] visited = new boolean[26];
//        take the frequency
        for (int i = 0; i < n; i++)
            freq[s.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
//            if not visited
            if (!visited[ch - 'a']) {
                while (!sb.isEmpty() && sb.charAt(sb.length() - 1) > ch) {
                    if (freq[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else
                        break;
                }
                visited[ch - 'a'] = true;
                sb.append(ch);
            }
//            decrement the count
            freq[ch - 'a']--;
        }

        return sb.toString();
    }
}

/*
Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
Example 1:
Input: s = "bcabc"
Output: "abc"
Example 2:
Input: s = "cbacdcbc"
Output: "acdb"
 
Constraints:
1 <= s.length <= 1000
s consists of lowercase English letters.
 */

/*
First, consider a simpler problem: given a string s, how can we remove one character to make the resulting string
lexicographically smallest?
The answer is to find the smallest index i such that s[i]>s[i+1] and remove the character s[i].
 */