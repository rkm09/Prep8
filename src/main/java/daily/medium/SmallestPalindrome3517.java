package daily.medium;

public class SmallestPalindrome3517 {
    public static void main(String[] args) {
        String s = "babab";
        System.out.println(smallestPalindrome(s));
    }

//  counting sort; time: O(n), space: O(n)
    public static String smallestPalindrome(String s) {
        if (s.length() == 1) return s;
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        String mid = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                char curr = (char)('a' + i);
                if (freq[i] % 2 != 0)
                    mid = curr + "";
                int count = freq[i] / 2 ;
                while (count-- > 0)
                    sb.append(curr);
            }
        }
        String res = new String(sb);
        res += mid;
        res += sb.reverse();

        return res;
    }
}

/*
You are given a palindromic string s.
Return the lexicographically smallest palindromic permutation of s.
Example 1:
Input: s = "z"
Output: "z"
Explanation:
A string of only one character is already the lexicographically smallest palindrome.
Example 2:
Input: s = "babab"
Output: "abbba"
Explanation:
Rearranging "babab" → "abbba" gives the smallest lexicographic palindrome.
Example 3:
Input: s = "daccad"
Output: "acddca"
Explanation:
Rearranging "daccad" → "acddca" gives the smallest lexicographic palindrome.

Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
s is guaranteed to be palindromic.
 */
