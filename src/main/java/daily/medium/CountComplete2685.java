package daily.medium;

import java.util.*;

public class CountComplete2685 {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,2},{3,4}};
        System.out.println(countCompleteComponents(6, edges));
    }

//    union find; time: O(v + e), space: O(v + e)
    public static int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> components = new HashMap<>();
        int count = 0;
        int[] degrees = new int[n];
        UnionFind dsu = new UnionFind(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }

        for (int v = 0; v < n; v++) {
            int parent = dsu.find(v);
            components.computeIfAbsent(parent, k -> new HashSet<>())
                    .add(v);
        }

        for (var entry : components.entrySet()) {
            Set<Integer> set = entry.getValue();
            int size = set.size();
            boolean isComplete = true;
            for (int node : set) {
                if (degrees[node] != size - 1) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete)
                count++;
        }

        return count;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0 ; i < n; i++)
                parent[i] = i;
        }

        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union (int x, int y) {
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
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.
Return the number of complete connected components of the graph.
A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
A connected component is said to be complete if there exists an edge between every pair of its vertices.
Example 1:
Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.
Example 2:
Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.

Constraints:
1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.
 */