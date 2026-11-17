package daily.hard;

import java.util.Arrays;

public class MinChange2911 {
    static void main() {
        String s = "abcac";
        System.out.println(minimumChanges(s,2));
    }

    public static int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // Precompute cost[i][j]: min changes to make s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                cost[i][j] = getSemiPalindromeCost(chars, i, j);
            }
        }

        // dp[c][i] = min changes to partition s[0...i] into c parts
        int[][] dp = new int[k + 1][n];
        for (int[] row : dp) Arrays.fill(row, 1000000);

        // Base case: 1 part from 0 to i
        for (int i = 0; i < n; i++) {
            dp[1][i] = cost[0][i];
        }

        for (int c = 2; c <= k; c++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    dp[c][i] = Math.min(dp[c][i], dp[c - 1][j] + cost[j + 1][i]);
                }
            }
        }

        return dp[k][n - 1];
    }
    private static int getSemiPalindromeCost(char[] s, int start, int end) {
        int len = end - start + 1;
        if (len < 2) return 1000000; // Length < 2 cannot be a semi-palindrome (no proper divisor)

        int minCost = 1000000;

        // Find all proper divisors d of len
        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int currentCost = 0;
                // For each group determined by divisor d
                for (int offset = 0; offset < d; offset++) {
                    // Collect characters in this group and find changes to make it a palindrome
                    int left = start + offset;
                    int right = start + ((len - 1) / d) * d + offset; // last element in group
                    // group indices are start + offset, start + offset + d, start + offset + 2d, ...
                    // group starts at start + offset, steps by d up to end.
                    currentCost += getGroupPalindromeCost(s, start + offset, end, d);
                }
                minCost = Math.min(minCost, currentCost);
            }
        }

        return minCost;
    }

    // Calculate changes to make a specific group (starting at groupStart, stepping by d, within bounds <= end) a palindrome
    private static int getGroupPalindromeCost(char[] s, int groupStart, int end, int d) {
        // Collect indices for this group
        // A simpler way: loop two pointers from ends of the group
        int changes = 0;
        int l = groupStart;
        int r = groupStart + ((end - groupStart) / d) * d; // approximate last element in group
        // To be precise, let's find all positions in the group:
        // Actually, we can just extract them or use two pointers moving by d.
        // Let's implement two pointers directly on the indices:
        int leftIdx = groupStart;
        int rightIdx = groupStart;
        while (rightIdx + d <= end) {
            rightIdx += d;
        }

        while (leftIdx < rightIdx) {
            if (s[leftIdx] != s[rightIdx]) {
                changes++;
            }
            leftIdx += d;
            rightIdx -= d;
        }

        return changes;
    }
}

/*
Given a string s and an integer k, partition s into k substrings such that the letter changes needed to make each
substring a semi-palindrome are minimized.
Return the minimum number of letter changes required.
A semi-palindrome is a special type of string that can be divided into palindromes based on a repeating pattern. To
check if a string is a semi-palindrome:
Choose a positive divisor d of the string's length. d can range from 1 up to, but not including, the string's length.
For a string of length 1, it does not have a valid divisor as per this definition, since the only divisor is its length,
which is not allowed.
For a given divisor d, divide the string into groups where each group contains characters from the string that follow
a repeating pattern of length d. Specifically, the first group consists of characters at positions 1, 1 + d, 1 + 2d,
and so on; the second group includes characters at positions 2, 2 + d, 2 + 2d, etc.
The string is considered a semi-palindrome if each of these groups forms a palindrome.
Consider the string "abcabc":
The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
For d = 2:
Group 1 (positions 1, 3, 5): "acb"
Group 2 (positions 2, 4, 6): "bac"
Neither group forms a palindrome.
For d = 3:
Group 1 (positions 1, 4): "aa"
Group 2 (positions 2, 5): "bb"
Group 3 (positions 3, 6): "cc"
All groups form palindromes. Therefore, "abcabc" is a semi-palindrome.
Example 1:
Input: s = "abcac", k = 2
Output: 1
Explanation: Divide s into "ab" and "cac". "cac" is already semi-palindrome. Change "ab" to "aa", it becomes semi-palindrome with d = 1.
Example 2:
Input: s = "abcdef", k = 2
Output: 2
Explanation: Divide s into substrings "abc" and "def". Each needs one change to become semi-palindrome.
Example 3:
Input: s = "aabbaa", k = 3
Output: 0
Explanation: Divide s into substrings "aa", "bb" and "aa". All are already semi-palindromes.

Constraints:
2 <= s.length <= 200
1 <= k <= s.length / 2
s contains only lowercase English letters.
 */
