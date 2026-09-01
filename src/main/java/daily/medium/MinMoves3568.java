package daily.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinMoves3568 {
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) {
        String[] classroom = {"S","XL"};
        System.out.println(minMoves(classroom, 2));
    }

//    Shortest path; bfs; time: O(), space: O()
    public static int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int startR = -1, startC = -1;
        int totalLitter = 0;
        int[][] litterIndex = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(litterIndex[i], -1);
//        grid initialization
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterIndex[r][c] = totalLitter++;
                }
            }
        }

//        edge case: no litter to pick up
        if (totalLitter == 0)
            return 0;

        int targetMask = (1 << totalLitter) - 1;
//        visited[r][c][mask] stores max remaining energy seen for state (r, c, mask)
        int[][][] visited = new int[m][n][1 << totalLitter];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(visited[i][j], - 1);

//        queue elements {r, c, mask, remainingEnergy}
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, 0, energy});
        visited[startR][startC][0] = energy;

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int currEnergy = curr[3];

//                out of energy
                if (currEnergy == 0)
                    continue;

                for (int[] dir : DIRS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
//                    check boundary
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X')
                        continue;

                    int nextEnergy = currEnergy - 1;
                    int nextMask = mask;
                    char cell = classroom[nr].charAt(nc);
//                    restore energy to maximum capacity
                    if (cell == 'R')
                        nextEnergy = energy;
                    else if (cell == 'L')
                        nextMask |= (1 << litterIndex[nr][nc]);

                    if (nextMask == targetMask)
                        return moves + 1;

//                    prune paths that arrive at (nr, nc, nextMask) with lower or equal energy
                    if (nextEnergy > visited[nr][nc][nextMask]) {
                        visited[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[] {nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}

/*
You are given an m x n grid classroom where a student volunteer is tasked with cleaning up litter scattered around the room. Each cell in the grid is one of the following:
'S': Starting position of the student
'L': Litter that must be collected (once collected, the cell becomes empty)
'R': Reset area that restores the student's energy to full capacity, regardless of their current energy level (can be used multiple times)
'X': Obstacle the student cannot pass through
'.': Empty space
You are also given an integer energy, representing the student's maximum energy capacity. The student starts with this energy from the starting position 'S'.
Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the student can only continue if they are on a reset area 'R', which resets the energy to its maximum capacity energy.
Return the minimum number of moves required to collect all litter items, or -1 if it's impossible.
Example 1:
Input: classroom = ["S.", "XL"], energy = 2
Output: 2
Explanation:
The student starts at cell (0, 0) with 2 units of energy.
Since cell (1, 0) contains an obstacle 'X', the student cannot move directly downward.
A valid sequence of moves to collect all litter is as follows:
Move 1: From (0, 0) → (0, 1) with 1 unit of energy and 1 unit remaining.
Move 2: From (0, 1) → (1, 1) to collect the litter 'L'.
The student collects all the litter using 2 moves. Thus, the output is 2.
Example 2:
Input: classroom = ["LS", "RL"], energy = 4
Output: 3
Explanation:
The student starts at cell (0, 1) with 4 units of energy.
A valid sequence of moves to collect all litter is as follows:
Move 1: From (0, 1) → (0, 0) to collect the first litter 'L' with 1 unit of energy used and 3 units remaining.
Move 2: From (0, 0) → (1, 0) to 'R' to reset and restore energy back to 4.
Move 3: From (1, 0) → (1, 1) to collect the second litter 'L'.
The student collects all the litter using 3 moves. Thus, the output is 3.
Example 3:
Input: classroom = ["L.S", "RXL"], energy = 3
Output: -1
Explanation:
No valid path collects all 'L'.

Constraints:
1 <= m == classroom.length <= 20
1 <= n == classroom[i].length <= 20
classroom[i][j] is one of 'S', 'L', 'R', 'X', or '.'
1 <= energy <= 50
There is exactly one 'S' in the grid.
There are at most 10 'L' cells in the grid.
 */

/*
Complexity Analysis
Time Complexity:
O(m.n.2^{K}.E), where $m \times n$ is the grid size, $K$ is the number of litter cells ($K \le 10$),
and $E$ is the maximum energy capacity ($E \le 50$). With state pruning via visited, each state (r, c, mask) is expanded
at most $E$ times.
Total operations: $20 \times 20 \times 1024 \times 50 \approx 2 \times 10^7$, well within the time limit.
Space Complexity:
O(m.n.2^{K})) to store maximum energy states in the 3D array visited.
 */