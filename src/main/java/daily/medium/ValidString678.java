package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidString678 {
    public static void main(String[] args) {
        String s = "**((";
        System.out.println(checkValidString(s));
    }

//    two pointer greedy; time: O(n), space: O(n)
    public static boolean checkValidString(String s) {
        int openCount = 0, closeCount = 0;
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
//            traverse from the beginning
            if (chars[i] == '(' || chars[i] == '*')
                openCount++;
            else
                openCount--;

//            traverse from the end
            if (chars[n - i - 1] == ')' || chars[n - i - 1] == '*')
                closeCount++;
            else
                closeCount--;

//            if at any point, open or close count goes negative, string is invalid
            if (openCount < 0 || closeCount < 0)
                return false;
        }

        return true;
    }

//     a counter example of why the initial logic was flawed:: String s = "**((" Vs ""((**;
//    we had not tied count of '*' to the exact order. '(' should appear before ')'
    public static boolean checkValidStringXX(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int count = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(c);
            else if (c == '*')
                count++;
            else {
                if (!stack.isEmpty())
                    stack.pop();
                else
                    count--;
            }
        }

        return (stack.isEmpty() || stack.size() == count) && count >= 0;
    }
}

/*
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
The following rules define a valid string:
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
Example 1:
Input: s = "()"
Output: true
Example 2:
Input: s = "(*)"
Output: true
Example 3:
Input: s = "(*))"
Output: true
Example 4:
Input: s = "("
Output: false

Constraints:
1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */


/*
We can use a two-pointer greedy approach, which checks the balance between open and closed brackets from both ends of
the array simultaneously, ensuring that no surplus or deficit of brackets occurs at any point during the iteration.
 */