package daily.medium;

import java.util.*;

public class MinScore2492 {
    public static void main(String[] args) {
        MinScore2492 m = new MinScore2492();
        int[][] roads1 = {{1,2,9},{2,3,6},{2,4,5},{1,4,7}};
        int[][] roads = {{1,2,5},{2,3,7},{6,7,2},{3,4,8},{4,5,9},{5,8,10}};
        System.out.println(m.minScore(8, roads));
    }

//    union find; time: O(E.alpha(E)), space: O(V)
    public int minScore(int n, int[][] roads) {
        UnionFind dsu = new UnionFind(n);
        int min = Integer.MAX_VALUE;
        for (int[] road : roads)
            dsu.union(road[0], road[1]);

//        find representative of 1st node
        int root1 = dsu.find(1);
        for (int[] road : roads) {
            if (dsu.find(road[0]) == root1)
                min = Math.min(min, road[2]);
        }

        return min;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++)
                parent[i] = i;
        }

        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) {
                if (rank[px] > rank[py])
                    parent[py] = px;
                else if (rank[px] < rank[py])
                    parent[px] = py;
                else {
                    parent[py] = px;
                    rank[px]++;
                }
            }
        }
    }
}

/*
You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
The score of a path between two cities is defined as the minimum distance of a road in this path.
Return the minimum possible score of a path between cities 1 and n.
Note:
A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n.
Example 1:
Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
Output: 5
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
Example 2:
Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
Output: 2
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.

Constraints:
2 <= n <= 105
1 <= roads.length <= 105
roads[i].length == 3
1 <= ai, bi <= n
ai != bi
1 <= distancei <= 104
There are no repeated edges.
There is at least one path between 1 and n.
 */
