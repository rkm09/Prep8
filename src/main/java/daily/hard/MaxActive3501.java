package daily.hard;

import java.util.ArrayList;
import java.util.List;

public class MaxActive3501 {
    public static void main(String[] args) {
        String s = "0100";
        int[][] queries = {{0,3},{0,2},{1,3},{2,3}};
        System.out.println(maxActiveSectionsAfterTrade(s, queries));
    }

    public static List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {


        List<Integer> res = new ArrayList<>();

        return res;
    }

//    TLE! O(n^2)
    public static List<Integer> maxActiveSectionsAfterTradeX(String s, int[][] queries) {
        int cnt1 = 0;
        for (char c : s.toCharArray())
            if (c == '1')
                cnt1++;
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            List<Integer> zeroBlocks = new ArrayList<>();
            String sub = s.substring(query[0], query[1] + 1);
            int i = 0, n = sub.length();
            while (i < n) {
                int start = i;
                while (i < n && sub.charAt(start) == sub.charAt(i))
                    i++;
                if (sub.charAt(start) == '0')
                    zeroBlocks.add(i - start);
            }
            int m = zeroBlocks.size();
            if (m < 2) {
                res.add(cnt1);
                continue;
            }
            int bestGain = 0;
            for (int j = 0; j < m - 1; j++)
                bestGain = Math.max(bestGain, zeroBlocks.get(j) + zeroBlocks.get(j + 1));
            res.add(bestGain + cnt1);
        }

        return res;
    }
}

/*
You are given a binary string s of length n, where:
'1' represents an active section.
'0' represents an inactive section.
You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
Additionally, you are given a 2D array queries, where queries[i] = [li, ri] represents a substring s[li...ri].
For each query, determine the maximum possible number of active sections in s after making the optimal trade on the substring s[li...ri].
Return an array answer, where answer[i] is the result for queries[i].
Note
For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
The queries are independent of each other.
Example 1:
Input: s = "01", queries = [[0,1]]
Output: [1]
Explanation:
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.
Example 2:
Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]
Output: [4,3,1,1]
Explanation:
Query [0, 3] → Substring "0100" → Augmented to "101001"
Choose "0100", convert "0100" → "0000" → "1111".
The final string without augmentation is "1111". The maximum number of active sections is 4.
Query [0, 2] → Substring "010" → Augmented to "10101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "1110". The maximum number of active sections is 3.
Query [1, 3] → Substring "100" → Augmented to "11001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.
Query [2, 3] → Substring "00" → Augmented to "1001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.
Example 3:
Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]
Output: [6,7,2]
Example 4:
Input: s = "01010", queries = [[0,3],[1,4],[1,3]]
Output: [4,4,2]

Constraints:
1 <= n == s.length <= 10^5
1 <= queries.length <= 10^5
s[i] is either '0' or '1'.
queries[i] = [li, ri]
0 <= li <= ri < n
 */
