package daily.medium;

import java.util.*;

public class SafeWalk3286 {
    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> one = List.of(0,1,0,0,0);
        List<Integer> two = List.of(0,1,0,1,0);
        List<Integer> three = List.of(0,0,0,1,0);
        grid.add(one); grid.add(two); grid.add(three);
        System.out.println(findSafeWalk(grid, 1));
    }

//    Dijikstra; time: O(MNlogMN), space: O(MN)
    public static boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.getFirst().size();
        int[][] dist = new int[m][n];
        for (int[] ar : dist)
            Arrays.fill(ar, -1);

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {grid.getFirst().getFirst(), 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int val = curr[0];
            int cx = curr[1];
            int cy = curr[2];
//            if visited
            if (dist[cx][cy] >= 0)
                continue;
//            update with the new value
            dist[cx][cy] = val;

            for (int[] dir : dirs) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];
//                check for bounds
                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
//                check whether visited
                if (dist[nx][ny] >= 0)
                    continue;
                pq.offer(new int[] {val + grid.get(nx).get(ny), nx, ny});
            }
        }

        return dist[m - 1][n - 1] < health;
    }
}

/*
You are given an m x n binary matrix grid and an integer health.
You start from the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.
Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.
Example 1:
Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
Output: true
Explanation:
The final cell can be reached safely by walking along the gray cells below.
Example 2:
Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3
Output: false
Explanation:
A minimum of 4 health points is needed to reach the final cell safely.
Example 3:
Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
Output: true
Explanation:
The final cell can be reached safely by walking along the gray cells below.
Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 50
2 <= m * n
1 <= health <= m + n
grid[i][j] is either 0 or 1.
 */
