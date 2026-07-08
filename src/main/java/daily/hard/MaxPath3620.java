package daily.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPath3620 {
    public static void main(String[] args) {
//        int[][] edges = {{0,1,5},{1,3,10},{0,2,3},{2,3,4}};
//        int[][] edges = {{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}}; //k=12, 3 - false
        int[][] edges = {{0,1,0},{0,2,7},{1,3,9},{0,4,7},{2,4,9},{3,4,2},{0,3,5},{2,3,3},{1,4,6},{1,2,0}}; // k = 5
        boolean[] online = {true,true,true,true,true};
//        boolean[] online = {true,true,true,false,true};
        System.out.println(findMaxPathScore(edges, online, 5));
//        int[][] edges = {{0,1},{0,4},{1,4},{1,2},{2,3}};
//        this diamond chain dag would make normal dfs fail. need memoization.
//        int[][] edges = {{0,1},{0,2},{1,3},{2,3},{3,4},{3,5},{4,5},{5,6},{6,7},{6,8},{7,9},{8,9}};
//        System.out.println(findValidPaths(edges, 5));
    }


    public static int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        List<List<int[]>> adjList = new ArrayList<>();
        int n = online.length;
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int x = edge[2];
            if (online[u] && online[v])
                adjList.get(u).add(new int[]{v,x});
        }

        int[] maxMin = {Integer.MAX_VALUE, -1, 0};
        dfsMax(adjList, 0, n, maxMin, k);

        return maxMin[1];
    }

    private static void dfsMax(List<List<int[]>> adjList, int node, int n, int[] maxMin, long k) {
        if (node == n - 1) {
            if (maxMin[2] > k) return;
            maxMin[1] = Math.max(maxMin[0], maxMin[1]);
        } else {
            for (int[] neighbor : adjList.get(node)) {
                int prevMin = maxMin[0];
                maxMin[0] = Math.min(maxMin[0], neighbor[1]);
                maxMin[2] += neighbor[1];
                dfsMax(adjList, neighbor[0], n, maxMin, k);
                maxMin[0] = prevMin;
                maxMin[2] -= neighbor[1];
            }
        }
    }

//    exercise to find just a constraint less list of all valid paths from 0 to n-1
    public static List<List<Integer>> findValidPaths(int[][] edges, int n) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }
        List<List<Integer>> paths = new ArrayList<>();
        dfs(paths, adjList, 0, n, new ArrayList<>());
        int[] cnt = new int[1];
        dfs1(adjList, 0, n, cnt);
        System.out.println("count: " + cnt[0]);
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        System.out.println("count with memo: " + dfs2(adjList, 0, n, memo));
        return paths;
    }

//    add and then back track; time: O(2^n)
    private static void dfs(List<List<Integer>> paths, List<List<Integer>> adjList, int node, int n, List<Integer> curr) {
//        choose: add the current node to the path
        curr.add(node);
//        base case: if we reached the destination (n - 1), capture a deep copy
        if (node == n - 1)
            paths.add(new ArrayList<>(curr));
        else
//            explore: recurse into all neighboring branches
            for (int neighbor : adjList.get(node))
                dfs(paths, adjList, neighbor, n, curr);
//            un-choose(backtrack): clean up the state before popping off the execution stack
        curr.removeLast();
    }

//    dfs; time: O(2^N) not feasible for complex structures
    private static void dfs1(List<List<Integer>> adjList, int node, int n, int[] cnt) {
        if (node == n - 1)
            cnt[0]++;
        else
            for (int neighbor : adjList.get(node))
                dfs1(adjList, neighbor, n, cnt);
    }

//    dfs with memo; will handle diamond chains easily too; time: O(V+E)
    private static int dfs2(List<List<Integer>> adjList, int node, int n, int[] memo) {
//        base case: if we are at the destination, there is exactly one way here
        if (node == n - 1)
            return 1;
//        if we have already calculated the paths from here, return the cached result
        if (memo[node] != -1)
            return memo[node];

        int totalWaysFromHere = 0;
        for (int neighbor : adjList.get(node))
//            number of ways from the current node, is the sum of all the ways from its neighbors
            totalWaysFromHere += dfs2(adjList, neighbor, n, memo);

//        cache the result before returning
        memo[node] = totalWaysFromHere;

        return totalWaysFromHere;
    }

}

/*
You are given a directed acyclic graph of n nodes numbered from 0 to n−1. This is represented by a 2D array edges of length m,
where edges[i] = [ui, vi, costi] indicates a one‑way communication from node ui to node vi with a recovery cost of costi.
Some nodes may be offline. You are given a boolean array online where online[i] = true means nodei is online.
Nodes 0 and n−1 are always online. A path from 0 to n − 1 is valid if:
- All intermediate nodes on the path are online.
- The total recovery cost of all edges on the path does not exceed k.
For each valid path, define its score as the minimum edge‑cost along that path.
Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths.
If no valid path exists, return -1.
Example 1:
Input: edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10
Output: 3
Explanation:
The graph has two possible routes from node 0 to node 3:
Path 0 → 1 → 3
Total cost = 5 + 10 = 15, which exceeds k (15 > 10), so this path is invalid.
Path 0 → 2 → 3
Total cost = 3 + 4 = 7 <= k, so this path is valid.
The minimum edge‐cost along this path is min(3, 4) = 3.
There are no other valid paths. Hence, the maximum among all valid path‐scores is 3.
Example 2:
Input: edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12
Output: 6
Explanation:
Node 3 is offline, so any path passing through 3 is invalid.
Consider the remaining routes from 0 to 4:
Path 0 → 1 → 4
Total cost = 7 + 5 = 12 <= k, so this path is valid.
The minimum edge‐cost along this path is min(7, 5) = 5.
Path 0 → 2 → 3 → 4
Node 3 is offline, so this path is invalid regardless of cost.
Path 0 → 2 → 4
Total cost = 6 + 6 = 12 <= k, so this path is valid.
The minimum edge‐cost along this path is min(6, 6) = 6.
Among the two valid paths, their scores are 5 and 6. Therefore, the answer is 6.

Constraints:
n == online.length
2 <= n <= 5 * 10^4
0 <= m == edges.length <= min(10^5, n * (n - 1) / 2)
edges[i] = [ui, vi, costi]
0 <= ui, vi < n
ui != vi
0 <= costi <= 10^9
0 <= k <= 5 * 10^13
online[i] is either true or false, and both online[0] and online[n − 1] are true.
The given graph is a directed acyclic graph.
 */


/*
Every time we add just 3 more nodes (one more diamond link) to the graph, we double the total number of paths.
Connecting Nodes to Operations: If you have a graph with $n$ nodes structured this way, you will have roughly $\frac{n}{3}$ diamonds.
The total number of unique paths from the start to the destination is:$$\text{Total Paths} = 2^{\frac{n}{3}}$$Mathematically,
any base raised to a fraction of $n$ (like $2^{n/3}$) still represents exponential growth, which falls under the asymptotic umbrella of $O(2^n)$.

For 80% of problems, thinking "DFS for reachability/structure" and "BFS/Dijkstra for metrics/shortest paths" will get
you through.But there are absolutely deeper grooves to this pattern. When you transition into advanced graph engineering,
the choice between DFS and BFS isn't just about what you are looking for, but the shape of the graph and the physics of memory.
Here are the hidden grooves that alter the strategy.

Groove 1: The "Shallow Target" Trap
If you only want to know if any path exists from A to B, your model says DFS.
But consider a graph with a depth of 100,000 nodes, where your destination $B$ is literally a
direct neighbor of your starting node $A$.DFS: Depending on the order of the edges, DFS might pick the wrong neighbor first, plunge 100,000
levels deep into the graph, and spend massive amounts of time exploring a massive dead-end before backtracking to find the target right next door.
BFS: Explores level-by-level. It checks all immediate neighbors first and finds $B$ in exactly 1 step.
The Pattern: If you suspect the target is structurally "close" to the source, BFS is a far safer bet for reachability than DFS, even if you don't care
about the shortest path.

Groove 2: Memory Topology (Stack vs. Queue)
The choice between DFS and BFS is often dictated by your memory constraints,
because they use completely different data structures under the hood.DFS uses a Stack ($O(\text{Max Depth})$ space): It only cares about the current
path it is exploring.BFS uses a Queue ($O(\text{Max Width})$ space): It has to hold the entire "frontier" of the current layer in memory.If you are dealing with a wide,
shallow graph (like a social network where one user has 10,000 friends, and they each have 10,000 friends), BFS will explode your memory usage because the queue has to hold
millions of nodes at layer 2. DFS thrives here because the stack depth is tiny.Conversely, if the graph is a narrow, deeply nested spine, DFS will throw a StackOverflowError,
while BFS will elegantly process it using almost no memory.

Groove 3: State Space Search (Implicit Graphs)
Sometimes the graph isn't a collection of nodes you built in an adjacency list;
it's a puzzle. For example, "Change the word COLD to WARM by changing one letter at a time."Each word is a node, and changing a letter is an edge.
If you want to find any sequence, DFS works.But in these problems, you almost always want the fewest steps. Because every edge has an implicit
weight of 1, BFS acts as an optimization algorithm, guaranteeing that the first time you hit the target word, you found it in the minimum possible moves.
 */