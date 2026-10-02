package daily.medium;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses22 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

//    backtrack; time: O(4^n/sqrt(n)), space: O(n)
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder curr, int open, int close, int max) {
//        base case: combination is complete
        if (curr.length() == max * 2) {
            result.add(curr.toString());
            return;
        }

//        choice 1: add open, if we haven't reached limit 'n'
        if (open < max) {
            curr.append("(");
            backtrack(result, curr, open + 1, close, max);
            curr.deleteCharAt(curr.length() - 1);
        }

//        choice 2: add close, if it balances previous open
        if (close < open) {
            curr.append(")");
            backtrack(result, curr, open, close + 1, max);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:
Input: n = 1
Output: ["()"]

Constraints:
1 <= n <= 8
 */
