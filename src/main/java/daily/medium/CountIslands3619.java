package daily.medium;

import java.util.Arrays;

public class CountIslands3619 {
    static void main() {
        int[][] grid = {{3,0,3,0},{0,3,0,3},{3,0,3,0}};
        System.out.println(countIslands(grid, 3));
    }

    public static int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        UnionFind dsu = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    if (j + 1 < n && grid[i][j + 1] != 0)
                        dsu.union(i * n + j, i * n + (j + 1));
                    if (i + 1 < m && grid[i + 1][j] != 0)
                        dsu.union(i * n + j, (i + 1) * n + j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] != 0) && dsu.find(i * n + j) == (i * n + j))
                    if (dsu.connectedSum(i * n + j) % k == 0)
                        count++;
            }
        }

        return count;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        private final int[] sum;
        UnionFind(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int k = m * n;
            parent = new int[k];
            rank = new int[k];
            sum = new int[k];
            for (int i = 0; i < k; i++) {
                parent[i] = i;
            }
            System.out.println(Arrays.toString(sum));
            System.out.println("******");
        }
        int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                    sum[rootX] += rootY;
                }
                else if (rank[rootY] > rank[rootX]) {
                    parent[rootX] = rootY;
                    sum[rootY] += rootX;
                }
                else {
                    parent[rootY] = rootX;
                    sum[rootX] += rootY;
                    rank[rootX]++;
                }
            }
        }
        int connectedSum(int i) {
            System.out.println(Arrays.toString(sum));
            return sum[i];
        }
    }
}

/*
You are given an m x n matrix grid and a positive integer k. An island is a group of positive integers (representing land) that are 4-directionally connected (horizontally or vertically).
The total value of an island is the sum of the values of all the cells within the island.
Return the number of islands with a total value divisible by k.
Example 1:
Input: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5
Output: 2
Explanation:
The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5, while the islands highlighted in red do not.
Example 2:
Input: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
Output: 6
Explanation:
The grid contains six islands, each with a total value that is divisible by 3.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 10^5
0 <= grid[i][j] <= 10^6
1 <= k <= 10^6
 */
