package daily.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShiftGrid1260 {
    public static void main(String[] args) {
        int[][] grid1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] grid2 = {{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}};
        int[][] grid = {{1},{2},{3}};
        List<List<Integer>> res = shiftGrid(grid, 4);
        for (List<Integer> r : res)
            System.out.println(r);
    }

//    convert to 1d; time: O(m*n), space: O(m*n)
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        k %= total;
        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            List<Integer> row = new ArrayList<>();
            for (int c = 0; c < n; c++) {
//                calculate the 1D of the current output position
                int output1D = r * n + c;
//                reverse the shift, find the 1D index of the original element at this position
//                add + total to deal with negative modulo safely
                int input1D = (output1D - k + total) % total;
//                pull directly from the grid and add straight to the row
                row.add(grid[input1D / n][input1D % n]);
            }
            res.add(row);
        }

        return res;
    }

//    convert to 1d; time: O(m*n), space: O(m*n)
    public static List<List<Integer>> shiftGrid1(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
//        optimization; after m * n iterations you land back to the original
        k %= total;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++)
            res.add(new ArrayList<>(Collections.nCopies(n, 0)));
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
//                find the current 1d
                int old1D = r * n + c;
//                calculate the new 1d after shifting k times
                int new1D = (old1D + k) % total;
//                convert the 1d positions back to 2d coordinates
                int newR = new1D / n;
                int newC = new1D % n;
//                place it directly into the result
                res.get(newR).set(newC, grid[r][c]);
            }
        }

        return res;
    }

//    simulation; time: O(m*n), space: O(m*n)
    public static List<List<Integer>> shiftGrid2(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        int[] last = new int[m];
        List<List<Integer>> res = new ArrayList<>();
        k %= total;
        while (k-- > 0) {
            for (int i = 0; i < m; i++)
                last[i] = grid[i][n - 1];

            for (int i = 0; i < m; i++)
                for (int j = n - 1; j > 0; j--)
                    grid[i][j] = grid[i][j - 1];

            grid[0][0] = last[m - 1];
            for (int i = 1; i < m; i++)
                grid[i][0] = last[i - 1];
        }

        for (int[] cell : grid) {
            List<Integer> li = new ArrayList<>();
            for (int j = 0; j < n; j++) {
               li.add(cell[j]);
            }
            res.add(li);
        }

        return res;
    }
}

/*
Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
In one shift operation:
Element at grid[i][j] moves to grid[i][j + 1].
Element at grid[i][n - 1] moves to grid[i + 1][0].
Element at grid[m - 1][n - 1] moves to grid[0][0].
Return the 2D grid after applying shift operation k times.
Example 1:
Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]
Example 2:
Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:
Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]

Constraints:
m == grid.length
n == grid[i].length
1 <= m <= 50
1 <= n <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
 */
