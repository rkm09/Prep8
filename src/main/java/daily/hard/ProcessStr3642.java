package daily.hard;

public class ProcessStr3642 {
    public static void main(String[] args) {
        String s = "a#b%*";
        System.out.println(processStr(s, 1));
    }

//    simulation; time: O(n), space: O(1)
    public static char processStr(String s, long k) {
        long len = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '*':
                    if (len > 0) len--;
                    break;
                case '#':
                    len *= 2; break;
                case '%':
                    break;
                default:
                    len++;
            }
        }

        if (k + 1 > len)
            return '.';

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case '*':
                    len++;
                    break;
                case '#':
                    if (k + 1 > (len + 1) / 2)
                        k -= len / 2;
                    len = (len + 1) / 2;
                    break;
                case '%':
                    k = len - k - 1;
                    break;
                default:
                    if (k + 1 == len)
                        return c;
                    len--;
            }
        }

        return '.';
    }
}

/*
You are given a string s consisting of lowercase English letters and the special characters: '*', '#', and '%'.
You are also given an integer k.
Build a new string result by processing s according to the following rules from left to right:
If the letter is a lowercase English letter append it to result.
A '*' removes the last character from result, if it exists.
A '#' duplicates the current result and appends it to itself.
A '%' reverses the current result.
Return the kth character of the final string result. If k is out of the bounds of result, return '.'.
Example 1:
Input: s = "a#b%*", k = 1
Output: "a"
Explanation:
i	s[i]	Operation	Current result
0	'a'	Append 'a'	"a"
1	'#'	Duplicate result	"aa"
2	'b'	Append 'b'	"aab"
3	'%'	Reverse result	"baa"
4	'*'	Remove the last character	"ba"
The final result is "ba". The character at index k = 1 is 'a'.
Example 2:
Input: s = "cd%#*#", k = 3
Output: "d"
Explanation:
i	s[i]	Operation	Current result
0	'c'	Append 'c'	"c"
1	'd'	Append 'd'	"cd"
2	'%'	Reverse result	"dc"
3	'#'	Duplicate result	"dcdc"
4	'*'	Remove the last character	"dcd"
5	'#'	Duplicate result	"dcddcd"
The final result is "dcddcd". The character at index k = 3 is 'd'.
Example 3:
Input: s = "z*#", k = 0
Output: "."
Explanation:
i	s[i]	Operation	Current result
0	'z'	Append 'z'	"z"
1	'*'	Remove the last character	""
2	'#'	Duplicate the string	""
The final result is "". Since index k = 0 is out of bounds, the output is '.'.

Constraints:
1 <= s.length <= 10^5
s consists of only lowercase English letters and special characters '*', '#', and '%'.
0 <= k <= 10^15
The length of result after processing s will not exceed 10^15.
 */

/*
In this version, the length of the string s can be up to 10^5, and the length of result can reach 10^15.
We are also asked to find the k-th character of result.
Therefore, it is impossible to explicitly construct result and then query its k-th character.
Since directly simulating the construction of result is infeasible, we need a different perspective.

Instead of trying to determine which character ends up at position k in the final string,
we can work backward and determine which position in an earlier state of result corresponds to the final position k.
The problem states that if k is outside the valid index range of result, we should return '.'. Since the final length of result is not known in advance,
we first simulate only its length, denoted by len.
If k+1>len after processing the entire string, then the requested position does not exist, and we can immediately return '.'.
Otherwise, we traverse the string s from right to left and reverse each operation. During this process, we maintain the invariant k+1≤len.
*/