package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinInsertions1541 {
    public static void main(String[] args) {
        String s = "(()))";
        System.out.println(minInsertions(s));
    }

//    stack; time: O(n), space: O(n)
    public static int minInsertions(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int openCount = 0, closeCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty())
                    openCount++;
                else
                    stack.pop();
                if (i + 1 < n && s.charAt(i + 1) == ')')
                    i++;
                else
                    closeCount++;
            }
        }

        return openCount + closeCount + stack.size() * 2;
    }
}

/*
Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.
Return the minimum number of insertions needed to make s balanced.
Example 1:
Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
Example 2:
Input: s = "())"
Output: 0
Explanation: The string is already balanced.
Example 3:
Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.

Constraints:
1 <= s.length <= 10^5
s consists of '(' and ')' only.
 */
