package daily.hard;

import java.util.Arrays;
import java.util.List;

public class MaxScore1301 {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        List<String> board = List.of("E23","2X2","12S");
        System.out.println(Arrays.toString(pathsWithMaxScore(board)));
    }

//    dp; time: O(n^2), space: O(n^2)
    public static int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
//        dp[i][j][0] - max sum, dp[i][j][1] - path count
        int[][][] dp = new int[n][n][2];
//        initialize starting point 'E' at (0,0)
        dp[0][0][1] = 1;
//        valid directions (top, left, top-left) (looking-backward)
        int[][] dirs = {{-1,0}, {0,-1}, {-1,-1}};
//        process cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board.get(i).charAt(j);
//                skip if first cell (already processed) or invalid cell
                if ((i == 0 && j == 0) || c == 'X')
                    continue;
                int maxPrevSum = -1;
                int totalPaths = 0;
//                check the 3 incoming directions
                for (int[] dir : dirs) {
                    int pi = i + dir[0];
                    int pj = j + dir[1];
//                    if the neighbor is valid and reachable
                    if (pi >= 0 && pj >= 0 && dp[pi][pj][1] > 0) {
                        if (dp[pi][pj][0] > maxPrevSum) {
                            maxPrevSum = dp[pi][pj][0];
                            totalPaths = dp[pi][pj][1];
                        } else if (dp[pi][pj][0] == maxPrevSum)
                            totalPaths = (totalPaths + dp[pi][pj][1]) % MOD;
                    }
                }
//                if this cell is reachable from at least one neighbor
                if (maxPrevSum != -1) {
                    int currVal = (c == 'S') ? 0 : c - '0';
                    dp[i][j][0] = maxPrevSum + currVal;
                    dp[i][j][1] = totalPaths;
                }
            }
        }

        return dp[n-1][n-1];
    }
}

/*
You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
In case there is no path, return [0, 0].
Example 1:
Input: board = ["E23","2X2","12S"]
Output: [7,1]
Example 2:
Input: board = ["E12","1X1","21S"]
Output: [4,2]
Example 3:
Input: board = ["E11","XXX","11S"]
Output: [0,0]

Constraints:
2 <= board.length == board[i].length <= 100
 */