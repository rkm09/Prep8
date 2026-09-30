package daily.medium;

import java.util.Arrays;

public class MaxDepth1111 {
    public static void main(String[] args) {
        String seq = "()(())()";
        System.out.println(Arrays.toString(maxDepthAfterSplit(seq)));
    }

//    matching brackets; time: O(n), space: O(1)
    public static int[] maxDepthAfterSplit(String seq) {
        int depth = 0, n = seq.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depth++;
                ans[i] = depth % 2;
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }

        return ans;
    }
}

/*
A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:
It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
We can similarly define the nesting depth depth(S) of any VPS S as follows:
depth("") = 0
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length). The subsequences may not necessarily be contiguous.
For example, for the sequence 123456789, one possible split is:
A = {1, 3, 5, 7, 9},
B = {2, 4, 6, 8}.
This corresponds to the output [0, 1, 0, 1, 0, 1, 0, 1, 0]  where 0 indicates membership in A and 1 indicates membership in B.
Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.
Example 1:
Input: seq = "(()())"
Output: [0,1,1,1,1,0]
Example 2:
Input: seq = "()(())()"
Output: [0,0,0,1,1,0,1,1]

Constraints:
1 <= seq.size <= 10000
 */


/*
To split the string into two subsequences that minimize the maximum nesting depth, we first need to understand how 
to determine the nesting depth of each parenthesis. We can determine it by simulating parentheses matching with a
stack: Maintain a stack and traverse each character in the parentheses string from left to right:
If the current character is (, push it onto the stack; the nesting depth of this ( is the current size of the stack.
If the current character is ), the nesting depth of this ) is the current size of the stack, and then pop a ( from the stack.
Below is the nesting depth at each character for the bracket sequence (()(())()):

Parentheses	    (	(	)	(	(	)	)	(	)	)
Index	        0	1	2	3	4	5	6	7	8	9
Nesting depth	1	2	2	2	3	3	2	2	2	1
Knowing how to calculate the nesting depth, the solution becomes clear: by distributing the parentheses so that roughly
half of the nested depth belongs to sequence A and the other half belongs to sequence B, we minimize the maximum depth
of both subsequences to ⌈max_depth/2⌉. To achieve this balanced distribution, we can assign parentheses at odd nesting
depths to one group and those at even nesting depths to the other (using depth mod2). For the example above, parentheses
at nesting depths 1 and 3 (forming (())) are assigned to one group, while parentheses at nesting depth 2 (forming ()()())
are assigned to the other.
Furthermore, since the stack only tracks (, we do not need an actual stack data structure; an integer variable representing
the current stack depth is sufficient.
 */
