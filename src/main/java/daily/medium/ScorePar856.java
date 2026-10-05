package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ScorePar856 {
    public static void main(String[] args) {
        String s = "(()(()))";
        System.out.println(scoreOfParentheses(s));
    }

//    stack; time: O(n), space: O(n)
    public static int scoreOfParentheses(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
//        base frame for the outer score sum
        stack.push(0);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(')
//                start new scope frame
                stack.push(0);
            else {
                int innerScore = stack.pop();
                int outerScore = stack.pop();
//                if innerScore is 0, it's "()" worth 1, else it is "(A)" worth 2
                int scoreOfThisPair = Math.max(2 * innerScore, 1);
//                add to the enclosing scope frame
                stack.push(outerScore + scoreOfThisPair);
            }
        }

        return stack.pop();
    }

//    without stack; time: O(n), space: O(1)
    public static int scoreOfParentheses1(String s) {
        int score = 0, depth = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(')
                depth++;
            else {
                depth--;
//                equivalent to 1 * 2^depth
                if (s.charAt(i - 1) == '(')
                    score += 1 << depth;
            }
        }

        return score;
    }
}

/*
Given a balanced parentheses string s, return the score of the string.
The score of a balanced parentheses string is based on the following rule:
"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
Example 1:
Input: s = "()"
Output: 1
Example 2:
Input: s = "(())"
Output: 2
Example 3:
Input: s = "()()"
Output: 2

Constraints:
2 <= s.length <= 50
s consists of only '(' and ')'.
s is a balanced parentheses string.
 */


/*
String s.  Core () Pairs (0-indexed depth d) Calculation (2d). Total Score
"()"        1 pair at depth 0                   2^0                 1
"(())"      1 pair at depth 1                   2^1                 2
"()()"      2 pairs at depth 0                  2^0 + 2^0       1 + 1 = 2
"((()))"    1 pair at depth 2                   2^2                 4
"(()())"    2 pairs at depth 1                  2^1 + 2^1       2 + 2 = 4
 */