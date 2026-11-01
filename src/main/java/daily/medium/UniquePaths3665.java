package daily.medium;

public class UniquePaths3665 {
    private static final int MOD = 1_000_000_007;
    static void main() {
        UniquePaths3665 u = new UniquePaths3665();
        int[][] grid = {{0,1,0},{0,0,1},{1,0,0}};
        System.out.println(u.uniquePaths(grid));
    }

//    dp + dfs; time: O(m.n), space: O(m.n)
    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfs(0, 0, m, n, grid, memo);
    }

    private int dfs(int r, int c, int m, int n, int[][] grid, Integer[][] memo) {
        if (r == m - 1 && c == n - 1)
            return 1;
        if (memo[r][c] != null)
            return memo[r][c];
        long totalPaths = 0;
//        Try moving right (starting direction 0 = moving right)
        int[] rightNext = simulate(grid, r, c + 1, m, n, 0);
        if (rightNext != null)
            totalPaths = (totalPaths + dfs(rightNext[0], rightNext[1], m, n, grid, memo)) % MOD;
//        Try moving down (starting direction 1 = moving down)
        int[] downNext = simulate(grid, r + 1, c, m, n, 1);
        if (downNext != null)
            totalPaths = (totalPaths + dfs(downNext[0], downNext[1], m, n, grid, memo)) % MOD;

        return memo[r][c] = (int) totalPaths;
    }

//    Unified simulation helper: handles chain reaction reflections for both directions
//    dir 0 = moving right, 1 = moving down
    private int[] simulate(int[][] grid, int r, int c, int m, int n, int dir) {
        int currR = r, currC = c;
        while (true) {
            if (currR < 0 || currR >= m || currC < 0 || currC >= n)
                return null;  // out of bounds
            if (grid[currR][currC] == 0)
                return new int[] {currR, currC};  // landed on an empty cell
//            hit a mirror (grid[currR][currC] == 1), reflect based on entry direction
            if (dir == 0) {
                currR++;
                dir = 1; // turned down
            } else {
                currC++;
                dir = 0;  // turned right
            }
        }
    }
}

/*
Given an m x n binary grid 'grid' where:
grid[i][j] == 0 represents an empty cell, and
grid[i][j] == 1 represents a mirror.
A robot starts in the top-left corner of the grid (0, 0) and wants to reach the bottom-right corner (m - 1, n - 1).
It can move only right or down. If the robot attempts to move into a mirror cell, it is reflected before entering that
cell:
If it tries to move right into a mirror, it is turned down and moved into the cell directly below the mirror.
If it tries to move down into a mirror, it is turned right and moved into the cell directly to the right of the mirror.
If this reflection causes the robot to move outside the grid boundaries, the path is considered invalid and should
not be counted.
Return the number of unique valid paths from (0, 0) to (m - 1, n - 1).
Since the answer may be very large, return it modulo 109 + 7.
Note: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the direction
it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered while moving down,
it will be turned right. This process will continue until either the last cell is reached, the robot moves out of bounds
or the robot moves to a non-mirror cell.
Example 1:
Input: grid = [[0,1,0],[0,0,1],[1,0,0]]
Output: 5
Explanation:
Number	Full Path
1	(0, 0) → (0, 1) [M] → (1, 1) → (1, 2) [M] → (2, 2)
2	(0, 0) → (0, 1) [M] → (1, 1) → (2, 1) → (2, 2)
3	(0, 0) → (1, 0) → (1, 1) → (1, 2) [M] → (2, 2)
4	(0, 0) → (1, 0) → (1, 1) → (2, 1) → (2, 2)
5	(0, 0) → (1, 0) → (2, 0) [M] → (2, 1) → (2, 2)
[M] indicates the robot attempted to enter a mirror cell and instead reflected.
Example 2:
Input: grid = [[0,0],[0,0]]
Output: 2
Explanation:
Number	Full Path
1	(0, 0) → (0, 1) → (1, 1)
2	(0, 0) → (1, 0) → (1, 1)
Example 3:
Input: grid = [[0,1,1],[1,1,0]]
Output: 1
Explanation:
Number	Full Path
1	(0, 0) → (0, 1) [M] → (1, 1) [M] → (1, 2)
(0, 0) → (1, 0) [M] → (1, 1) [M] → (2, 1) goes out of bounds, so it is invalid.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 500
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
 */
