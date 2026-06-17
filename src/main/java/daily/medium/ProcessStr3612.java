package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProcessStr3612 {
    public static void main(String[] args) {
        String s = "a#b%*";
        System.out.println(processStr(s));
    }

//    simulation; time: O(2^n), space: O(2^n)   [worst case doubling, scan the whole and copy the whole]
    public static String processStr(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (!res.isEmpty())
                    res.deleteCharAt(res.length() - 1);
            }
            else if (c == '#')
                res.append(res);
            else if (c == '%')
                res.reverse();
            else
                res.append(c);
        }

        return res.toString();
    }

//    snails pace and unnecessary :p
    public static String processStrX(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != '#' && c != '%' && c != '*') {
                stack.push(c); continue;
            }
            if (stack.isEmpty()) continue;
            if (c == '*')
                stack.pop();
            else {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty())
                    sb.append(stack.pop());
                if (c == '%') {
                    for (int i = 0; i < sb.length(); i++)
                        stack.push(sb.charAt(i));
                } else {
                    sb.append(sb);
                    for (int i = sb.length() - 1; i >= 0; i--)
                        stack.push(sb.charAt(i));
                }
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty())
            res.append(stack.pop());

        return res.reverse().toString();
    }
}

/*
You are given a string s consisting of lowercase English letters and the special characters: *, #, and %.
Build a new string result by processing s according to the following rules from left to right:
If the letter is a lowercase English letter append it to result.
A '*' removes the last character from result, if it exists.
A '#' duplicates the current result and appends it to itself.
A '%' reverses the current result.
Return the final string result after processing all characters in s.
Example 1:
Input: s = "a#b%*"
Output: "ba"
Explanation:
i	s[i]	Operation	Current result
0	'a'	Append 'a'	"a"
1	'#'	Duplicate result	"aa"
2	'b'	Append 'b'	"aab"
3	'%'	Reverse result	"baa"
4	'*'	Remove the last character	"ba"
Thus, the final result is "ba".
Example 2:
Input: s = "z*#"
Output: ""
Explanation:
i	s[i]	Operation	Current result
0	'z'	Append 'z'	"z"
1	'*'	Remove the last character	""
2	'#'	Duplicate the string	""
Thus, the final result is "".

Constraints:
1 <= s.length <= 20
s consists of only lowercase English letters and special characters *, #, and %.
 */