package daily.hard;

import java.util.*;

public class InvalidPar301 {
    public static void main(String[] args) {
        String s = "()())()";
        System.out.println(removeInvalidParentheses(s));
    }

//    bfs; time: O(n), space: O(n)
    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (isValid(curr)) {
                found = true;
                result.add(curr);
            }

//            if we found a valid string at this level, don't generate next level
            if (found) continue;

//            generate next state by removing one parenthesis at a time
            for (int i = 0; i < curr.length(); i++) {
                char c = curr.charAt(i);
//                skip letters
                if (c != '(' && c != ')')
                    continue;
                String next = curr.substring(0, i) + curr.substring(i + 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    private static boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                count++;
//            note: there can be letters too, so avoid that by making a conditional else
            else if (c == ')') {
                count--;
                if (count < 0)
                    return false;
            }
        }

        return count == 0;
    }
}

/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:
Input: s = ")("
Output: [""]

Constraints:
1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
 */
