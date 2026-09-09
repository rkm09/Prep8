package daily.hard;

public class DistinctSubs115 {
    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        System.out.println(numDistinct(s, t));
    }

    public static int numDistinct(String s, String t) {
        return 0;
    }
}

/*
Given two strings s and t, return the number of distinct subsequences of s which equal t.
The test cases are generated so that the answer fits in a 32-bit signed integer.
Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rab_bit
ra_bbit
rab_bit
Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
ba_g___
ba____g
b____ag
__b__ag
____bag

Constraints:
1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
