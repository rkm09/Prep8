package daily.hard;

public class MaxPalindrome2472 {
    public static void main(String[] args) {
        String s = "abaccdbbd";
        System.out.println(maxPalindromes(s, 3));
    }

//    dp; time: O(n^2), space: O(n^2)
    public static int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len <= n; left++) {
                int right = left + len - 1;
                isPalindrome[left][right] = (s.charAt(left) == s.charAt(right)) &&
                        (len <= 2 || isPalindrome[left + 1][right - 1]);
            }
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int j = 0; j + k <= i; j++) {
                if (isPalindrome[j][i - 1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        return dp[n];
    }
}


/*
You are given a string s and a positive integer k.
Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
The length of each substring is at least k.
Each substring is a palindrome.
Return the maximum number of substrings in an optimal selection.
A substring is a contiguous sequence of characters within a string.
Example 1:
Input: s = "abaccdbbd", k = 3
Output: 2
Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
It can be shown that we cannot find a selection with more than two valid substrings.
Example 2:
Input: s = "adbcda", k = 2
Output: 0
Explanation: There is no palindrome substring of length at least 2 in the string.

Constraints:
1 <= k <= s.length <= 2000
s consists of lowercase English letters.
 */

/*
Let dp[i] denote the maximum number of non-overlapping palindromic substrings of length at least k
that can be selected from the first i characters s[0…i−1].
For the first i characters, there are two choices:
- Do not select a palindromic substring ending at i−1. In this case, dp[i]=dp[i−1].
- Enumerate the starting position j of a candidate palindromic substring ending at i−1.
  If i−j≥k and s[j…i−1] is a palindrome, then this substring can be appended after the optimal partition of the
  first j characters, updating dp[i]=max(dp[i],dp[j]+1).
 */