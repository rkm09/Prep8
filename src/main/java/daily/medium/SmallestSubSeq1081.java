package daily.medium;

import java.util.HashSet;
import java.util.Set;

public class SmallestSubSeq1081 {
    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(smallestSubsequence(s));
    }

//    sliding window;
    public static String smallestSubsequence(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++)
            set.add(s.charAt(i));

        
        return "";
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
