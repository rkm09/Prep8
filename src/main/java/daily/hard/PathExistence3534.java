package daily.hard;

import java.util.*;

public class PathExistence3534 {
    public static void main(String[] args) {
        int[][] queries = {{0,3},{2,4}};
        int[] nums = {1,8,3,4,2};
        System.out.println(Arrays.toString(pathExistenceQueries(5, nums, 3, queries)));
    }

//    bfs; time: O(V+E)
    public static int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = Math.abs(nums[j] - nums[i]);
                if (diff <= maxDiff) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        int[] res = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {

        }

        return res;
    }

    public static int bfs (List<List<Integer>> adjList, int node, int target) {
        return 0;
    }

//    TLE; dfs; time: O(2^n)
    public static int[] pathExistenceQueriesX(int n, int[] nums, int maxDiff, int[][] queries) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = Math.abs(nums[j] - nums[i]);
                if (diff <= maxDiff) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        int[] res = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int[] hops = {Integer.MAX_VALUE};
            boolean[] visited = new boolean[n];
            dfs(adjList, query[0], query[1], hops, 0, visited);
            res[i++] = hops[0] == Integer.MAX_VALUE ? -1 : hops[0];
        }

        return res;
    }

    private static void dfs(List<List<Integer>> adjList, int node, int target, int[] hops, int cnt, boolean[] visited) {
//        if our count is already bad or worse than return
        if (cnt >= hops[0])
            return;
        visited[node] = true;
        if (node == target)
            hops[0] = cnt; // since we have already checked on cnt
        else {
            for (int neighbor : adjList.get(node)) {
                if(!visited[neighbor]) {
//                    send cnt + 1, which automatically gets backtracked once it returns
                    dfs(adjList, neighbor, target, hops, cnt + 1, visited);
                    visited[neighbor] = false;
                }
            }
        } // could do this too instead: visited[node] = false;
    }


    private static void dfs1(List<List<Integer>> adjList, int node, int target, int[] hops, int cnt, boolean[] visited) {
        visited[node] = true;
        if (node == target)
            hops[0] = Math.min(hops[0], cnt);
        else {
            for (int neighbor : adjList.get(node)) {
                if(!visited[neighbor]) {
                    cnt++;
                    dfs(adjList, neighbor, target, hops, cnt, visited);
                    visited[neighbor] = false;
                    cnt--;
                }
            }
        }
    }
}

/*
You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
You are also given an integer array nums of length n and an integer maxDiff.
An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
You are also given a 2D integer array queries. For each queries[i] = [ui, vi], find the minimum distance between nodes ui and vi. If no path exists between the two nodes, return -1 for that query.
Return an array answer, where answer[i] is the result of the ith query.
Note: The edges between the nodes are unweighted.
Example 1:
Input: n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]
Output: [1,1]
Explanation:
The resulting graph is:
Query	Shortest Path	Minimum Distance
[0, 3]	0 → 3	1
[2, 4]	2 → 4	1
Thus, the output is [1, 1].
Example 2:
Input: n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]
Output: [1,2,-1,1]
Explanation:
The resulting graph is:
Query	Shortest Path	Minimum Distance
[0, 1]	0 → 1	1
[0, 2]	0 → 1 → 2	2
[2, 3]	None	-1
[4, 3]	3 → 4	1
Thus, the output is [1, 2, -1, 1].
Example 3:
Input: n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]
Output: [0,-1,-1]
Explanation:
There are no edges between any two nodes because:
Nodes 0 and 1: |nums[0] - nums[1]| = |3 - 6| = 3 > 1
Nodes 0 and 2: |nums[0] - nums[2]| = |3 - 1| = 2 > 1
Nodes 1 and 2: |nums[1] - nums[2]| = |6 - 1| = 5 > 1
Thus, no node can reach any other node, and the output is [0, -1, -1].

Constraints:
1 <= n == nums.length <= 105
0 <= nums[i] <= 105
0 <= maxDiff <= 105
1 <= queries.length <= 105
queries[i] == [ui, vi]
0 <= ui, vi < n
 */
