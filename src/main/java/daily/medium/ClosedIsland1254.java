package daily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ClosedIsland1254 {
    static void main() {
        int[][] grid = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
        int[][] grid1 = {{1,1,0,1,1,1,1,1,1,1},{0,0,1,0,0,1,0,1,1,1},{1,0,1,0,0,0,1,0,1,0},
                {1,1,1,1,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,0},{0,0,0,0,1,1,0,0,0,0},
                {1,0,1,0,0,0,0,1,1,0},{1,1,0,0,1,1,0,0,0,0},{0,0,0,1,1,0,1,1,1,0},
                {1,1,0,1,0,1,0,0,1,0}};
        System.out.println(closedIsland(grid1));
    }

//    bfs; time: O(m.n), space: O(m.n)
    public static int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visited[i][j] && bfs(i, j, m, n, grid, visited))
                    count++;
            }
        }

        return count;
    }

    private static boolean bfs(int x, int y, int m, int n, int[][] grid, boolean[][] visited) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        boolean isClosed = true;
        int[][] dir = {{0,-1}, {0, 1},{-1, 0},{1, 0}};
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            x = temp[0];
            y = temp[1];
            for (int i = 0; i < 4; i++) {
                int r = x + dir[i][0];
                int c = y + dir[i][1];
//                (x, y) is a boundary cell
                if (r < 0 || r == m || c < 0 || c == n)
                    isClosed = false;
                else if (grid[r][c] == 0 && !visited[r][c]){
                    visited[r][c] = true;
                    queue.offer(new int[] {r, c});
                }
            }
        }

        return isClosed;
    }

//    dfs; time: O(m.n), space: O(m.n)
    public static int closedIsland1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visited[i][j] && dfs(i, j, m, n, grid, visited))
                    count++;
            }
        }

        return count;
    }

    private static boolean dfs(int x, int y, int m, int n, int[][] grid, boolean[][] visited) {
        if (x < 0 || x == m || y < 0 || y == n)
            return false;
        if (grid[x][y] == 1 || visited[x][y])
            return true;
        visited[x][y] = true;
        int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};
        boolean isClosed = true;
        for (int i = 0; i < 4; i++) {
            int r = x + dir[i][0];
            int c = y + dir[i][1];
            if (!dfs(r, c, m, n, grid, visited))
                isClosed = false;
        }

        return isClosed;
    }

//    union find; time: O(m.n.alpha(m)), space: O(m.n)
    public static int closedIsland2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind dsu = new UnionFind(m * n + 1);
        int boundary = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                if it is a land cell
                if (grid[i][j] == 0) {
//                    1. if the cell touches outer boundary, unite it with boundary cell
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1)
                        dsu.union( i * n + j, boundary);
//                    2. unite with the right neighbor if it is land
                    if ((j + 1) < n && grid[i][j + 1] == 0)
                        dsu.union(i * n + j, i * n + (j + 1));
//                    3. unite with the bottom neighbor if it is land
                    if ((i + 1) < m && grid[i + 1][j] == 0)
                        dsu.union(i * n + j, (i + 1) * n + j);
                }
            }
        }

        int closedIslandCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                check if it is land && acts as the root of its connected component
                if (grid[i][j] == 0 && dsu.find(i * n + j) == i * n + j) {
//                    ensure that the connected component is actually a closed one, i.e. it is not connected to the boundary node
                    if (!dsu.isConnected((i * n + j), boundary))
                        closedIslandCount++;
                }
            }
        }

        return closedIslandCount;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }
        int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY])
                    parent[rootY] = rootX;
                else if (rank[rootY] > rank[rootX])
                    parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }

        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}

/*
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
Return the number of closed islands.
Example 1:
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

Constraints:
1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */
