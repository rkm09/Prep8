package daily.medium;

public class MinCost3882 {
    static void main() {
//        int[][] grid = {{1,2},{3,4}};
        int[][] grid = {{26,24,3},{2,22,17}};
        System.out.println(minCost(grid));
    }

//  dp; time: O(m.n), space: O(m.n)
    public static int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
//        dp[i][j][xorVal] will be true if 'xorVal' as the distance from (0,0) is achievable at cell (i,j)
        boolean[][][] dp = new boolean[m][n][1024];
        dp[0][0][grid[0][0]] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                for (int xorVal = 0; xorVal < 1024; xorVal++) {
                    if ((i > 0 && dp[i - 1][j][xorVal])
                            || (j > 0 && dp[i][j - 1][xorVal]))
                        dp[i][j][xorVal^ grid[i][j]] = true;
                }
            }
        }

//        find the minimum xor value present at the bottom right cell
        for (int xorValue = 0; xorValue < 1024; xorValue++)
            if (dp[m - 1][n - 1][xorValue])
                return xorValue;

        return -1;
    }


//  Does not work; 510/999; [[26,24,3],[2,22,17]] // output: 16, expected: 5
    public static int minCostX(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = grid[i][j];
                if ((i == 0) && j == 0)
                    continue;
                if ((i > 0) && (j > 0))
                    dp[i][j] = Math.min(dp[i][j] ^ dp[i - 1][j], dp[i][j] ^ dp[i][j - 1]);
                else if (i > 0)
                    dp[i][j] ^= dp[i - 1][j];
                else
                    dp[i][j] ^= dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}

/*
You are given a 2D integer array grid of size m * n.
You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).
At each step, you may move either right or down.
The cost of a path is defined as the bitwise XOR of all the values in the cells along that path, including the start and end cells.
Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1).
Example 1:
Input: grid = [[1,2],[3,4]]
Output: 6
Explanation:
There are two valid paths:
(0, 0) → (0, 1) → (1, 1) with XOR: 1 XOR 2 XOR 4 = 7
(0, 0) → (1, 0) → (1, 1) with XOR: 1 XOR 3 XOR 4 = 6
The minimum XOR value among all valid paths is 6.
Example 2:
Input: grid = [[6,7],[5,8]]
Output: 9
Explanation:
There are two valid paths:
(0, 0) → (0, 1) → (1, 1) with XOR: 6 XOR 7 XOR 8 = 9
(0, 0) → (1, 0) → (1, 1) with XOR: 6 XOR 5 XOR 8 = 11
The minimum XOR value among all valid paths is 9.
Example 3:
Input: grid = [[2,7,5]]
Output: 0
Explanation:
There is only one valid path:
(0, 0) → (0, 1) → (0, 2) with XOR: 2 XOR 7 XOR 5 = 0
The XOR value of this path is 0, which is the minimum possible.

Constraints:
1 <= m == grid.length <= 1000
1 <= n == grid[i].length <= 1000
m * n <= 1000
0 <= grid[i][j] <= 1023
 */


/*
Given a matrix grid with m rows and n columns, we need to find the minimum XOR value among all paths starting at (0,0)
and ending at (m−1,n−1). Since we can only move down or right, a path to cell (i,j) can only arrive from the cell above
(i−1,j) or the cell to the left (i,j−1). Unlike standard shortest path problems, bitwise XOR is non-monotonic
— an intermediate path with a larger XOR value can later yield a smaller final XOR value when combined with subsequent
cell values. Therefore, we cannot greedily track only the minimum XOR value at each cell.
Instead, we must track all reachable XOR values. Given that 0≤grid[i][j]≤1023<2^10, any XOR sum along any path will
never exceed 1023. With m⋅n≤1000, the total number of cell-value combinations is small (m⋅n⋅1024≤1.024×10^6). We can
define the dynamic programming state dp[i][j][v] to indicate whether cell (i,j) can be reached from (0,0) with an XOR
sum of exactly v. It is true if such a path exists, and false otherwise. The transition is:

dp[i][j][v]=dp[i−1][j][v⊕grid[i][j]]∨dp[i][j−1][v⊕grid[i][j]]

This transition uses the fact that XOR is its own inverse: a⊕b=c⟺a=c⊕b. Thus, the XOR value in the preceding state is
v⊕grid[i][j]. Equivalently, from a forward perspective, if an XOR value v is reachable at a predecessor cell, moving
into (i,j) allows us to reach v⊕grid[i][j]:

dp[i][j][v⊕grid[i][j]]=dp[i−1][j][v]∨dp[i][j−1][v]

Applying the transition at each position gives all possible XOR values of paths ending there. The answer is the
smallest v for which dp[m−1][n−1][v]=true.
Note: The states in this type of dynamic programming essentially maintain the set of candidate values at each position.
Depending on the language, we can use a boolean array, a bitset, or a hash set to implement these states.
Space Optimization:
This is a classic grid-path problem in which states can only be reached from above or from the left. Using the rolling
array technique, we can let dp[j] represent the state in the current row and traverse grid from top to bottom and left
to right: When processing cell (i,j), dp[j] before the update holds the reachable XOR values from the cell above,
(i−1,j). The already updated dp[j−1] holds the reachable XOR values from the cell to the left, (i,j−1).
To prevent newly computed XOR states from interfering with subsequent transitions for the same cell, we compute the
reachable states for (i,j) in a temporary array and then overwrite dp[j] immediately. This reduces the space
complexity from O(m⋅n⋅V) to O(n⋅V), where V=1024 is the number of possible XOR values.
*/