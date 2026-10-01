package daily.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ValidParentheses20 {
    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

//    optimized stack array; time: O(n), space: O(n)
    public static boolean isValid(String s) {
        int n = s.length();
        if ((n & 1) != 0)
            return false;
        char[] stack = new char[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(' ->
                    stack[++top] = ')';
                case '[' ->
                    stack[++top] = ']';
                case '{' ->
                    stack[++top] = '}';
                default -> {
                    if (top == -1 || stack[top--] != c)
                        return false;
                }
            }
        }

        return top == -1;
    }

    public static boolean isValid1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> keyMap = new HashMap<>();
        keyMap.put(')','(');
        keyMap.put(']','[');
        keyMap.put('}','{');
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                if (stack.pop() != keyMap.get(c))
                    return false;
            }
        }

        return stack.isEmpty();
    }
}

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
Example 1:
Input: s = "()"
Output: true
Example 2:
Input: s = "()[]{}"
Output: true
Example 3:
Input: s = "(]"
Output: false
Example 4:
Input: s = "([])"
Output: true
Example 5:
Input: s = "([)]"
Output: false

Constraints:
1 <= s.length <= 10^4
s consists of parentheses only '()[]{}'.
 */