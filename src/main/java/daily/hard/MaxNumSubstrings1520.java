package daily.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxNumSubstrings1520 {
    public static void main(String[] args) {
        String s = "adefaddaccc";
        System.out.println(maxNumOfSubstrings(s));
    }

//    greedy; time: O(n), space: O(n)
    public static List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
//        populate the first and last index arrays
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1)
                first[c] = i;
            last[c] = i;
        }
        List<int[]> intervals = new ArrayList<>();
//        list down valid intervals
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1)
                continue;
            int start = first[i];
            int end = last[i];
            boolean isValid = true;
            for (int j = start; j <= end; j++) {
                int c = s.charAt(j) - 'a';
                if (first[c] < start) {
                    isValid = false;
                    break;
                }
                end = Math.max(end, last[c]);
            }
            if (isValid) {
                intervals.add(new int[]{start, end});
            }
        }
//        sort the intervals list based on the end index, to ensure you maximize on the count of substrings
        intervals.sort(Comparator.comparingInt(a -> a[1]));
//        to ensure intervals do not overlap
        int prevIdx = -1;
        List<String> result = new ArrayList<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (start > prevIdx) {
                result.add(s.substring(start, end + 1));
                prevIdx = end;
            }
        }

        return result;
    }
}

/*
Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:
The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
A substring that contains a certain character c must also contain all occurrences of c.
Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same
number of substrings, return the one with minimum total length.
It can be shown that there exists a unique solution of minimum total length.
Notice that you can return the substrings in any order.
Example 1:
Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else, and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
Example 2:
Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.

Constraints:
1 <= s.length <= 10^5
s contains only lowercase English letters.
 */