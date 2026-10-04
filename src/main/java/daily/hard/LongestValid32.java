package daily.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValid32 {
    public static void main(String[] args) {
        String s = ")()())";
        System.out.println(longestValidParentheses(s));
    }


//    without stack; time: O(n), space: O(n) [fastest]
//    two passes, since l to r will not capture "(()", and vice versa ())
    public static int longestValidParentheses(String s) {
        int n = s.length(), maxLength = 0;
        char[] arr = s.toCharArray();
        int left = 0, right = 0;
//        pass 1: left to right
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(')
                left++;
            else
                right++;
            if (left == right)
                maxLength = Math.max(maxLength, right * 2);
            else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;

//        pass 2: right to left
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == '(')
                left++;
            else
                right++;
            if (left == right)
                maxLength = Math.max(maxLength, left * 2);
            else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLength;
    }

//    dp; time: O(n), space: O(n) [faster]
    public static int longestValidParentheses1(String s) {
        int n = s.length(), maxLength = 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                else if ((i - dp[i - 1] > 0) && chars[i - dp[i - 1] - 1] == '(') {
                    int prevValid = (i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] : 0;
                    dp[i] = prevValid + dp[i - 1] + 2;
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

//    simulation using stack (1 pass); time: O(n), space: O(n)
    public static int longestValidParentheses2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length(), maxLength = 0;
//        base index for valid substring length calculation
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty())
//                    current index serves as the new boundary
                    stack.push(i);
                else
//                    length of the current valid substring
                    maxLength = Math.max(maxLength, i - stack.peek());
            }
        }

        return maxLength;
    }
}

/*
Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.
Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:
Input: s = ""
Output: 0

Constraints:
0 <= s.length <= 3 * 10^4
s[i] is '(', or ')'.
 */