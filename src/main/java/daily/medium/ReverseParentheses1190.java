package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseParentheses1190 {
    public static void main(String[] args) {
        String s = "(u(love)i)";
        System.out.println(reverseParentheses(s));
    }

//    teleportation; time: O(n), space: O(n)
    public static String reverseParentheses(String s) {
        Deque<Integer> openParenthesesIndices = new ArrayDeque<>();
        int n = s.length();
        int[] pair = new int[n];
//        first pass: pair up the parentheses
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                openParenthesesIndices.push(i);
            else if (s.charAt(i) == ')') {
                int j = openParenthesesIndices.pop();
                pair[j] = i;
                pair[i] = j;
            }
        }
        StringBuilder res = new StringBuilder();
//        second pass: build the result string
        for (int currIndex = 0, direction = 1; currIndex < n; currIndex += direction) {
            if (s.charAt(currIndex) == '(' || s.charAt(currIndex) == ')') {
                currIndex = pair[currIndex];
                direction = -direction;
            } else {
                res.append(s.charAt(currIndex));
            }
        }

        return res.toString();
    }

    public static String reverseParentheses1(String s) {
        Deque<Integer> openParenthesesIndices = new ArrayDeque<>();
        int n = s.length();
        StringBuilder res = new StringBuilder();
        char[] str = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (str[i] == '(')
                openParenthesesIndices.push(res.length());
            else if (str[i] == ')') {
                int start = openParenthesesIndices.pop();
                reverseString(res, start, res.length() - 1);
            } else {
                res.append(str[i]);
            }
        }

        return res.toString();
    }

    private static void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }
}

/*
You are given a string s that consists of lower case English letters and brackets.
Reverse the strings in each pair of matching parentheses, starting from the innermost one.
Your result should not contain any brackets.
Example 1:
Input: s = "(abcd)"
Output: "dcba"
Example 2:
Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

Constraints:
1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
 */